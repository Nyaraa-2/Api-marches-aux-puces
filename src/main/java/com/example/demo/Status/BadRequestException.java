package com.example.demo.Status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Le nom du marché existe déjà")
public class BadRequestException extends RuntimeException{
}
