package kr.chis.webfluxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author InSeok
 * Date : 2020-10-29
 * Remark :
 */
public class GlobalException  extends ResponseStatusException {
    public GlobalException(HttpStatus status) {
        super(status);
    }

    public GlobalException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public GlobalException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
