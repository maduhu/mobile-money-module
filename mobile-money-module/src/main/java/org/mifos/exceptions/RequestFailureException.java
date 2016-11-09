package org.mifos.exceptions;

/**
 * Created by daniel on 11/8/16.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestFailureException extends RuntimeException {
    public RequestFailureException(String error){
        super("Error making request to the mobile money API, error message: " + error);
    }
}
