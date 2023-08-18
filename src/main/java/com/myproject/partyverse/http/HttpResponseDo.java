package com.myproject.partyverse.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponseDo<T> {
    private Integer status;
    private String message;
    private T data;

    public static <T> HttpResponseDo<T> success(T data){
        return new HttpResponseDo<>(HttpStatus.OK.value(),"Success",data);
    }

    public static <T> HttpResponseDo<T> error(Integer status, String message){
        return new HttpResponseDo<>(status, message, null);
    }
}
