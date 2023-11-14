package com.pblgllgs.spring.serivice;
/*
 *
 * @author pblgl
 * Created on 13-11-2023
 *
 */

import com.pblgllgs.spring.domain.Bookmark;
import com.pblgllgs.spring.domain.BookmarkMapper;
import com.pblgllgs.spring.dto.BookmarkDTO;
import com.pblgllgs.spring.dto.BookmarksDTO;
import com.pblgllgs.spring.dto.CreateBookmarkRequest;
import com.pblgllgs.spring.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    @Transactional(readOnly = true)
    public BookmarksDTO findAllBookmarks(Integer page) {
        int pageNumber = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.Direction.ASC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = bookmarkRepository.findBookmarks(pageable);
        return new BookmarksDTO(bookmarkPage);
    }


    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(String query, Integer page) {
        int pageNumber = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.Direction.ASC, "createdAt");
//        Page<BookmarkDTO> bookmarkPage = bookmarkRepository.searchBookmarks(query,pageable);
        Page<BookmarkDTO> bookmarkPage = bookmarkRepository.findByTitleContainsIgnoreCase(query,pageable);
        return new BookmarksDTO(bookmarkPage);
    }

    public BookmarkDTO save(CreateBookmarkRequest request) {
        Bookmark bookmark = new Bookmark(null,request.getTitle(),request.getUrl(), Instant.now());
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return bookmarkMapper.bookmarkToDTO(savedBookmark);
    }
}
