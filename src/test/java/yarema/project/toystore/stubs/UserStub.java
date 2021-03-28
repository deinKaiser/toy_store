package yarema.project.toystore.stubs;

import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.entity.User;

public final class UserStub {
    public static final Long ID = 1L;
    public static User getUser() {
        return User.builder()
                .id(ID)
                .name("test user")
                .phoneNumber("380502283227")
                .email("email@email.em")
                .build();
    }

    public static RequestUserDTO getRequestDTO () {
        return RequestUserDTO.builder()
                .name("test user")
                .phoneNumber("380502283227")
                .email("email@email.em")
                .build();
    }

    public  static ResponseUserDTO getResponseDTO () {
        return ResponseUserDTO.builder()
                .id(ID)
                .name("test user")
                .phoneNumber("380502283227")
                .email("email@email.em")
                .build();
    }
}
