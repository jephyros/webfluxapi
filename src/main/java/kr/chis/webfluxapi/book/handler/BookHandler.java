package kr.chis.webfluxapi.book.handler;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class BookHandler {
    private final BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public Mono<ServerResponse> bookAllList(ServerRequest request){

        Flux<Book> books = bookService.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(books,Book.class);

    }

    public Mono<ServerResponse> bookFindbyId(ServerRequest request)  {

        Mono<String> id = Mono.just(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bookService.findById(id),Book.class);

        // 2안. 핸들러에서 notFound 보내기
        //        ServerResponse notFound = ServerResponse.status(HttpStatus.NOT_FOUND).build().block();
        //        return bookService.findById(id)
        //                .flatMap(book -> ServerResponse.ok()
        //                                    .contentType(MediaType.APPLICATION_JSON)
        //                                    .body(BodyInserters.fromValue(book)))
        //                .defaultIfEmpty(Objects.requireNonNull(notFound));


    }

    public Mono<ServerResponse> bookSave(ServerRequest request) {

        Mono<Book> book = request.bodyToMono(Book.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book.flatMap(bookService::save),Book.class);

    }

    public Mono<ServerResponse> bookUpdate(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Book> book = request.bodyToMono(Book.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book.flatMap(v-> bookService.update(id,v)),Book.class);

    }

    public Mono<ServerResponse> bookDeleteById(ServerRequest request)  {

        String id = request.pathVariable("id");
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//                .body(bookService.findById(id),Book.class)
//                .defaultIfEmpty(Objects.requireNonNull(ServerResponse.status(HttpStatus.NOT_FOUND).build().block()));
        //

        return bookService.deleteById(id).log()
                .flatMap(book -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .build());




    }

}
