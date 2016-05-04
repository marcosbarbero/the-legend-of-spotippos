package org.github.barbero.spotippos.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.Errors;

/**
 * Exception for requests with invalid data.
 *
 * @author Marcos Barbero
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(Errors errors) {
        super("Invalid Request");
        this.errors = errors;
    }
}
