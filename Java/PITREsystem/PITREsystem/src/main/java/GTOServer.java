import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author San
 */
public class GTOServer implements GTOInterface {
    private Map<String, TaxRecord> taxRecords;

    public GTOServer() {
        taxRecords = new HashMap<>();
    }

    public static void main(String[] args) {
        try {
            // Create an instance of the GTO server
            GTOServer gtoServer = new GTOServer();

            // Export the GTO server object
            GTOInterface stub = (GTOInterface) UnicastRemoteObject.exportObject(gtoServer, 0);

            // Bind the GTO server object to the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("GTO_SERVER", stub);

            System.out.println("GTOServer is running and waiting for client requests...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public double calculateTaxEstimate(double annualTaxableIncome, double taxWithheld) throws RemoteException {
        // Perform tax return calculation or estimate based on the provided information
        double medicareLevy = annualTaxableIncome * 0.02;

        // Medicare Levy Surcharge calculation
        double medicareLevySurcharge = 0.0;
        if (annualTaxableIncome > 90000 && annualTaxableIncome <= 105000) {
            medicareLevySurcharge = annualTaxableIncome * 0.01;
        } else if (annualTaxableIncome > 105000 && annualTaxableIncome <= 140000) {
            medicareLevySurcharge = annualTaxableIncome * 0.0125;
        } else if (annualTaxableIncome > 140000) {
            medicareLevySurcharge = annualTaxableIncome * 0.015;
        }

        double taxEstimate = annualTaxableIncome - taxWithheld - medicareLevy - medicareLevySurcharge;

        return taxEstimate;
    }

    @Override
    public double calculatePayrollTax(double grossWage) throws RemoteException {
        // Perform payroll tax calculation based on the provided gross wage
        double taxLevied = grossWage * 0.15;

        // Additional tax components like Medicare Levy and Medicare Levy Surcharge
        double medicareLevy = grossWage * 0.02;
        double medicareLevySurcharge = 0.0; // Placeholder calculation

        taxLevied += medicareLevy + medicareLevySurcharge;

        return taxLevied;
    }

    @Override
    public boolean storeTaxReport(TaxReport taxReport) throws RemoteException {
        // Store the tax report in the PITD database
        // Your database storage logic here
        // You can access the tax report data using taxReport.getEmployerNBN(),
        // taxReport.getEmployerName(), taxReport.getEmployerLocation(), and
        // taxReport.getTaxRecords()

        // Store the tax records in the taxRecords map
        for (TaxRecord taxRecord : taxReport.getTaxRecords()) {
            taxRecords.put(taxRecord.getEmployeeId(), taxRecord);
        }

        return true;
    }
    
}
