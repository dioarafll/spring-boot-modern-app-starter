

package myapp.exception;

/**
 * Exception yang dilemparkan saat pengguna tidak ditemukan.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Konstruktor exception dengan pesan khusus.
     * 
     * @param message Pesan error yang akan ditampilkan.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
