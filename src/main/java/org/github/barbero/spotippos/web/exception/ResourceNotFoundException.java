package org.github.barbero.spotippos.web.exception;

/**
 * @author Marcos Barbero
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super(String.format("The resource with id '%s' was not found.", id.toString()));
    }

    public ResourceNotFoundException() {
        super("The resource was not found.");
    }
}
