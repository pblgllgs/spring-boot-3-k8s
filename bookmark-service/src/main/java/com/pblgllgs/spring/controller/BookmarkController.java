package com.pblgllgs.spring.controller;
/*
 *
 * @author pblgl
 * Created on 13-11-2023
 *
 */

import com.pblgllgs.spring.dto.BookmarksDTO;
import com.pblgllgs.spring.serivice.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<BookmarksDTO> findAllBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return new ResponseEntity<>(bookmarkService.findAllBookmarks(page), HttpStatus.OK);
    }
}
