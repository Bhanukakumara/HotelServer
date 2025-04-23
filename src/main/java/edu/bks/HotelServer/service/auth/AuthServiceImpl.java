package edu.bks.HotelServer.service.auth;

import edu.bks.HotelServer.Repository.UserRepository;
import edu.bks.HotelServer.dto.SignupRequest;
import edu.bks.HotelServer.dto.UserDto;
import edu.bks.HotelServer.entity.User;
import edu.bks.HotelServer.enums.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }else {
            System.out.println("Admin account already exist");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With email" +signupRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createUser = userRepository.save(user);
        return createUser.getUserDto();
    }
}
