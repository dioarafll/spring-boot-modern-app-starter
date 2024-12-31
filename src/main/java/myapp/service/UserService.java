


package myapp.service;

import myapp.dto.UserRequestDTO;
import myapp.dto.UserResponseDTO;
import myapp.exception.UserNotFoundException;
import myapp.mapper.UserMapper;
import myapp.model.User;
import myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service untuk mengelola operasi CRUD pada entitas User dengan caching dan hashing password.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Mendapatkan semua pengguna.
     *
     * @return Daftar UserResponseDTO berisi semua data pengguna.
     */
    @Cacheable(value = "users", key = "'allUsers'")
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Mendapatkan pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan diambil.
     * @return UserResponseDTO berisi data pengguna.
     * @throws UserNotFoundException jika pengguna tidak ditemukan.
     */
    @Cacheable(value = "users", key = "#id")
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userMapper.toUserResponseDTO(user);
    }

    /**
     * Memperbarui data pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan diperbarui.
     * @param userRequestDTO Data pengguna yang akan diperbarui.
     * @return UserResponseDTO berisi data pengguna setelah diperbarui.
     * @throws UserNotFoundException jika pengguna tidak ditemukan.
     */
    @CachePut(value = "users", key = "#id")
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setEmail(userRequestDTO.getEmail());
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));
        }
        existingUser.setEmailVerified(userRequestDTO.getEmailVerified());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserResponseDTO(updatedUser);
    }

    /**
     * Menghapus pengguna berdasarkan ID.
     *
     * @param id ID pengguna yang akan dihapus.
     * @throws UserNotFoundException jika pengguna tidak ditemukan.
     */
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}
