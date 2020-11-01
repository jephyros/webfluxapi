package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookService {
    Flux<Book> findAll();
    Mono<Book> findById(Mono<String> id);
    Mono<Book> save(Book book);
    Mono<Book> update(String id,Book book);
    Mono<Void> deleteAll();
    Mono<Void> deleteById(String id);
    Flux<Book> findByBookId(String bookId);
}
