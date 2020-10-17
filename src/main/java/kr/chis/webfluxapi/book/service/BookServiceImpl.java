package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.domain.Book;
import kr.chis.webfluxapi.exception.BookException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class BookServiceImpl implements BookService{
    public Mono<List<Book>> findAll() {

        Book b1 = new Book(1L, "죄와벌");
        Book b2 = new Book(1L, "철학의 숲");
        Book b3 = new Book(1L, "Clean Code");
        List<Book> books = Arrays.asList(b1, b2, b3);

        if (1==1) return Mono.error(new BookException("S001","리스트 호출 서비 오류"));

        return Mono.just(books);
    }

    @Override
    public Mono<Book> findById() {
        Book b1 = new Book(1L, "죄와벌");
        return  Mono.just(b1);
    }
}
