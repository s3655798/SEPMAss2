package HelloWorld;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PepPepController {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
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
        while (!selection.equals("X")) {
            System.out.println("Cinco public transport ticketing system");
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("A - Purchase a new pep-pep ticket or add credit to existing pep-pep ticket");
            System.out.println("B - Purchase travel pass using pep-pep ticket");
            System.out.println("C - Print all purchased tickets");
            System.out.println("X - Exit");
            System.out.println();
            System.out.print("Please enter your selection here: ");
            selection = sc.nextLine();
            System.out.println("selection is: " + selection);
            switch (selection) {
                case "A":
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

                        Random rando = new Random();
                        int tempPepId = 100000 + rando.nextInt(999999);
                        for (int i = 0; i < ids.size(); i++) {
                            if (tempPepId == ids.get(i)) {
                                tempPepId = 100000 + rando.nextInt(999999);
                                i = 0;
                            }
                        }
                        ids.add(tempPepId);
                        currentId = tempPepId;
                        String date = dateFormat.format(new Date());
                        currentDate = date;
                        ticketDates.add(date);
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
                    if (addAmount % 5 == 0 && currentCredit + addAmount <= 100.0) {
                        for (int i = 0; i < ids.size(); i++) {
                            if (currentId == ids.get(i)) {
                                ticketCredits.set(i, ticketCredits.get(i) + addAmount);
                            }
                        }
                    } else {
                        System.out.println("Invalid amount- Must be in multiples of $5 and less than $100.");
                    }
                    break;
                case "B":
                    String travelPassType;
                    double passPrice;
                    String HA = null;
                    String HB = null;
                    String DA = null;
                    String DB = null;
                    System.out.println("Select Travel Pass Type");
                    System.out.println("--------------------------------------------");
                    System.out.println();
                    System.out.println("type 'HA' for 2 hours, Zone 1");
                    System.out.println("type 'HB' for  2 hours, Zone 1 & Zone 2");
                    System.out.println("type 'DA' for  All Day, Zone 1");
                    System.out.println("type 'DB' for  All Day, Zone 1 & Zone 2");
                    System.out.println();
                    System.out.print("Please enter your Travel Pass Type here: ");
                    travelPassType = sc.nextLine();
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
                        passPrice = 0.00;
                        System.out.println("You have insufficient funds to purchase a travel pass");
                        System.out.println("Credit Available:" + currentCredit);
                        System.out.println("Travel pass price:" + passPrice);
                    }
                    break;
                case "C":
                    System.out.println("Printing...");
                    for (int i=0; i<ids.size(); i++) {
                        System.out.println("Ticket Id: " + ids.get(i) + ", Purchase date: " + ticketDates.get(i) + ", Ticket credit: " + ticketCredits.get(i));
                        System.out.println("-----------------------------");
                    }
                    break;
                case "X":
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
