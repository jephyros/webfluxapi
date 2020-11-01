package kr.chis.webfluxapi.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @author InSeok
 * Date : 2020-10-29
 * Remark :
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        if(getError(request) instanceof  GlobalException){
            GlobalException ex = (GlobalException) getError(request);
            map.put("exception", ex.getClass().getSimpleName());
            //map.put("message", ex.getReason());
            map.put("message", ex.getMessage());
            map.put("status", ex.getStatus().value());
            map.put("code", ex.getCode());
            map.put("error", ex.getStatus().getReasonPhrase());
            return map;
        }
        map.put("exception", "SystemException");
        map.put("message", "System Error , Check logs!");
        map.put("status", "500");
        map.put("error", " System Error ");
        return map;

    }
}
