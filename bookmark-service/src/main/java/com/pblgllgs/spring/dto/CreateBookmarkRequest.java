package com.pblgllgs.spring.dto;
/*
 *
 * @author pblgl
 * Created on 14-11-2023
 *
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBookmarkRequest {
    @NotBlank(message = "Title should not be empty")
    private String title;
    @NotBlank(message = "Url should not be empty")
    private String url;
}
