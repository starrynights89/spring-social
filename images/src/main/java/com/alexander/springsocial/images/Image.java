package com.alexander.springsocial.images;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Alexander Hartson
 */

@Data
@AllArgsConstructor
public class Image {

    @Id private String id;
    private String name;
    private String owner;
}
