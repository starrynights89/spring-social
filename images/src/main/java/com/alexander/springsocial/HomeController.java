package com.alexander.springsocial;

import com.alexander.springsocial.images.CommentHelper;
import com.alexander.springsocial.images.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Hartson
 */
@Controller
public class HomeController {

    private final ImageService imageService;

    // tag injection
    private final CommentHelper commentHelper;

    public HomeController(ImageService imageService,
                          CommentHelper commentHelper) {
        this.imageService = imageService;
        this.commentHelper = commentHelper;
    }
    // end injection

    @GetMapping("/")
    public Mono<String> index(Model model, WebSession webSession) {
        model.addAttribute("images",
                imageService
                        .findAllImages()
                        .map(image -> new HashMap<String, Object>() {{
                            put("id", image.getId());
                            put("name", image.getName());
                            put("owner", image.getOwner());
                            // comments
                            put("comments", commentHelper.getComments(image,
                                    webSession.getId()));
                        }})
        );
        return Mono.just("index");
    }

    @GetMapping("/token")
    @ResponseBody
    public Mono<Map<String, String>> token(WebSession webSession) {
        return Mono.just(Collections.singletonMap("token", webSession.getId()));
    }
}
