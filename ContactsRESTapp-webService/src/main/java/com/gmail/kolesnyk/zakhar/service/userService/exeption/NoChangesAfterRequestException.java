package com.gmail.kolesnyk.zakhar.service.userService.exeption;

/**
 * The {@code NoChangesAfterRequestException} exception class used for informing of cause
 * that has not changed persistence in system after request
 *
 * @author Kolesnyk Zakhar
 * @since JDK1.8
 */
public class NoChangesAfterRequestException extends RuntimeException {
    public NoChangesAfterRequestException(String message) {
        super(message);
    }
}
