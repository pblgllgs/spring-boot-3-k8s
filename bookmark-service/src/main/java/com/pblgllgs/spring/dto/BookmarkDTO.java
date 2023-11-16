package com.pblgllgs.spring.dto;
/*
 *
 * @author pblgl
 * Created on 13-11-2023
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
public class BookmarkDTO {

    private Long id;
    private String title;
    private String url;
    private Instant createdAt;
}
