package com.example.registration;
import lombok.Data;

@Data
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private Long mobile;
    private String address;
    private String gender;
    private String country;
    private String userName;
    private String password;
    private String emailId;

}