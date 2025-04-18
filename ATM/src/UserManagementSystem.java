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

    // Function to create a new user
    private static void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (!validateEmail(email)) {
            System.out.print("Invalid email. Enter a valid email: ");
            email = scanner.nextLine();
        }
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        JSONObject user = new JSONObject();
        user.put("username", username);
        user.put("email", email);
        user.put("balance", balance);
        usersData.put(user);
        saveData();
        System.out.println("User created successfully.");
    }

    // Function to read user details
    private static void readUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to search: ");
        String username = scanner.nextLine();
        for (int i = 0; i < usersData.length(); i++) {
            JSONObject user = usersData.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                System.out.println("Username: " + user.getString("username"));
                System.out.println("Email: " + user.getString("email"));
                System.out.println("Balance: " + user.getDouble("balance"));
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Function to update user information
    private static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to update: ");
        String username = scanner.nextLine();
        for (int i = 0; i < usersData.length(); i++) {
            JSONObject user = usersData.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                System.out.print("Enter new email for " + username + ": ");
                user.put("email", scanner.nextLine());
                System.out.print("Enter new balance for " + username + ": ");
                user.put("balance", scanner.nextDouble());
                saveData();
                System.out.println("User updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Function to delete user
    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to delete: ");
        String username = scanner.nextLine();
        for (int i = 0; i < usersData.length(); i++) {
            JSONObject user = usersData.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                usersData.remove(i);
                saveData();
                System.out.println("User deleted successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Function to deposit amount with interest
    private static void depositWithInterest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to deposit: ");
        String username = scanner.nextLine();
        for (int i = 0; i < usersData.length(); i++) {
            JSONObject user = usersData.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                System.out.print("Enter interest rate (in %): ");
                double interestRate = scanner.nextDouble();
                double amountWithInterest = amount + (amount * interestRate / 100);
                user.put("balance", user.getDouble("balance") + amountWithInterest);
                saveData();
                System.out.println(amountWithInterest + " deposited successfully with interest.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Function to credit amount with interest
    private static void creditWithInterest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to credit: ");
        String username = scanner.nextLine();
        for (int i = 0; i < usersData.length(); i++) {
            JSONObject user = usersData.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                System.out.print("Enter amount to credit: ");
                double amount = scanner.nextDouble();
                System.out.print("Enter interest rate (in %): ");
                double interestRate = scanner.nextDouble();
                double amountWithInterest = amount + (amount * interestRate / 100);
                if (user.getDouble("balance") >= amountWithInterest) {
                    user.put("balance", user.getDouble("balance") - amountWithInterest);
                    saveData();
                    System.out.println(amountWithInterest + " credited successfully with interest.");
                } else {
                    System.out.println("Insufficient funds.");
                }
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Function to transfer funds between users
    private static void transferFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the sender's username: ");
        String fromUser = scanner.nextLine();
        System.out.print("Enter the receiver's username: ");
        String toUser = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        JSONObject sender = null;
        JSONObject receiver = null;
        for (int i = 0; i < usersData.length(); i++) {
            JSONObject user = usersData.getJSONObject(i);
            if (user.getString("username").equals(fromUser)) {
                sender = user;
            }
            if (user.getString("username").equals(toUser)) {
                receiver = user;
            }
        }
        if (sender != null && receiver != null) {
            if (sender.getDouble("balance") >= amount) {
                sender.put("balance", sender.getDouble("balance") - amount);
                receiver.put("balance", receiver.getDouble("balance") + amount);
                saveData();
                System.out.println("Transfer of " + amount + " completed successfully.");
            } else {
                System.out.println("Sender has insufficient funds.");
            }
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Function to validate email format
    private static boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    // Function to generate report
    private static void generateReport() {
        int totalUsers = usersData.length();
        double totalBalance = 0;
        for (int i = 0; i < usersData.length(); i++) {
            totalBalance += usersData.getJSONObject(i).getDouble("balance");
        }
        System.out.println("Total users: " + totalUsers);
        System.out.println("Total balance: " + totalBalance);
    }

    // Function to show the menu
    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create user");
            System.out.println("2. Read user");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("5. Deposit with interest");
            System.out.println("6. Credit with interest");
            System.out.println("7. Transfer funds");
            System.out.println("8. Generate report");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    readUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    depositWithInterest();
                    break;
                case 6:
                    creditWithInterest();
                    break;
                case 7:
                    transferFunds();
                    break;
                case 8:
                    generateReport();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}