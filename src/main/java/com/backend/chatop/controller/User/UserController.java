package com.backend.chatop.controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.errors.GlobalExceptionHandler.ErrorMessage;
import com.backend.chatop.errors.GlobalExceptionHandler.MyAuthenticationEntryPoint.MyAuthenticationEntryPoint;
import com.backend.chatop.model.Rental;
import com.backend.chatop.model.User.User;
import com.backend.chatop.service.User.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name = "User", description = "User management APIs")
public class UserController {

  
    private final UserService userService;


    // !GET_BY_ID
    @Operation(
        summary = "Retrieve {User} by Id", 
        description = "Get a {User} object by specifying its id. The response is {User} object.", 
        tags = {"{User}", "get" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "Found the {User} matching this Id",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
                @ApiResponse(
                    responseCode = "404", 
                    description = "{User} Not Found", 
                    content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),

                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
        })
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getRentalById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
