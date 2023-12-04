package com.caklipakli.catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppException  {

    String message;
    String cause;

}
