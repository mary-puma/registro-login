package org.api.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ApiErrorResponse<T> {

    private HttpStatus status;
    private T errors;

}
