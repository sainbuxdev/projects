import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author San
 */
public class TaxReport {
     private String employerNBN;
    private String employerName;
    private String employerLocation;
    private List<TaxRecord> taxRecords;

    public TaxReport(String employerNBN, String employerName, String employerLocation, List<TaxRecord> taxRecords) {
        this.employerNBN = employerNBN;
        this.employerName = employerName;
        this.employerLocation = employerLocation;
        this.taxRecords = taxRecords;
    }

    public String getEmployerNBN() {
        return employerNBN;
    }

    public String getEmployerName() {
        return employerName;
    }

    public String getEmployerLocation() {
        return employerLocation;
    }

    public List<TaxRecord> getTaxRecords() {
        return taxRecords;
    }
    
}
