package ubb.postuniv.riddingaddict.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ShopException.class})
    public ResponseEntity<Object> handleGarageSaleException(ShopException e) {

        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        log.error("exception = {}", apiException.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ItemNotFoundException.class})
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException e) {

        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());

        log.error("exception = {}", apiException.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<Object> handleException(Exception e) {
//
//        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
//
//        log.error("exception = {}", apiException.getMessage());
//
//        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
//    }
}
