package kr.chis.webfluxapi.book.service;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.entity.BookRepository;
import kr.chis.webfluxapi.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

//        if (1==1){
//            throw new GlobalException(HttpStatus.BAD_REQUEST,"reqquest param book is error");
//        }
        return bookRepository.save(book);
    }

    @Override
    public Mono<Void> deleteAll(){
        return bookRepository.deleteAll();
    }
}
