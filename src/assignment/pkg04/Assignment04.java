package assignment.pkg04;

import java.io.*;
import java.util.*;

/**
 * Assignment-04
 *
 * This program is designed to store information in a .txt file where the
 * program will be asking the user for the options to Add, Read, Update, Delete
 * any information stored in the .txt file. It is stored as a list and parts can
 * be accessed with the index number user must stop program using the number 5.
 *
 * @author Victor Telles
 *
 */
public class Assignment04 {

    private static final String DATA_FILE_PATH = "data.txt"; // .txt file where data is saved

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        List<String> dataEntries = loadData();

        while (true) { // Runs untill user Quits
            System.out.println("Choose an option using the #'s: [1] Add, [2] Read/Display, [3] Update, [4] Delete, [5] Quit");
            int userChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Clears the newline character after the number input
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter the new data: ");
                    String newData = inputScanner.nextLine();
                    dataEntries.add(newData);
                }
                case 2 ->
                    displayData(dataEntries);
                case 3 -> {
                    // Display the data before asking which to update
                    System.out.println("Current data entries:");
                    displayData(dataEntries);

                    System.out.println("Enter # to update: ");
                    int updateIndex = inputScanner.nextInt();
                    if (updateIndex >= 0 && updateIndex < dataEntries.size()) {
                        inputScanner.nextLine();
                        System.out.println("Enter the new data: ");
                        String newData = inputScanner.nextLine();
                        dataEntries.set(updateIndex, newData);
                        System.out.println("Updated successfully.");
                    } else {
                        System.out.println("Invalid #!!!");
                    }
                }
                case 4 -> {
                    // Display the data before asking which to delete
                    System.out.println("Current data entries:");
                    displayData(dataEntries);

                    System.out.println("Enter # to delete: ");
                    int deleteIndex = inputScanner.nextInt();
                    if (deleteIndex >= 0 && deleteIndex < dataEntries.size()) {
                        dataEntries.remove(deleteIndex);
                        System.out.println("Deleted successfully.");
                    } else {
                        System.out.println("Invalid # to delete!!!");
                    }
                }
                case 5 -> {
                    saveData(dataEntries); // Save the data to the file
                    System.exit(0); // Quits/Exits program
                }
                default ->
                    System.out.println("Invalid #. Please enter a # between 1-5.");
            }
        }
    }

    private static void displayData(List<String> dataEntries) {
        if (dataEntries.isEmpty()) {
            System.out.println("No data to read or display.");
        } else {
            for (int i = 0; i < dataEntries.size(); i++) {
                System.out.println(i + ": " + dataEntries.get(i));
            }
        }
    }

    private static List<String> loadData() {
        List<String> dataFromFile = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(DATA_FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                dataFromFile.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!");
        }
        return dataFromFile;
    }

    private static void saveData(List<String> dataToSave) {
        try (PrintWriter outputFile = new PrintWriter(DATA_FILE_PATH)) {
            for (String dataEntry : dataToSave) {
                outputFile.println(dataEntry);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving!!!");
        }
    }
}
