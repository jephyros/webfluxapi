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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

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
        //데이터 모두 삭
        bookService.deleteAll().subscribe();
        //3개 데이터 저장
        createBookThree();
    }


    @Test
    @DisplayName("Book 을 저장한다.")
    void bookSave(){
        //given
        Book b1 = new Book("IS-004", "죄와벌4");

        //when
        Mono<Book> savebook = bookService.save(b1);

        //then
            //저장
        StepVerifier.create(savebook)
                .expectSubscription()
                .assertNext(book -> { // DB에서 저장 조회 확인 ?
                    assertThat(book.getBookName()).as("기대값:" + b1.getBookName()).isEqualTo(b1.getBookName());
                    assertThat(book.getBookId()).as("기대값:" + b1.getBookId()).isEqualTo(b1.getBookId());
                } ).verifyComplete();
            //총데이터수 4개 확인
        StepVerifier.create(bookRepository.findAll())
                .expectSubscription()
                .expectNextCount(4)
                .verifyComplete();

//        bookRepository.findAll()
//                .doOnNext(book -> System.out.println(book.getBookName())).blockLast();



    }

    @Test
    @DisplayName("Book 을 삭제한다.")
    void bookDeleteByID(){
        //given

        //when
        Flux<Void> delBook = bookRepository.findByBookId("IS-002")
                .flatMap(book -> bookService.deleteById(book.getId()));


        //then
            //삭제
        StepVerifier.create(delBook)
                .expectSubscription()
                .verifyComplete();
            //삭제후 라인수확인
        StepVerifier.create(bookRepository.findAll())
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();

    }


    private void createBookThree(){
        Book b1 = new Book("IS-001", "죄와벌1");
        Book b2 = new Book("IS-002", "죄와벌2");
        Book b3 = new Book("IS-003", "죄와벌3");
        bookRepository.saveAll(Arrays.asList(b1, b2, b3)).subscribe();
    }
}
