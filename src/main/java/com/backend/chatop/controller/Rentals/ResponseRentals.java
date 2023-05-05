package com.backend.chatop.controller.Rentals;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseRentals {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", status.value());
            map.put("rentals", responseObj);
            return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> generateResponseMessage(HttpStatus status, Object responseObj, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", status.value());
            map.put("rental", responseObj);
            map.put("message", message);
            return new ResponseEntity<Object>(map,status);
    }
   
}
