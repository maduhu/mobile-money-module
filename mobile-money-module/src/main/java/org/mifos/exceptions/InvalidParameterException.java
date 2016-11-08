package org.mifos.exceptions;

/**
 * Created by daniel on 11/8/16.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(String string){
        super("Invalid parameter provided: " + string);
    }
}
