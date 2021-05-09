package com.example;

import com.example.dto.RegistrationDTO;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * @author Crunchify.com
 * Best and simple Production ready utility to save/load
 * (read/write) data from/to file
 */

@Component
public class ReadWriteUtilityForFile {

    private static String crunchify_file_location = "C:/Users/Home/Desktop/New folder/crunchify.txt";
    private static Gson gson = new Gson();


    // Save to file Utility
    public static void crunchifyWriteToFile(String myData) {
        File crunchifyFile = new File(crunchify_file_location);
        if (!crunchifyFile.exists()) {
            try {
                File directory = new File(crunchifyFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                crunchifyFile.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter crunchifyWriter;
            crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), true);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
            bufferWriter.write(myData.toString());
            bufferWriter.close();

            log("Company data saved at file location: " + crunchify_file_location + " Data: " + myData + "\n");
        } catch (IOException e) {
            log("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
    }

    // Read From File Utility
    public List<RegistrationDTO> crunchifyReadFromFile() {
        List<RegistrationDTO> registrationDTOs = null;
        File crunchifyFile = new File(crunchify_file_location);
        if (!crunchifyFile.exists())
            log("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(crunchifyFile), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);
            RegistrationDTO company = gson.fromJson(myReader, RegistrationDTO.class);

            registrationDTOs = gson.fromJson(myReader, RegistrationDTO.class);


        } catch (Exception e) {
            log("error load cache from file " + e.toString());
        }
        return registrationDTOs;


    }

    private static void log(String string) {
        System.out.println(string);
    }

}