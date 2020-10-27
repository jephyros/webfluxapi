package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookService {
    Flux<Book> findAll();
    Mono<Book> findById(Mono<String> id);
    Mono<Book> save(Book book);
}
