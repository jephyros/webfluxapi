package kr.chis.webfluxapi.book.entity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author InSeok
 * Date : 2020-10-19
 * Remark :
 */
public interface BookRepository extends ReactiveMongoRepository<Book,String> {

    Flux<Book> findByBookId(String bookId);
}
