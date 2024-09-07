package com.clipboar.util.aes.exception;

public class AesDecryptException extends RuntimeException{

    public AesDecryptException(String message) {
        super(message);
    }

    public AesDecryptException(String message, Throwable cause) {
        super(message, cause);
    }

}
