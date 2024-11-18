package com.ecommerce.ecommerce.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private  T data;

    public static<T> GenericResponse<T> empty(){
        return success(null,"no data show");
    }

    public static<T> GenericResponse<T> success(T data,String message){
        return GenericResponse.<T>builder().message(message).data(data).success(true).build();
    }

    public static<T> GenericResponse<T> error(String message){
        return GenericResponse.<T>builder().message(message).success(false).build();
    }
}
