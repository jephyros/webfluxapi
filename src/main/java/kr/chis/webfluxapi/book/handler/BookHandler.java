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
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class BookHandler {
    private final BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public Mono<ServerResponse> bookAllList(ServerRequest request)  {
        //if (1==1) throw new BookException("B001","리스트 호출 오류");
        Flux<Book> books = bookService.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(books,Book.class);

    }

    public Mono<ServerResponse> bookFindbyId(ServerRequest request)  {
        //if (1==1) throw new BookException("B001","리스트 호출 오류");

//        return bookService.findById()
//                .flatMap(v -> ok().body(Mono.just(v), Book.class));
        Mono<String> id = Mono.just(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bookService.findById(id),Book.class);


//        public Mono<ServerResponse> getUser(ServerRequest request) {
//            final int id = Integer.parseInt(request.pathVariable("id"));
//            final Mono<User> user = userRepository.findById(id);
//            return user.flatMap(usr -> ok().contentType(APPLICATION_JSON)
//                    .body(fromPublisher(user, User.class)))
//                    .switchIfEmpty(notFound().build());
//        }
    }

    public Mono<ServerResponse> bookSave(ServerRequest request) {

        Mono<Book> book = request.bodyToMono(Book.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(book.flatMap(bookService::save),Book.class));

//        return request.bodyToMono(Book.class)
//                .flatMap(bookService::save)
//                .flatMap(v-> ok().body(Mono.just(v),Book.class));


    }
}
