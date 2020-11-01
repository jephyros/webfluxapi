package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.entity.BookRepository;
import kr.chis.webfluxapi.book.handler.BookErrorCode;
import kr.chis.webfluxapi.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> findAll() {


        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> findById(Mono<String> id) {


        return bookRepository.findById(id)
                .switchIfEmpty(Mono.just(new Book()).handle((v, s) -> s.error(new GlobalException(HttpStatus.NOT_FOUND, BookErrorCode.B404001.getCode(), BookErrorCode.B404001.getDesc()))));
    }

    @Override
    public Mono<Book> save(Book book) {
        if (StringUtils.isEmpty(book.getBookId()) || StringUtils.isEmpty(book.getBookName())) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, BookErrorCode.B400001.getCode(), BookErrorCode.B400001.getDesc());
        }
        book.setInsertTime(LocalDateTime.now());
        book.setModifyTime(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public Mono<Book> update(String id, Book book) {
        if (StringUtils.isEmpty(book.getBookId()) || StringUtils.isEmpty(book.getBookName())) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, BookErrorCode.B400001.getCode(), BookErrorCode.B400001.getDesc());
        }

        return bookRepository.findById(id)
                .flatMap(bookSave -> {
                    bookSave.setBookId(book.getBookId());
                    bookSave.setBookName(book.getBookName());
                    bookSave.setModifyTime(LocalDateTime.now());
                    return bookRepository.save(bookSave);
                });
    }


    @Override
    public Mono<Void> deleteAll() {
        return bookRepository.deleteAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        //System.out.println("==========="+book.getBookName());
        return bookRepository.findById(id)
                //삭제시 DB 에 값이없으면 Exception 발생
                .switchIfEmpty(Mono.just(new Book()).handle((v, s) -> s.error(new GlobalException(HttpStatus.NOT_FOUND, BookErrorCode.B404001.getCode(), BookErrorCode.B404001.getDesc()))))
                .flatMap(bookRepository::delete);

    }

    @Override
    public Flux<Book> findByBookId(String bookId) {
        return bookRepository.findByBookId(bookId);
    }

}
