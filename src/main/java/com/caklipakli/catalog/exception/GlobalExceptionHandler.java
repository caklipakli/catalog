package com.caklipakli.catalog.exception;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;


public class GlobalExceptionHandler implements CommandLine.IExecutionExceptionHandler {

    @Getter
    @Setter
    private AppException appException;

    @Override
    public int handleExecutionException(Exception e, CommandLine commandLine, CommandLine.ParseResult parseResult) throws Exception {

        appException.setMessage(e.getMessage());
        appException.setCause(e.getCause().toString());
        return 500;
    }
}
