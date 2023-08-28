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
public class PITDServer {
    private Map<String, TaxReport> taxReports;

    public PITDServer() {
        taxReports = new HashMap<>();
    }

    public boolean storeTaxReport(TaxReport taxReport) {
        // Store the tax report in the PITD database
        taxReports.put(taxReport.getEmployerNBN(), taxReport);

        // Placeholder implementation, always return true
        return true;
    }

    public TaxReport getTaxReport(String employerNBN) {
        // Retrieve the tax report from the PITD database
        return taxReports.get(employerNBN);
    }
    
}
