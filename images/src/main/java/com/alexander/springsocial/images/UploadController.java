package com.alexander.springsocial.images;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Alexander Hartson
 */
@Controller
public class UploadController {

    private static final String BASE_PATH = "/images";
    private static final String FILENAME = "{filename:.+}";

    private final ImageService imageService;

    public UploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = BASE_PATH + "/" + FILENAME + "/raw",
            produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Mono<ResponseEntity<?>> oneRawImage(
            @PathVariable String filename) {
        return imageService.findOneImage(filename).map(resource -> {
            try {
                return ResponseEntity.ok()
                        .contentLength(resource.contentLength())
                        .body(new InputStreamResource(
                                resource.getInputStream()));
            } catch (IOException e) {
                return ResponseEntity.badRequest()
                        .body("Couldn't find " + filename +
                                " => " + e.getMessage());
            }
        });
    }

    @PostMapping(value = BASE_PATH)
    public Mono<String> createFile(@RequestPart("file") Flux<FilePart> files,
                                   @AuthenticationPrincipal Principal principal) {
        return imageService.createImage(files, principal)
                .then(Mono.just("redirect:/"));
    }

    @DeleteMapping(BASE_PATH + "/" + FILENAME)
    public Mono<String> deleteFile(@PathVariable String filename) {
        return imageService.deleteImage(filename)
                .then(Mono.just("redirect:/"));
    }
}
