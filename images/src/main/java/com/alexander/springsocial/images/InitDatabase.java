package com.alexander.springsocial.images;

import com.alexander.springsocial.images.Image;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase {

    @Bean
    CommandLineRunner init(MongoOperations operations) {
        return args -> {
            operations.dropCollection(Image.class);

            operations.insert(new Image("1",
                    "image1.jpg", "alexander"));
            operations.insert(new Image("2",
                    "image2.jpg", "alexander"));
            operations.insert(new Image("3",
                    "image3.jpg", "phil"));

            operations.findAll(Image.class).forEach(image -> {
                System.out.println(image.toString());
            });
        };
    }
}
