

package myapp.dto;

/**
 * DTO untuk merespons data pengguna setelah registrasi atau login.
 */
public class UserResponseDTO {

    private Long id;
    private String email;
    private Boolean emailVerified;

    // Getter dan Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
