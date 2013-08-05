/**
 * Name: Rajesh Somavarapu
 * NetID: rxs125730
 * Date created: 13 October 2012
 * Purpose: Assignment 2 (Object-Oriented Program)
 * 
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;

//amortization table 

public class amortizationTable extends JFrame {

    double interestPaid;
    double principalPaid;
    double monthlyPayment;

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    //used to get the table information
    //Input: Principal amount, interest rate and term (in months)
 
    public void getTableInfo (String principalAmountTextField, String interestRateTextField, String termMonthsTextField){
  
        double principalAmount = Double.parseDouble(principalAmountTextField); //parsed principal amount from the principal amount text field
        double interestRate = Double.parseDouble(interestRateTextField);  //parsed interest rate from the interest rate text field
        int termMonths = Integer.parseInt(termMonthsTextField);  ////parsed term in months from the term (in months) text field
        String[] columnNames = {"Payment Number", "Begin Balance", "Interest","Principal", "End Balance"}; // column names

        for (int columnLength = 0; columnLength < 5; columnLength++) {
            model.addColumn(columnNames[columnLength]);
        }

        if (principalAmount > 0 && interestRate != 0 && termMonths != 0) {
            double newPrincipal = principalAmount;
            interestRate = interestRate/100.0;
            double monthlyInterestRate = interestRate/12.0;
            monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1+monthlyInterestRate, -termMonths))); //calculated monthly payment
            double monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1+monthlyInterestRate, -termMonths)));
            
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            
            for (int numberOfTerms = 0; numberOfTerms <termMonths; numberOfTerms++) {
                Object data[] = new Object[5];
                data[0] = Integer.toString(numberOfTerms + 1); //payment number
                data[1] = nf.format(newPrincipal);  //begin balance
                data[2] = nf.format(interestPaid = principalAmount * (monthlyInterestRate)); //interest paid
                data[3] = nf.format(principalPaid = monthlyPayment - interestPaid); //principal paid
                data[4] = nf.format(principalAmount = principalAmount - principalPaid); //ending balance
                newPrincipal = principalAmount;
                model.addRow(data);
            }
        }

        JFrame f = new JFrame();
        f.setSize(600,300);
        f.setTitle("Amortization Table"); //the title of the table
        f.add(new JScrollPane(table));
        f.setVisible(true);
     }
}

    

