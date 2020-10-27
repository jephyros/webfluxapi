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
//@SpringBootTest
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

        Disposable subscribe = bookService.save(b1)
                .doOnSubscribe(v-> System.out.println("Save Subscribe==="))
                .doOnNext(book->{
                    System.out.println("Book Save : " + book.getBookName());
                })
//                .doOnNext(book->{
//
//                    bookRepository.findByBookId(book.getBookId())
//                    .doOnNext(book1 ->{
//                        System.out.println("Book Find" + book1.getBookName());
//                    }).subscribe();
//                })
                .subscribe();

        bookRepository.findByBookId("IS-001")
                .doOnSubscribe(v-> System.out.println("FindByBookID Subscribe==="))
                .doOnNext(v->{
                    System.out.println("Book Find : " + v.getBookName());
                }).subscribe();


    }

}
