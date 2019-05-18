package HelloWorld;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PepPepController {

    public static void main(String[] args) {
    	// Declare variables
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String t = "";
        Scanner sc = new Scanner(System.in);
        String selection = "0";
        double addAmount;
        int counter = 0;
        String travelPass = "None";
        List<String> pepPepTickets = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<String> ticketDates = new ArrayList<>();
        List<Double> ticketCredits = new ArrayList<>();
        int currentId = 0;
        double currentCredit = 0.0;
        String currentDate;
        
        // Show menu options until option X (exit) is not selected
        while (!selection.equals("X")) {
        	// Show menu options to user
            System.out.println("Cinco public transport ticketing system");
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("A - Purchase a new pep-pep ticket or add credit to existing pep-pep ticket");
            System.out.println("B - Purchase travel pass using pep-pep ticket");
            System.out.println("C - Print all purchased tickets");
            System.out.println("X - Exit");
            System.out.println();
            // Wait for user's respond
            System.out.print("Please enter your selection here: ");
            selection = sc.nextLine();
            System.out.println("selection is: " + selection);
            // Working with user's menu option selection
            switch (selection) {
                case "A":
                case "a":
                    System.out.println("Do you have an existing pep-pep ticket? ('yes' to add credit / 'no' to first buy pep-pep ticket)");
                    String peptick = sc.nextLine();
                    if (peptick.equalsIgnoreCase("yes")) {
                        System.out.println("What is your pep-pep ticket 6 digit ID?");
                        int findId = sc.nextInt();
                        boolean found = false;
                        for (int i = 0; i < ids.size(); i++) {
                            if (ids.get(i) == findId) {
                                found = true;
                                currentId = findId;
                                currentCredit = ticketCredits.get(i);
                                currentDate = ticketDates.get(i);
                                System.out.println("found ID: " + findId);
                            }
                        }
                        if (!found) {
                            System.out.println("invalid ID");
                        }
                    } else if (peptick.equalsIgnoreCase("no")) {
                        System.out.println("A new pep-pep ticket is $8");
                        System.out.println("For cash payment, please credit $8 in cash slot below");
                        System.out.println("For card payment, please tap card to the right");
                        System.out.println("Money Accepted");

                        // Generate random pep-pep ticket ID
                        Random rando = new Random();
                        int tempPepId = 100000 + rando.nextInt(999999);
                        for (int i = 0; i < ids.size(); i++) {
                            if (tempPepId == ids.get(i)) {
                                tempPepId = 100000 + rando.nextInt(999999);
                                i = 0;
                            }
                        }
                        // Add pep-pep ticket ID to an array
                        ids.add(tempPepId);
                        currentId = tempPepId;
                        String date = dateFormat.format(new Date());
                        currentDate = date;
                        // Add date of purchase to an array
                        ticketDates.add(date);
                        // Add default pep-pep ticket Credit to an array
                        ticketCredits.add(0.0);
                        currentCredit = 0.0;
                        System.out.print("You pep-pep ticket ID is: " + tempPepId);
                        System.out.println();
                        System.out.println("Date of purchase: " + date);

                    } else {
                        System.out.println("Please, try again!");
                    }
                    System.out.println("Please enter amount you would like to credit your ticket: ");
                    addAmount = sc.nextDouble();
                    // Check if user entered amount that is multiple of 5
                    if (addAmount % 5 == 0 && currentCredit + addAmount <= 100.0) {
                        for (int i = 0; i < ids.size(); i++) {
                            if (currentId == ids.get(i)) {
                            	// Add and override the Credit of user's pep-pep ticket with a new amount 
                                ticketCredits.set(i, ticketCredits.get(i) + addAmount);
                            }
                        }
                    } else {
                    	// Notify user about the error in Credit amount
                        System.out.println("Invalid amount- Must be in multiples of $5 and less than $100.");
                    }
                    break;
                // Buying travel passes
                case "B":
                case "b":
                	// Declare variables
                    String travelPassType;
                    double passPrice;
                    String HA = null;
                    String HB = null;
                    String DA = null;
                    String DB = null;
                    // Show menu options to user
                    System.out.println("Select Travel Pass Type");
                    System.out.println("--------------------------------------------");
                    System.out.println();
                    System.out.println("type 'HA' for 2 hours, Zone 1");
                    System.out.println("type 'HB' for  2 hours, Zone 1 & Zone 2");
                    System.out.println("type 'DA' for  All Day, Zone 1");
                    System.out.println("type 'DB' for  All Day, Zone 1 & Zone 2");
                    System.out.println();
                    // Wait for user's respond
                    System.out.print("Please enter your Travel Pass Type here: ");
                    travelPassType = sc.nextLine();
                    // Working with user's menu option selection
                    if (travelPassType.equals("HA") && currentCredit >= 3.5)
                    {
                        passPrice = 3.50;
                        travelPass = "2 hours, zone 1";
                    }
                    else if (travelPassType.equals("HB") && currentCredit >= 6.0)
                    {
                        passPrice = 6.00;
                        travelPass = "2 hours, zone 1 & 2";
                    }
                    else if (travelPassType.equals("DA") && currentCredit >= 7.0)
                    {
                        passPrice = 7.00;
                        travelPass = "All day, zone 1";
                    }
                    else if (travelPassType.equals("DB") && currentCredit >= 12.0)
                    {
                        passPrice = 12.00;
                        travelPass = "All day, zone 1 & 2";
                    }
                    else // Price will be zero
                    {
                    	// Notify user about the error
                        passPrice = 0.00;
                        System.out.println("You have insufficient funds to purchase a travel pass");
                        System.out.println("Credit Available:" + currentCredit);
                        System.out.println("Travel pass price:" + passPrice);
                    }
                    break;
                // Working with printing of tickets
                case "C":
                case "c":
                    String s = "";
                    while (!s.equalsIgnoreCase("X")) {
                        System.out.println("CA - don't sort");
                        System.out.println("CB - sort by date");
                        System.out.println("CC - sort by id");
                        // Wait for user's respond
                        System.out.print("Please enter your selection: ");
                        s = sc.nextLine();
                        // Working with user's menu option selection
                        switch (s) {
                        	// User selected an option to not sort
                            case "CA":
                            case "ca":
                                System.out.println("Printing...");
                                // Loop through the array and print pep-pep tickets
                                for (int i = 0; i < ids.size(); i++) {
                                    System.out.println("Ticket Id: " + ids.get(i) + ", Purchase date: " + ticketDates.get(i) + ", Ticket credit: " + ticketCredits.get(i));
                                    System.out.println("-----------------------------");
                                }
                                s = "X";
                                break;
                            // User selected an option to sort by date
                            case "CB":
                            case "cb":
                                System.out.println("Printing...");
                                // Loop through the array and print pep-pep tickets
                                for (int i = 0; i < ids.size(); i++) {
                                    System.out.println("Ticket Id: " + ids.get(i) + ", Purchase date: " + ticketDates.get(i) + ", Ticket credit: " + ticketCredits.get(i));
                                    System.out.println("-----------------------------");
                                }
                                s = "X";
                                break;
                            // User selected an option to sort by ID's
                            case "CC":
                            case "cc":
                                System.out.println("Printing...");
                                // Declare variables
                                List<Double> tCredits = ticketCredits;
                                List<String> tDates = ticketDates;
                                List<Integer> tIds = ids;
                                // Loop through the array and sort pep-pep tickets by ID
                                for (int i=0; i < tIds.size(); i++) {
                                    for (int j = i+1; j < tIds.size(); j++){
                                        if (tIds.get(i) > tIds.get(j)) {
                                            Collections.swap(tIds, i, j);
                                            Collections.swap(tDates, i, j);
                                            Collections.swap(tCredits, i, j);
                                        }
                                    }
                                }
                                // Loop through the array and print pep-pep tickets
                                for (int i = 0; i < tIds.size(); i++) {
                                    System.out.println("Ticket Id: " + tIds.get(i) + ", Purchase date: " + tDates.get(i) + ", Ticket credit: " + tCredits.get(i));
                                    System.out.println("-----------------------------");
                                }
                                s = "X";
                                break;
                            default:
                                System.out.println("Please try again!");
                                break;
                        }
                    }
                    break;
                // User selected an option to exit the program
                case "X":
                case "x":
                    System.out.println("Exiting menu...");
                    break;
                default:
                    System.out.println("Error - Invalid menu selection!");
                    System.out.println(selection);
                    break;
            }
//            t = sc.nextLine();
        }
    }
}
