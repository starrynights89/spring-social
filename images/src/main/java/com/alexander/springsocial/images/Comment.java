package com.alexander.springsocial.images;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Alexander Hartson
 */

@Data
public class Comment {

    @Id private String id;
    private String imageId;
    private String comment;
}
