package kr.chis.webfluxapi.router;

import kr.chis.webfluxapi.book.domain.Book;
import kr.chis.webfluxapi.book.service.BookService;
import kr.chis.webfluxapi.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class BookRestcontroller {

    @Autowired
    BookService bookService;

    @GetMapping("/err")
    public Mono<ResponseEntity> err() throws BookException {
        if (1==1) throw new BookException("B001","리스트 호출 오류");

        return bookService.findAll()
                .flatMap(v -> Mono.just(ResponseEntity.ok().body(v)));
    }

}
