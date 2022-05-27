package br.com.walteann.productapi.configs.exception;

import lombok.Data;

@Data
public class ExceptionDetails {

    private int status;
    private String message;

}
