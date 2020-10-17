package kr.chis.webfluxapi.router;

import kr.chis.webfluxapi.book.handler.BookHandler;
import kr.chis.webfluxapi.exception.BookException;
import kr.chis.webfluxapi.exception.ErrorMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {
    @Bean
    public RouterFunction<ServerResponse> routers(BookHandler handler) {
        return nest(path("/api/v1/books"),
                //route(GET("/"),handler::bookAllList)
                route()
                        .GET("/",accept(MediaType.APPLICATION_JSON),handler::bookAllList)
                        .GET("/test",accept(MediaType.APPLICATION_JSON),handler::bookFindbyId)
                        .onError(BookException.class, this::bookException)
                    .build()

                );
    }

    //BookException 처리
    private Mono<ServerResponse> bookException(BookException e, ServerRequest request){
        ErrorMessage em = new ErrorMessage();
        em.setErrorCode(e.getErrorCode());
        em.setErrorMessage(e.getMessage());
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Mono.just(em),ErrorMessage.class);
    }
}
