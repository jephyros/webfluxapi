package kr.chis.webfluxapi;

import kr.chis.webfluxapi.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class WebfluxapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxapiApplication.class, args);
    }



}
