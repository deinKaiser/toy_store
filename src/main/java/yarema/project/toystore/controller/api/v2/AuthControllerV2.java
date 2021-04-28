package yarema.project.toystore.controller.api.v2;


import lombok.RequiredArgsConstructor;
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.service.v2.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v2/auth")
public class AuthControllerV2 {
    private final AuthService service;

    public void createUser(){
        var userRequest = new RequestUserDTO();
        userRequest.setEmail("user@test.com");
        userRequest.setPassword("user");
        service.createUser(userRequest,"ROLE_USER");
        var adminRequest = new RequestUserDTO();
        adminRequest.setEmail("admin@test.com");
        adminRequest.setPassword("admin");
        service.createUser(adminRequest, "ROLE_ADMIN");
    }


    @PostMapping("/createUser")
//    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> registerUser(RequestUserDTO userDto){
        return  ResponseEntity.ok(service.createUser(userDto, "ROLE_USER"));
    }
    @PostMapping("/createAdmin")
    public ResponseEntity<User> registerAdmin(RequestUserDTO userDto){
        return  ResponseEntity.ok(service.createUser(userDto,"ROLE_ADMIN"));
    }
    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> login(@RequestBody RequestUserDTO userRequest){
        return ResponseEntity.ok(service.createToken(userRequest));
    }
}