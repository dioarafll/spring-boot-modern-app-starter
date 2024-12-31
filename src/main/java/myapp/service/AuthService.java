

package myapp.service;

import myapp.dto.UserRequestDTO;
import myapp.dto.UserResponseDTO;
import myapp.exception.UserNotFoundException;
import myapp.mapper.UserMapper;
import myapp.model.User;
import myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service untuk menangani otentikasi pengguna, termasuk registrasi dan login.
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    // Constructor untuk menginisialisasi PasswordEncoder
    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder(); // Menggunakan BCrypt untuk hashing password
    }

    /**
     * Menangani proses registrasi pengguna.
     * 
     * @param userRequestDTO DTO yang berisi data pengguna baru.
     * @return UserResponseDTO berisi data pengguna setelah registrasi.
     */
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        // Cek apakah email sudah terdaftar
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        // Hash password sebelum menyimpannya
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        // Membuat entitas User dari DTO dan mengatur password ter-hash
        userRequestDTO.setPassword(hashedPassword); // Set password yang sudah di-hash ke DTO
        User user = userMapper.toUser(userRequestDTO);

        // Simpan pengguna ke dalam database
        user = userRepository.save(user);

        // Kembalikan UserResponseDTO untuk pengguna yang sudah didaftarkan
        return userMapper.toUserResponseDTO(user);
    }

    /**
     * Menangani proses login pengguna.
     * 
     * @param email Email pengguna yang ingin login.
     * @param password Password pengguna yang ingin login.
     * @return UserResponseDTO berisi data pengguna jika login berhasil.
     * @throws UserNotFoundException Jika pengguna tidak ditemukan.
     * @throws RuntimeException Jika password yang diberikan salah.
     */
    public UserResponseDTO loginUser(String email, String password) {
        // Cari pengguna berdasarkan email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        // Verifikasi password yang diberikan dengan password yang di-hash
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        // Kembalikan UserResponseDTO jika login berhasil
        return userMapper.toUserResponseDTO(user);
    }
}
