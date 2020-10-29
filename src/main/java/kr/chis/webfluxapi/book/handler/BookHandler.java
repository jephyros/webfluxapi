package kr.chis.webfluxapi.book.handler;

import kr.chis.webfluxapi.book.entity.Book;
import kr.chis.webfluxapi.book.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

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


    }

    public Mono<ServerResponse> bookSave(ServerRequest request) {

        Mono<Book> book = request.bodyToMono(Book.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book.flatMap(bookService::save),Book.class);

    }
}
