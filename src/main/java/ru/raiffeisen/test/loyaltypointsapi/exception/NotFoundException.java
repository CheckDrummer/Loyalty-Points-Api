package ru.raiffeisen.test.loyaltypointsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No any data for that user id")
public class NotFoundException extends RuntimeException {
}
