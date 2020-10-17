package kr.chis.webfluxapi.book.handler;

import kr.chis.webfluxapi.book.domain.Book;
import kr.chis.webfluxapi.book.service.BookService;
import kr.chis.webfluxapi.exception.BookException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class BookHandler {
    private final BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public Mono<ServerResponse> bookAllList(ServerRequest request)  {
        //if (1==1) throw new BookException("B001","리스트 호출 오류");


        return bookService.findAll()
                .flatMap(v -> ok().body(Mono.just(v), Book.class));


    }

    public Mono<ServerResponse> bookFindbyId(ServerRequest request)  {
        //if (1==1) throw new BookException("B001","리스트 호출 오류");

        return bookService.findById()
                .flatMap(v -> ok().body(Mono.just(v), Book.class));

    }

}
