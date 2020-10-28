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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataMongoTest
//@SpringBootTest
@DisplayName("Book 엔터티테스트")
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    BookService bookService;

    @BeforeEach
    void before(){
        this.bookService = new BookServiceImpl(bookRepository);
        bookService.deleteAll();
    }


    @Test
    @DisplayName("Book 을 저장한다.")
    void bookSave(){
        //given
        Book b1 = new Book("IS-001", "죄와벌");

        //when
        Mono<Book> savebook = bookService.save(b1);

        //then

        StepVerifier.create(savebook)
                .assertNext(book ->{
                    assertThat(book.getBookName()).as("기대값:" + b1.getBookName()).isEqualTo(b1.getBookName());
                    assertThat(book.getBookId()).as("기대값:" + b1.getBookId()).isEqualTo(b1.getBookId());
                } ).verifyComplete();

    }

}
