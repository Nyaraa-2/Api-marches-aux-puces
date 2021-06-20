package com.example.demo.Status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Reservation non trouvée")
public class NotFoundExceptionReserve extends RuntimeException{
}
