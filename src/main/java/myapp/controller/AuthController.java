

package myapp.controller;

import myapp.dto.UserRequestDTO;
import myapp.dto.UserResponseDTO;
import myapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controller untuk menangani endpoint registrasi dan login.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint untuk registrasi pengguna baru.
     * 
     * @param userRequestDTO Data pengguna yang ingin didaftarkan.
     * @return ResponseEntity dengan status dan UserResponseDTO.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = authService.registerUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    /**
     * Endpoint untuk login pengguna.
     * 
     * @param email Email pengguna yang ingin login.
     * @param password Password pengguna yang ingin login.
     * @return ResponseEntity dengan status dan UserResponseDTO.
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestParam String email, @RequestParam String password) {
        UserResponseDTO userResponseDTO = authService.loginUser(email, password);
        return ResponseEntity.ok(userResponseDTO);
    }
}
