package org.demo.app.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class AppResponse<T> {
    private T data;
    private Date responseDate;
    private Integer statusCode;
    private String message;
    private String errorCode;

    public static <T> AppResponse<T> ok(T data) {
        return new AppResponse<>(data, new Date(), HttpStatus.OK.value(), null, null);
    }

    public static <T> AppResponse<T> created(T data) {
        return new AppResponse<>(data, new Date(), HttpStatus.CREATED.value(), null, null);
    }

    public static <T> AppResponse<T> noContent() {
        return new AppResponse<>(null, new Date(), HttpStatus.NO_CONTENT.value(), null, null);
    }

    public static <T> AppResponse<T> status(Integer statusCode, String message) {
        return new AppResponse<>(null, new Date(), statusCode, message, null);
    }


}
