package com.bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc  = new Scanner(System.in);
       User currentUser = null;

       while(true){
            System.out.println("\n------ Welcome to Banking System -----");
            if(currentUser == null){
                System.out.println(" 1. Create Account");
                System.out.println(" 2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose Option: ");
                int choice = sc.nextInt();
                sc.nextLine();
                if(choice == 1){
                  BankingOperations.CreateAcc();
                }else if(choice == 2){
                    currentUser = BankingOperations.login();
                }else {
                    System.out.println("Logout Successfully.");
                    break;
                }
            }else {
                System.out.println("\n Welcome," + currentUser.name);
                System.out.println("1. Deposit");
                System.out.println("2. withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Transaction History");
                System.out.println("5. Logout");
                System.out.println("Choose Option: ");
                int choice  = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1 :
                        BankingOperations.deposit(currentUser);
                        break;
                    case 2:
                        BankingOperations.withdraw(currentUser);
                        break;
                    case 3:
                        BankingOperations.checkBalance(currentUser);
                        break;
                    case 4:
                        BankingOperations.transactionHistory(currentUser);
                        break;
                    case 5:
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
       }
       sc.close();
       System.out.println("Thank you for using the System.");
    }
}