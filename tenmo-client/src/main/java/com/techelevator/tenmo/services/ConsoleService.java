package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }
    /*
        Menu options to be implemented at a later date:
            System.out.println("4: View your pending requests");
            System.out.println("5: Request TE bucks");
     */
    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: Send TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printTransfersOptions() {
        System.out.println();
        System.out.println("#: Enter an ID# to show transfer details");
        System.out.println("0: Return to main menu");
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public long promptForId(String prompt) {
        long idSelection = 0;
        System.out.print(prompt);
        try {
            idSelection = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }

        return idSelection;
    }

    public BigDecimal promptForAmount(String prompt) {
        BigDecimal amount = BigDecimal.valueOf(0);
        System.out.print(prompt);
        try {
            amount = new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }

        return amount;
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public long promptForLong(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

}
