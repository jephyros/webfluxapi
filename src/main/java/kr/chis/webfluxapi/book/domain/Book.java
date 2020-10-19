package kr.chis.webfluxapi.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
@Getter
@Setter
@AllArgsConstructor
public class Book {
    private Long id;
    private String bookName;


}
