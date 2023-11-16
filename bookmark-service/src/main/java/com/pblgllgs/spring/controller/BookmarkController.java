package com.pblgllgs.spring.controller;
/*
 *
 * @author pblgl
 * Created on 13-11-2023
 *
 */

import com.pblgllgs.spring.dto.BookmarkDTO;
import com.pblgllgs.spring.dto.BookmarksDTO;
import com.pblgllgs.spring.dto.CreateBookmarkRequest;
import com.pblgllgs.spring.serivice.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<BookmarksDTO> findAllBookmarks(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "query", defaultValue = "") String query
    ) {
        if (query == null || query.trim().isEmpty()) {
            return new ResponseEntity<>(bookmarkService.findAllBookmarks(page), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookmarkService.searchBookmarks(query, page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookmarkDTO> createBookmark(@Valid @RequestBody CreateBookmarkRequest request) {
        return new ResponseEntity<>(bookmarkService.save(request),HttpStatus.CREATED);
    }
}
