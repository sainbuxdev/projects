/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author San
 */
public class TaxRecord {
      private String employeeId;
    private double annualTaxableIncome;
    private double taxWithheld;

    public TaxRecord(String employeeId, String employeeName, String tfn, String payPeriod, String payday, double annualTaxableIncome, double taxWithheld, double netWage) {
        this.employeeId = employeeId;
        this.annualTaxableIncome = annualTaxableIncome;
        this.taxWithheld = taxWithheld;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public double getAnnualTaxableIncome() {
        return annualTaxableIncome;
    }

    public double getTaxWithheld() {
        return taxWithheld;
    }
    
}
