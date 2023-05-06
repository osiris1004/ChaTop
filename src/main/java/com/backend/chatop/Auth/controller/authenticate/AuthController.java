package com.backend.chatop.Auth.controller.authenticate;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop.Auth.controller.registry.RegisterRequest;
import com.backend.chatop.Auth.service.AuthService;
import com.backend.chatop.config.Security.middleware.JwtService;
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
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

    private final AuthService service;
    private final UserService userService;
    private final JwtService jwtService;

    //! registration
    @Operation(
        summary = "{Registration} process", 
        description = "Create an an account through the {Register} system. The response is a {Registered token} object", 
        tags = {"{Register}", "post" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = " {Registration} process was successfully",
                    content = {@Content(schema = @Schema(implementation = AuthResponse.class), mediaType = "application/json") }),
        })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }


    //! login
    @Operation(
        summary = "{Login} in to your account", 
        description = "Authentication process . The response is User object.", 
        tags = {"{Login}", "post" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "You successfully login in to your account  ",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json") }),
        })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


     //! me
     @Operation(
        summary = "Retrieve {User} with token", 
        description = "Get a {User} object by specifying based on the token. The response is {User} object.", 
        tags = {"{Me}", "get" })
        @ApiResponses({
                @ApiResponse(
                    responseCode = "200", 
                    description = "Found the {User} matching the token",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
                @ApiResponse(
                    responseCode = "404", 
                    description = "{Rental} Not Found", 
                    content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),

                @ApiResponse(
                    responseCode = "401", 
                    description = "Unauthorized",
                    content = {@Content(schema = @Schema(implementation = MyAuthenticationEntryPoint.class)) }),
        })
    @GetMapping("/me")
    public ResponseEntity<User> getUser(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new Exception("Exception message");
            }
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUserName(jwt);
            return ResponseEntity.ok(userService.getUserByEmail(userEmail));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
