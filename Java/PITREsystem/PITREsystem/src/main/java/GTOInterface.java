import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author San
 */
public interface GTOInterface extends Remote{
    double calculateTaxEstimate(double annualTaxableIncome, double taxWithheld) throws RemoteException;

    double calculatePayrollTax(double grossWage) throws RemoteException;

    boolean storeTaxReport(TaxReport taxReport) throws RemoteException;
    
}
