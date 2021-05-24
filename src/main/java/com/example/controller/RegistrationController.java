package com.example.controller;

import com.example.ReadWriteUtilityForFile;
import com.example.dto.RegistrationDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {
    private static Gson gson = new Gson();
    @Autowired
    ReadWriteUtilityForFile readWriteUtilityForFile;

    @GetMapping(path = "/getAllRegisteredUser")
    public List<RegistrationDTO> getAllRegisteredUser() throws Exception {
        return readWriteUtilityForFile.crunchifyReadFromFile();
    }

    @PostMapping(path = "/registerUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationDTO registerUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        readWriteUtilityForFile.crunchifyWriteToFile(gson.toJson(registrationDTO));
        return registrationDTO;
    }

    @DeleteMapping(path = "/deleteAllRegisteredUser")
    public String deleteAllRegisteredUser() throws Exception {
        readWriteUtilityForFile.deleteAllRegisteredUser();
        return "deletion success";
    }

    @GetMapping(path = "/getRegisteredUserByUserName/{userName}")
    public RegistrationDTO getRegisteredUserByUserName(@PathVariable("userName") String userName) throws Exception {
        RegistrationDTO value = null;
        List<RegistrationDTO> list = readWriteUtilityForFile.crunchifyReadFromFile();
        for(RegistrationDTO test:list){
            if(test.getUserName().equalsIgnoreCase(userName)){
                value = test;
                break;
            }
        }
        return value;
    }


}