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

import java.util.Optional;

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
    public Mono<Book> findById(Mono<String> id) {


        return  bookRepository.findById(id);
    }

    @Override
    public Mono<Book> save(Book book) {
        if(StringUtils.isEmpty(book.getBookId()) || StringUtils.isEmpty(book.getBookName())){
            throw new GlobalException(HttpStatus.BAD_REQUEST, BookErrorCode.B404001.getCode(),BookErrorCode.B404001.getDesc());
        }
        return bookRepository.save(book);
    }

    @Override
    public Mono<Void> deleteAll(){
        return bookRepository.deleteAll();
    }

    @Override
    public Mono<Void> deleteById(String id){
        return bookRepository.deleteById(id);
    }

    @Override
    public Flux<Book> findByBookId(String bookId) {
        return bookRepository.findByBookId(bookId);
    }

}
