package com.pblgllgs.spring.controller;

import com.pblgllgs.spring.domain.Bookmark;
import com.pblgllgs.spring.repository.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///test"
        }
)
class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        List<Bookmark> bookmarks = new ArrayList<>();

        bookmarks.add(new Bookmark(null, "Spring Framework", "https://spring.io/", Instant.now()));
        bookmarks.add(new Bookmark(null, "React Documentation", "https://reactjs.org/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Java Documentation", "https://docs.oracle.com/en/java/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Docker Hub", "https://hub.docker.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Kubernetes Documentation", "https://kubernetes.io/docs/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Jenkins Official Website", "https://www.jenkins.io/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Git Documentation", "https://git-scm.com/doc", Instant.now()));
        bookmarks.add(new Bookmark(null, "Node.js Documentation", "https://nodejs.org/en/docs/", Instant.now()));
        bookmarks.add(new Bookmark(null, "PostgreSQL Documentation", "https://www.postgresql.org/docs/", Instant.now()));
        bookmarks.add(new Bookmark(null, "AWS Documentation", "https://docs.aws.amazon.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Angular Documentation", "https://angular.io/docs", Instant.now()));
        bookmarks.add(new Bookmark(null, "Python Official Documentation", "https://docs.python.org/3/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Azure Documentation", "https://docs.microsoft.com/en-us/azure/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Vue.js Documentation", "https://vuejs.org/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Elasticsearch Documentation", "https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html", Instant.now()));
        bookmarks.add(new Bookmark(null, "Flutter Documentation", "https://flutter.dev/docs", Instant.now()));
        bookmarks.add(new Bookmark(null, "GraphQL Documentation", "https://graphql.org/learn/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Rust Documentation", "https://www.rust-lang.org/learn", Instant.now()));
        bookmarks.add(new Bookmark(null, "Terraform Documentation", "https://www.terraform.io/docs/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Express.js Documentation", "https://expressjs.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Ansible Documentation", "https://docs.ansible.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Swift Documentation", "https://docs.swift.org/swift-book/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Ruby on Rails Guides", "https://guides.rubyonrails.org/", Instant.now()));
        bookmarks.add(new Bookmark(null, "TensorFlow Documentation", "https://www.tensorflow.org/guide", Instant.now()));
        bookmarks.add(new Bookmark(null, "Go Documentation", "https://golang.org/doc/", Instant.now()));
        bookmarks.add(new Bookmark(null, "MongoDB Documentation", "https://docs.mongodb.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Apache Kafka Documentation", "https://kafka.apache.org/documentation/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Redux Documentation", "https://redux.js.org/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Chef Documentation", "https://docs.chef.io/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Scrum Guide", "https://www.scrumguides.org/scrum-guide.html", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "1,30,3,1,true,false,true,false",
                    "2,30,3,2,false,false,true,true",
                    "3,30,3,3,false,true,false,true",
            }
    )
    void givenParams_whenGetAllBookmarks_thenReturnListOfBookmarks(
            int pageNo,
            int totalElements,
            int totalPages,
            int currentPage,
            boolean isFirst,
            boolean isLast,
            boolean hasNext,
            boolean hasPrevious
    ) throws Exception {
        // when - action or the behaviour that we are going the
        ResultActions response = mockMvc.perform(get("/api/v1/bookmarks?page=" + pageNo));
        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", equalTo(hasPrevious)));
    }

    @Test
    void givenObjectCreateBookmarkRequest_whenCreateBookmark_thenReturnObjectBookmarkDTO() throws Exception {
        //given -  precondition or setup
        String body = """
                {
                    "title": "Nestjs",
                    "url": "https://nestjs.com/"
                }
                """;
        // when - action or the behaviour that we are going the
        ResultActions response = this.mockMvc.perform(
                post("/api/v1/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );
        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",notNullValue()))
                .andExpect(jsonPath("$.title", is("Nestjs")))
                .andExpect(jsonPath("$.url", is("https://nestjs.com/")));
    }

    @Test
    void givenIncompleteObjectCreateBookmarkRequest_whenCreateBookmark_thenReturnObjectWithErrorMessage() throws Exception {
        //given -  precondition or setup
        String body = """
                {
                    "url": "https://nestjs.com/"
                }
                """;
        // when - action or the behaviour that we are going the
        ResultActions response = this.mockMvc.perform(
                post("/api/v1/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );
        // then
        response.andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type",is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("title")))
                .andExpect(jsonPath("$.violations[0].message", is("Title should not be empty")))
                .andReturn();
    }

}