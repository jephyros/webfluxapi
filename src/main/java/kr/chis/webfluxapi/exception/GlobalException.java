package kr.chis.webfluxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author InSeok
 * Date : 2020-10-29
 * Remark :
 */
public class GlobalException  extends ResponseStatusException {
    private String code;
    public GlobalException(HttpStatus status) {
        super(status);
    }

    public GlobalException(HttpStatus status,String code, String reason) {
        super(status, reason);
        this.code = code;
    }

    public GlobalException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public String getCode() {
        return code;
    }
}
