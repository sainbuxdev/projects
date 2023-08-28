import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author San
 */
public class TREClient {
    public static void main(String[] args) {
        try {
            // Obtain a reference to the remote GTO server object
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            GTOInterface gtoServer = (GTOInterface) registry.lookup("GTO_SERVER");

            // Accept user input for annual income and tax withheld
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter annual taxable income: ");
            double annualIncome = scanner.nextDouble();
            System.out.print("Enter tax withheld: ");
            double taxWithheld = scanner.nextDouble();

            // Invoke the remote method on the GTO server to calculate tax estimate
            double taxEstimate = gtoServer.calculateTaxEstimate(annualIncome, taxWithheld);

            // Display the calculated tax estimate
            System.out.println("Tax Estimate: " + taxEstimate);

            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
}
