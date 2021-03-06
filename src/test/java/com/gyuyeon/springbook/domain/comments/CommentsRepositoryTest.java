package com.gyuyeon.springbook.domain.comments;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.repository.PostsRepository;
import com.gyuyeon.springbook.repository.CommentsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    CommentsRepository commentsRepository;

    Posts posts;

    @BeforeEach
    void setUp() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        posts = Posts.builder()
                .title(title)
                .content(content)
                .author("test@gmail.com")
                .category(Category.일기)
                .build();

        postsRepository.save(posts);
    }

    @AfterEach
    void tearDown() {
        commentsRepository.deleteAll();
    }

    @Test
    @DisplayName("Post와 Comment 연결 확인")
    void test1() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글")
                .build();

        //when
        Comments savedComment = commentsRepository.save(comments);

        //then
        Assertions.assertThat(savedComment.getPosts()).isEqualTo(posts);
    }

    @Test
    @DisplayName("댓글 생성 성공")
    void test2() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글")
                .build();

        //when
        Comments savedComment = commentsRepository.save(comments);

        //then
        Assertions.assertThat(savedComment).isEqualTo(comments);
    }

    @Test
    @DisplayName("댓글 수정 성공")
    void test3() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글")
                .build();

        Comments savedComment = commentsRepository.save(comments);

        //when
        String content = "수정된 댓글";
        comments.setContent(content);

        //then
        Assertions.assertThat(savedComment.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("댓글 삭제 성공")
    void test4() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글")
                .build();

        Comments savedComment = commentsRepository.save(comments);
        Long id = savedComment.getId();

        //when
        commentsRepository.delete(comments);
        Comments deletedComments = commentsRepository.findById(id).orElse(null);

        //then
        Assertions.assertThat(deletedComments).isNull();
    }
}
