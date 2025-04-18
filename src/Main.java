import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class UserManagementSystem {
    private static final String DATA_FILE = "users_data.json";
    private static JSONArray usersData = new JSONArray();

    public static void main(String[] args) {
        loadData();
        showMenu();
    }

    // Function to load data from JSON file
    private static void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder json = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                usersData = new JSONArray(json.toString());
            } catch (IOException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }
    }

    // Function to save data to JSON file
    private static void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write(usersData.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

