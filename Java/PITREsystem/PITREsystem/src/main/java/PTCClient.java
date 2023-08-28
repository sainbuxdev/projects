import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author San
 */
public class PTCClient {
    public static void main(String[] args) {
        try {
            // Obtain a reference to the remote GTO server object
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            GTOInterface gtoServer = (GTOInterface) registry.lookup("GTO_SERVER");

            // Accept user input for employer information
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employer National Business Number (NBN): ");
            String employerNBN = scanner.nextLine();
            System.out.print("Enter employer business name: ");
            String employerName = scanner.nextLine();
            System.out.print("Enter employer location: ");
            String employerLocation = scanner.nextLine();

            // Accept user input for number of employees
            System.out.print("Enter the number of employees: ");
            int numEmployees = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            List<TaxRecord> taxRecords = new ArrayList<>();

            // Accept employee information and calculate taxes for each employee
            for (int i = 0; i < numEmployees; i++) {
                System.out.println("\nEmployee #" + (i + 1));

                System.out.print("Enter employee ID: ");
                String employeeId = scanner.nextLine();

                System.out.print("Enter employee name: ");
                String employeeName = scanner.nextLine();

                System.out.print("Enter Tax File Number (TFN): ");
                String tfn = scanner.nextLine();

                System.out.print("Enter pay period (start date - end date): ");
                String payPeriod = scanner.nextLine();

                System.out.print("Enter date of payday: ");
                String payday = scanner.nextLine();

                System.out.print("Enter gross wage/salary: ");
                double grossWage = scanner.nextDouble();

                // Calculate tax levied on the gross wage using the GTO server
                double taxLevied = gtoServer.calculatePayrollTax(grossWage);

                // Calculate net wage/salary
                double netWage = grossWage - taxLevied;

                // Create a tax record for the employee
                TaxRecord taxRecord = new TaxRecord(employeeId, employeeName, tfn, payPeriod, payday, grossWage, taxLevied, netWage);
                taxRecords.add(taxRecord);
            }

            // Generate the tax report
            TaxReport taxReport = new TaxReport(employerNBN, employerName, employerLocation, taxRecords);

            // Invoke the remote method on the GTO server to store the tax report in PITD
            boolean isReportStored = gtoServer.storeTaxReport(taxReport);

            if (isReportStored) {
                System.out.println("\nTax report has been successfully stored in PITD.");
            } else {
                System.out.println("\nFailed to store the tax report in PITD.");
            }

            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
}
