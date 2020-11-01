package kr.chis.webfluxapi.router;

import kr.chis.webfluxapi.book.handler.BookHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class BookRouter {
    @Bean
    public RouterFunction<ServerResponse> routers(BookHandler handler) {
        return nest(path("/api/v1/books"),
                route()
                        .GET("/",accept(MediaType.APPLICATION_JSON),handler::bookAllList)
                        .GET("/{id}",accept(MediaType.APPLICATION_JSON),handler::bookFindbyId)
                        .DELETE("/{id}",accept(MediaType.APPLICATION_JSON),handler::bookDeleteById)
                        .POST("/",accept(MediaType.APPLICATION_JSON),handler::bookSave)
                    .build()
                // todo 업데이트
                );
    }


}
