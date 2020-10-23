package kr.chis.webfluxapi.book;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.entity.BookRepository;
import kr.chis.webfluxapi.book.service.BookService;
import kr.chis.webfluxapi.book.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@DataMongoTest
@DisplayName("Book 엔터티테스트")
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;


    BookService bookService;

    @BeforeEach
    void before(){
        this.bookService = new BookServiceImpl(bookRepository);
    }


    @Test
    @DisplayName("Book 을 저장한다.")
    void bookSave(){
        Book b1 = new Book("IS-001", "죄와벌");

        Disposable subscribe = bookService.save(b1).subscribe();
        bookRepository.findByBookId("IS-001")

                .flatMap(e-> {
                    System.out.println(e.getBookName());
                    return Mono.just(e);
                    }
                ).subscribe();




    }

}
