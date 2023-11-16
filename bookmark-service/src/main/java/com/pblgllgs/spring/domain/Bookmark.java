package com.pblgllgs.spring.domain;
/*
 *
 * @author pblgl
 * Created on 13-11-2023
 *
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "bookmarks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bookmark_id_seq")
    @SequenceGenerator(name = "bookmark_id_seq", sequenceName = "bookmark_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
