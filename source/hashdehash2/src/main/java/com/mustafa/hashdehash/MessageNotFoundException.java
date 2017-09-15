package com.mustafa.hashdehash;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Error class implemented for returning 404 in case of missing messages
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Message Not Found")
public class MessageNotFoundException extends RuntimeException {
   
}

