

package myapp.repository;

import myapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository untuk entitas User. Menggunakan JpaRepository untuk akses data database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Menyediakan metode untuk mencari pengguna berdasarkan email.
     * 
     * @param email Email pengguna yang akan dicari.
     * @return Optional yang berisi User jika ditemukan, atau kosong jika tidak ada pengguna dengan email tersebut.
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Menyediakan metode untuk memeriksa apakah email sudah ada dalam database.
     * 
     * @param email Email yang akan diperiksa.
     * @return True jika email sudah ada, false jika tidak.
     */
    boolean existsByEmail(String email);
}
