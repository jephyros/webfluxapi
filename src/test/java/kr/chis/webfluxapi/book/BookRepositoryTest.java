package kr.chis.webfluxapi.book;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.entity.BookRepository;
import kr.chis.webfluxapi.book.handler.BookErrorCode;
import kr.chis.webfluxapi.book.service.BookService;
import kr.chis.webfluxapi.book.service.BookServiceImpl;
import kr.chis.webfluxapi.exception.GlobalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataMongoTest
//@SpringBootTest
@DisplayName("Book 엔터티테스트")
public class BookRepositoryTest {

    Book b1 = new Book("IS-001", "죄와벌1");
    Book b2 = new Book("IS-002", "죄와벌2");
    Book b3 = new Book("IS-003", "죄와벌3");

    @Autowired
    BookRepository bookRepository;

    BookService bookService;

    @BeforeEach
    void before() {
        this.bookService = new BookServiceImpl(bookRepository);
        //데이터 모두 삭제
        bookRepository.deleteAll().block();
        //3개 테스트 데이터 저장
        createBookThree();
    }

    // 참고 https://github.com/jephyros/Teach-ReactiveSpring/blob/master/learn-reactivespring/src/test/java/com/learnreactivespring/repository/ItemReactiveRepositorytest.java

    @Test
    @DisplayName("Book 을 저장한다.")
    void bookSave() {
        //given
        Book b1 = new Book("IS-004", "죄와벌4");

        //when
        Mono<Book> savebook = bookService.save(b1);

        //then
        //저장
        StepVerifier.create(savebook)
                .expectSubscription()
                .assertNext(book -> {
                    assertThat(book.getBookName()).as("기대값:" + b1.getBookName()).isEqualTo(b1.getBookName());
                    assertThat(book.getBookId()).as("기대값:" + b1.getBookId()).isEqualTo(b1.getBookId());
                }).verifyComplete();
        //저장 후 총데이터수 4개 확인
        StepVerifier.create(bookRepository.findAll())
                .expectSubscription()
                .expectNextCount(4)
                .verifyComplete();


    }


    @Test
    @DisplayName("Book 을 모두 조회한다.")
    void bookDeleteAll() {
        //given
        //when then
        StepVerifier.create(bookRepository.findAll())
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    @DisplayName("Book 을 BookId로 조회한다.")
    void bookFindByBookId() {
        //given
        //when then
        StepVerifier.create(bookRepository.findByBookId(b2.getBookId()))
                .expectSubscription()
                .assertNext(book -> {
                    assertThat(book.getBookId()).as("기대값 :" + b2.getBookId()).isEqualTo(b2.getBookId());
                    assertThat(book.getBookName()).as("기대값 :" + b2.getBookName()).isEqualTo(b2.getBookName());
                })
                .expectNextCount(0) // 1개조회했기때문에 0이여야한다.
                .verifyComplete();

    }

    @Test
    @DisplayName("Book 을 삭제한다.")
    void bookDeleteByID() {
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

    @Test
    @DisplayName("DB에 없는값을 삭제시 예외를 발생시킨다.")
    void bookDeleteByIDException() {
        //give

        //when then
        StepVerifier.create(bookService.deleteById("xxxxxx"))
                .expectSubscription()
                .verifyErrorSatisfies(e->{
                    e.getMessage().contains(BookErrorCode.B404001.getDesc());
                });
                //.verifyErrorMessage(BookErrorCode.B404001.getDesc());
                //.verifyError(GlobalException.class);

    }

    @Test
    @DisplayName("DB에 없는값을 조회시 예외를 발생시킨다.")
    void bookFindByIDException() {
        //give

        //when then
        StepVerifier.create(bookService.findById(Mono.just("xxxxxx")))
                .expectSubscription()
                .verifyErrorSatisfies(e->{
                    e.getMessage().contains(BookErrorCode.B404001.getDesc());
                });
        //.verifyErrorMessage(BookErrorCode.B404001.getDesc());
        //.verifyError(GlobalException.class);

    }
    // todo update 정상, 익셉션 테스트


    private void createBookThree() {

        bookRepository.saveAll(Arrays.asList(b1, b2, b3)).blockLast();
    }
}
