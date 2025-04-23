package edu.bks.HotelServer.dto;

import edu.bks.HotelServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private  Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
