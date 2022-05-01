package dk.game.aop;


import dk.game.dto.ApiError;
import dk.game.dto.ParameterError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        ApiError error = new ApiError();
        error.setErrors(fieldErrors);
        error.setMessage(ex.getMessage());


        return ResponseEntity.badRequest().body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError err = new ApiError();
        ParameterError paramError = new ParameterError(ex.getParameterName(), ex.getMessage());
        err.setParamErrors(List.of(paramError));
        return ResponseEntity.badRequest().body(err);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        List<ParameterError> collect = ex.getConstraintViolations().stream().map(
                cv -> new ParameterError(cv.getPropertyPath().toString(), cv.getMessage())).collect(Collectors.toList());
        ApiError err = new ApiError();
        err.setParamErrors(collect);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
}