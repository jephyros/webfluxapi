package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.entity.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
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

    public Flux<Book> findAll() {



        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> findById(String id) {

        return  bookRepository.findById(id);
    }

    @Override
    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }
}
