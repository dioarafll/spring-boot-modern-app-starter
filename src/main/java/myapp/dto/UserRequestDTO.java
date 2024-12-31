

package myapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * DTO untuk request registrasi pengguna.
 */
public class UserRequestDTO {

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    @NotEmpty(message = "Email verification status cannot be null.")
    private Boolean emailVerified;

    // Getter dan Setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
