/*
 * Thomas Wessel 2019.
 */


package com.collectioncard;

import com.collectioncard.Exceptions.PersonNotFoundException;
import com.sun.istack.internal.Nullable;

import java.util.Scanner;

public class Menu {

    static Phonebook phoneBook = new Phonebook();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice = 0;

        //import previous tree
        try {
            phoneBook.readFromFile();
            System.out.printf("\n\u001B[42m\u001B[30mPhone book loaded from file!\u001B[0m\n\n");
        } catch (Exception e) {
            System.out.printf("\n\u001B[41m\u001B[30mWarning: No phone book file exists!\u001B[0m\n\u001B[41m\u001B[30mOne will be created on program exit.\u001B[0m\n\n");

        }


        while (1 + 3 != 5) {

            System.out.println("|*******************************************|");
            System.out.println("|        Welcome to the phone book!         |");
            System.out.println("|*******************************************|");
            System.out.println("|1. Add a person to the phone book          |");
            System.out.println("|2. Find a person in the phone book         |");
            System.out.println("|3. Change a person's phone number          |");
            System.out.println("|4. Delete a person from the phone book     |");
            System.out.println("|5. Exit                                    |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Choice: ");
            try {
                choice = input.nextInt();

                //print out a few empty lines
                System.out.println("\n");

                switch (choice) {
                    case 1:
                        addPerson(null);
                        break;
                    case 2:
                        findPerson();
                        break;
                    case 3:
                        changeData();
                        break;
                    case 4:
                        delete();
                        break;
                    case 5:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid option!\nTry again\n");
                }
            } catch (Exception e) {
                System.out.println("\n\nInvalid Input!\n");
                input.next();
            }
        }
    }

    private static void addPerson(@Nullable String name) {

        Scanner input = new Scanner(System.in);

        System.out.println("|***************************|");
        System.out.println("|       Add a Person        |");
        System.out.println("|***************************|");

        if (name == null) {
            System.out.print("Please enter a name: ");
            name = input.nextLine();
        } else {
            System.out.println("Name is: " + name);
        }

        System.out.print("Please enter a phone number: ");
        String newNumber = input.nextLine();

        phoneBook.add(newNumber, name);
        //print out a few empty lines
        System.out.println("\n");

    }

    private static void findPerson() {
        Scanner input = new Scanner(System.in);

        System.out.println("|***************************|");
        System.out.println("|       Find a Person       |");
        System.out.println("|***************************|");

        String toFind = "";
        System.out.print("Please enter a name: ");
        toFind = input.nextLine();
        try {
            System.out.println("The number is: " + phoneBook.find(toFind).getNumber());
        } catch (Exception e) {
            System.out.println("No such person is in the phone book!");
            System.out.print("Would you like to add them(y/n): ");
            if (input.nextLine().equalsIgnoreCase("y")) {
                System.out.println("Adding new person\n\n");
                addPerson(toFind);
            }

        }
        //print out a few empty lines
        System.out.println("\n");
    }

    private static void changeData() {
        Scanner input = new Scanner(System.in);
        String name, newNumber;

        System.out.println("|*************************************|");
        System.out.println("|            Change Contact           |");
        System.out.println("|*************************************|");

        System.out.print("Please enter the name: ");
        name = input.nextLine();

        //I want to check to see if there is a user in the phonebook that matches the name provided instead of asking for a number and then checking.
        try {
            //see if person exists
            phoneBook.find(name);

            //if there wasn't an exception, ask for a number
            System.out.print("Please enter a new number: ");
            newNumber = input.nextLine();
            System.out.println("The new phone number is: " + phoneBook.changeData(name, newNumber) + "\n\n");

        } catch (PersonNotFoundException e) {
            System.out.print("Person not in phonebook\nWould you like to add them(y/n): ");
            if (input.nextLine().equalsIgnoreCase("y")) {
                System.out.println("Adding new person\n\n");
                addPerson(name);
            }
            System.out.println("\n\n");
        }
    }

    private static void delete() {
        Scanner input = new Scanner(System.in);

        System.out.println("|***************************|");
        System.out.println("|      Delete a Person      |");
        System.out.println("|***************************|");
        System.out.print("contact name: ");
        try {
            phoneBook.delete(input.nextLine());
        } catch (PersonNotFoundException e) {
            System.out.println("Person not found in phonebook!");
        }
        //Print some empty lines
        System.out.println("\n\n");
    }

    private static void exit() {
        //write tree to file
        phoneBook.saveToFile();
        //exit
        System.exit(0);
    }
}