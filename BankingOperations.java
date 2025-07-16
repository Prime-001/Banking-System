package com.bank;

import java.sql.*;
import  java.util.Scanner;

public class BankingOperations {
    static Scanner sc = new Scanner(System.in);

    public static void CreateAcc(){
        try(Connection con = DBConnection.getConnection()){
            System.out.print("Enter Name");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Set 4-digit PIN: ");
            String pin = sc.nextLine();

            System.out.print("Initial Deposit: ");
            double balance = sc.nextDouble();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement("insert into User(name, email, pin, balance) Values(?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pin);
            ps.setDouble(4, balance);
            ps.executeUpdate();
            System.out.println("Account Created Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static User login(){
        try(Connection con = DBConnection.getConnection()){
            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            PreparedStatement ps = con.prepareStatement("Select * from User where email=? AND pin=?");
            ps.setString(1, email);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("acc_no"), rs.getString("name"), email, pin , rs.getDouble("balance"));
            }else{
                System.out.println("Login failed");
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void deposit(User user){
        try(Connection con = DBConnection.getConnection()){
            System.out.print("Enter Amount to deposit:");
            double amt = sc.nextDouble();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement("update User set balance = balance +? where acc_no =? ");
            ps.setDouble(1, amt);
            ps.setInt(2, user.accNo);
            ps.executeUpdate();

            PreparedStatement trans = con.prepareStatement("insert into Transaction(acc_no, type, amount) Values (?,'deposit',?)");
            trans.setInt(1,user.accNo);
            trans.setDouble(2,amt);
            trans.executeUpdate();
            System.out.println("deposit successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void withdraw(User user){
        try(Connection con = DBConnection.getConnection()){
            System.out.print("Enter amount to withdraw:");
            double amt = sc.nextDouble();
            sc.nextLine();

            if(amt > user.balance){
                System.out.println("Insufficient balance.");
                return;
            }
            PreparedStatement ps = con.prepareStatement("update User set balance = balance - ? where acc_no =?");
            ps.setDouble(1, amt);
            ps.setInt(2, user.accNo);
            ps.executeUpdate();

            PreparedStatement trans = con.prepareStatement("insert into Transaction(acc_no, type, amount) Values (?,'withdraw',?)");
            trans.setInt(1,user.accNo);
            trans.setDouble(2, amt);
            trans.executeUpdate();

            System.out.println("withdrawal successful.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void checkBalance(User user){
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("select balance from User where acc_no =?");
            ps.setInt(1,user.accNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Current balance : " + rs.getDouble("balance"));
                user.balance = rs.getDouble("balance");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void transactionHistory(User user){
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from Transaction where acc_no = ? order by timestamp desc");
            ps.setInt(1, user.accNo);
            ResultSet rs = ps.executeQuery();
            System.out.println("Transaction History:");
            while(rs.next()){
                System.out.println(rs.getString("type") + "Rs" + rs.getDouble("amount") + "on" + rs.getTimestamp("timestamp"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

