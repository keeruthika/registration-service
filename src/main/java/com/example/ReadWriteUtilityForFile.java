package com.example;

import com.example.dto.RegistrationDTO;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Crunchify.com
 * Best and simple Production ready utility to save/load
 * (read/write) data from/to file
 */

@Component
public class ReadWriteUtilityForFile {

    private static String crunchify_file_location = "C:/Users/Home/Desktop/New folder/registration.txt";
    private static Gson gson = new Gson();


    // Save to file Utility
    public static void crunchifyWriteToFile(String myData) {
        StringBuilder sb = new StringBuilder();
        File crunchifyFile = new File(crunchify_file_location);
        if (!crunchifyFile.exists()) {
            try {
                File directory = new File(crunchifyFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                crunchifyFile.createNewFile();
                String intial = "[]";
                FileWriter myWriter = new FileWriter(crunchify_file_location);
                myWriter.write(intial);
                myWriter.close();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            File myObj = new File(crunchify_file_location);
            Scanner myReader = new Scanner(myObj);
            String data="";
            while (myReader.hasNextLine()) {
                 data = removeLastChar(myReader.nextLine());
            }
            myReader.close();
            sb.append(data).append(",").append(myData).append("]");
            String finalString = sb.toString().replace("[,","[");
            FileWriter myWriter = new FileWriter(crunchify_file_location);
            myWriter.write(finalString);
            myWriter.close();

        } catch (IOException e) {
        }
    }

    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    // Read From File Utility
    public List<RegistrationDTO> crunchifyReadFromFile() {
        List<RegistrationDTO> registrationDTOs = new ArrayList<>();
        File crunchifyFile = new File(crunchify_file_location);
        if (!crunchifyFile.exists())
            log("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(crunchifyFile), "UTF-8");
            JsonReader myReader = new JsonReader(isReader);
            myReader.beginArray();
            while (myReader.hasNext()) {
                RegistrationDTO obj = (new Gson()).fromJson(myReader, RegistrationDTO.class);
                registrationDTOs.add(obj);
            }
            myReader.endArray();
            myReader.close();
        } catch (Exception e) {
            log("error load cache from file " + e.toString());
        }
        return registrationDTOs;


    }

    public static void deleteAllRegisteredUser() throws IOException {
        Files.delete(Paths.get(crunchify_file_location));
    }

    private static void log(String string) {
        System.out.println(string);
    }

}