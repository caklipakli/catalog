package com.caklipakli.catalog.exception;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;
import picocli.CommandLine.IExecutionExceptionHandler;
import picocli.CommandLine.ParseResult;


public class GlobalExceptionHandler implements IExecutionExceptionHandler {

    @Override
    public int handleExecutionException(Exception e, CommandLine cmd, ParseResult parseResult) throws Exception {

        cmd.getErr().println(cmd.getColorScheme().errorText(e.getMessage()));

        return cmd.getExitCodeExceptionMapper() != null
                ? cmd.getExitCodeExceptionMapper().getExitCode(e)
                : cmd.getCommandSpec().exitCodeOnExecutionException();
    }
}
