package com.fds.fooddeliverysystem.exception;

import org.springframework.http.HttpStatus;

public class RestaurantException extends RuntimeException{

    private static final long serialVersionUID = 2457189159617736175L;

    private final HttpStatus status;
    private String message;
    private String code;



    /**
     * Arg constructor.
     *
     * @param ex the {@link Throwable}
     * @param message the {@link String} message to set.
     * @param message the {@link String} code to set.
     * @param status the {@link HttpStatus} to set.
     */
    public RestaurantException(final Throwable ex, final String message,final String code, final HttpStatus status) {
        super(message, ex);
        this.status = status;
        this.message = message;
        this.code = null;
    }

    /**
     * Arg constructor.
     *
     * @param ex the {@link Throwable}
     * @param message the {@link String} message to set.
     */

    public RestaurantException(final Throwable ex, final String message) {
        super(message, ex);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
        this.code = null;
    }

    /**
     * Arg constructor.
     * @param message
     * @param status
     */
    public RestaurantException(final String message,final HttpStatus status){

        super(message);
        this.status = status;
        this.message = message;
    }


    /**
     * Getter for message.
     *
     * @return the {@link String} message set.
     */
    @Override
    public String getMessage() {
        return this.message;
    }

}
