package ubb.postuniv.riddingaddict.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ShopException extends RuntimeException {

    public ShopException(String message) {

        super(message);
    }
}
