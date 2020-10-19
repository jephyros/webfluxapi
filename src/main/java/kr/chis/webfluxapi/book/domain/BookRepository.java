package kr.chis.webfluxapi.book.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author InSeok
 * Date : 2020-10-19
 * Remark :
 */
public interface BookRepository extends ReactiveMongoRepository<Book,String> {
}
