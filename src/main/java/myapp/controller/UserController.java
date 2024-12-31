


package myapp.controller;

import myapp.dto.UserRequestDTO;
import myapp.dto.UserResponseDTO;
import myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller untuk menangani endpoint CRUD pengguna.
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint untuk mendapatkan semua pengguna.
     *
     * @return ResponseEntity berisi daftar UserResponseDTO dan status HTTP.
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint untuk mendapatkan pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan diambil.
     * @return ResponseEntity berisi UserResponseDTO dan status HTTP.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Endpoint untuk memperbarui pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan diperbarui.
     * @param userRequestDTO Data pengguna yang akan diperbarui.
     * @return ResponseEntity berisi UserResponseDTO dan status HTTP.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponse = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Endpoint untuk menghapus pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan dihapus.
     * @return ResponseEntity dengan status HTTP 204 jika berhasil.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
