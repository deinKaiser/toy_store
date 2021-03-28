package yarema.project.toystore.stubs;

import yarema.project.toystore.dto.type.RequestTypeDTO;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.entity.Type;

public final class TypeStub {
    public static final Long ID = 1L;
    public static Type getType () {
        return Type.builder()
                .id(ID)
                .name("my type")
                .build();
    }

    public static RequestTypeDTO getRequestDTO(){
        return RequestTypeDTO.builder()
                .name("my type")
                .build();
    }

    public static ResponseTypeDTO getResponseDTO(){
        return ResponseTypeDTO.builder()
                .id(ID)
                .name("my type")
                .build();
    }
}
