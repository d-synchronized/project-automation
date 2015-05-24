package uk.co.techblue.automation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class AutomationExecutionException.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AutomationExecutionException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4431045142317408808L;

    /** The error code. */
    private String errorCode;

    /**
     * Instantiates a new automation execution exception.
     * 
     * @param errorCode the error code
     * @param message the message
     */
    public AutomationExecutionException(final String errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new automation execution exception.
     * 
     * @param errorCode the error code
     * @param exception the exception
     */
    public AutomationExecutionException(final String errorCode, final Exception exception) {
        super(exception);
        this.errorCode = errorCode;
    }

}
