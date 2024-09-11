package com.clipboar.domain.clipboard.exception;

public class ClipboardNotFoundException extends RuntimeException{

    public ClipboardNotFoundException(){};

    public ClipboardNotFoundException(String message) {
        super(message);
    }

    public ClipboardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
