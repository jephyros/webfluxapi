package kr.chis.webfluxapi.book;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.entity.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayName("Book 라우터 테스트")
public class BookRouterTest {
    private static final String TESTURL = "/api/v1/books";

    @Autowired
    private WebTestClient webClient;

    @Autowired
    BookRepository bookRepository;


    @BeforeEach
    void before(){
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName(TESTURL + " Get 테스트")
    public void booksGet(){
        //given
        Book b1 = new Book("IS-001", "죄와벌1");
        Book b2 = new Book("IS-002", "죄와벌2");
        Book b3 = new Book("IS-003", "죄와벌3");
        bookRepository.saveAll(Arrays.asList(b1, b2, b3)).subscribe();


        //whenthen
        webClient.get().uri(TESTURL)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(3);

    }
    //todo 개별조회,삭제,저장 테스트


}
