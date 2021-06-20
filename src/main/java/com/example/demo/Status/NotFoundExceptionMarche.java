package com.example.demo.Status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Marché non trouvé")
public class NotFoundExceptionMarche extends RuntimeException{
}
