package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.domain.Book;
import kr.chis.webfluxapi.exception.BookException;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookService {
    Mono<List<Book>> findAll();
    Mono<Book> findById();
}
