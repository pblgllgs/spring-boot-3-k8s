package com.pblgllgs.spring.domain;
/*
 *
 * @author pblgl
 * Created on 14-11-2023
 *
 */

import com.pblgllgs.spring.dto.BookmarkDTO;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkDTO bookmarkToDTO(Bookmark bookmark){
        return new BookmarkDTO(
          bookmark.getId(),
          bookmark.getTitle(),
          bookmark.getUrl(),
          bookmark.getCreatedAt()
        );
    }
}
