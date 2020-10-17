package kr.chis.webfluxapi.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage {
    private String errorCode;
    private String errorMessage;

}
