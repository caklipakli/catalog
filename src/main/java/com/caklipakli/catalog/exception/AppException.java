package com.caklipakli.catalog.exception;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class AppException {

    String message;
    String cause;

}
