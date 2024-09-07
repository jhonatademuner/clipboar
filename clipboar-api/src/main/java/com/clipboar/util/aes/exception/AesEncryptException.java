package com.clipboar.util.aes.exception;

public class AesEncryptException extends RuntimeException{

    public AesEncryptException(String message) {
        super(message);
    }

    public AesEncryptException(String message, Throwable cause) {
        super(message, cause);
    }
}
