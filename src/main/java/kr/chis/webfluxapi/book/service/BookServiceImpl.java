package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.domain.Book;
import kr.chis.webfluxapi.book.domain.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<List<Book>> findAll() {

        Book b1 = new Book("1L", "죄와벌");
        Book b2 = new Book("2L", "철학의 숲");
        Book b3 = new Book("2L", "Clean Code");
        List<Book> books = Arrays.asList(b1, b2, b3);

        bookRepository.insert(b1).subscribe(System.out::println);
        log.info("=======저장 Test");
//        bookRepository.save(b2);
//        bookRepository.save(b3);
        //if (1==1) return Mono.error(new BookException("S001","리스트 호출 서비 오류"));

        return Mono.just(books);
    }

    @Override
    public Mono<Book> findById() {
        Book b1 = new Book("1L", "죄와벌");
        return  Mono.just(b1);
    }
}
