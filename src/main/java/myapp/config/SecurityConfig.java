
package myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Konfigurasi untuk enkripsi password menggunakan BCryptPasswordEncoder.
 */
@Configuration
public class SecurityConfig {

    /**
     * Mendefinisikan bean PasswordEncoder menggunakan BCryptPasswordEncoder.
     * 
     * @return PasswordEncoder untuk mengenkripsi dan memverifikasi password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
