package com.example.demo.Status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Emplacement non trouvé")
public class NotFoundExceptionEmplacements extends RuntimeException{
}
