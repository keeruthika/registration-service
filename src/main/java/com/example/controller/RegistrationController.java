package com.example.registration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.example.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import com.google.gson.*;

@RestController
public class RegistrationController {
    private static Gson gson = new Gson();
    @Autowired
    ReadWriteUtilityForFile readWriteUtilityForFile;

    @GetMapping(path = "/getAllRegisteredUser")
    public List<RegistrationDTO> getAllRegisteredUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        return null;
    }

    @PostMapping(path = "/registerUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationDTO registerUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        readWriteUtilityForFile.crunchifyWriteToFile(gson.toJson(registrationDTO));
        return registrationDTO;
    }

}