package edu.bks.HotelServer.service.auth;

import edu.bks.HotelServer.dto.SignupRequest;
import edu.bks.HotelServer.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
