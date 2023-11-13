package com.pblgllgs.spring.dto;
/*
 *
 * @author pblgl
 * Created on 13-11-2023
 *
 */

import com.pblgllgs.spring.domain.Bookmark;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkDTO toDto(Bookmark bookmark){
        BookmarkDTO bookmarkDTO = new BookmarkDTO();
        BeanUtils.copyProperties(bookmark,bookmarkDTO);
        return bookmarkDTO;
    }
}
