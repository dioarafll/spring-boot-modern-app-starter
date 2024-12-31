

package myapp.mapper;

import myapp.dto.UserRequestDTO;
import myapp.dto.UserResponseDTO;
import myapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper untuk konversi antara entitas User, UserRequestDTO, dan UserResponseDTO.
 * Menggunakan MapStruct untuk pemetaan otomatis antara objek.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Mengonversi UserRequestDTO menjadi entitas User.
     * 
     * @param userRequestDTO DTO untuk membuat pengguna baru.
     * @return User entitas yang akan disimpan dalam database.
     */
    @Mapping(target = "emailVerified", ignore = true) // `emailVerified` tidak boleh di-set melalui DTO
    User toUser(UserRequestDTO userRequestDTO);

    /**
     * Mengonversi entitas User menjadi UserResponseDTO.
     * 
     * @param user Entitas User yang akan dikonversi.
     * @return UserResponseDTO untuk dikirimkan ke klien.
     */
    UserResponseDTO toUserResponseDTO(User user);
}
