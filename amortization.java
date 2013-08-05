/**
 * Name: Rajesh Somavarapu
 * NetID: rxs125730
 * Date created: 13 October 2012
 * Purpose: Assignment 2 (Object-Oriented Program)
 * CS6359 : Object Oriented Analysis and Design
 */


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

//class which contains the GUI initialization and main method

public class amortization extends JFrame {

     public static int tMonths;
    public amortization() {
        initUI();
    }

    //this method is used to initialize the GUI

    public final void initUI() {

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel(new GridLayout(6,1,10,10));
        JPanel panel2= new JPanel();
        getContentPane().add(panel);

        JLabel principalAmountLabel = new JLabel("Principal Amount: ");     //Label to display the principal amount
        JLabel interestRateLabel = new JLabel("Annual Interest Rate: ");    //Label to display the annual interest rate
        JLabel termMonthsLabel = new JLabel("Term (in months): ");          //Label to display the term in months
        JLabel monthlyPaymentLabel = new JLabel("Monthly Payment: ");       //Label to display the monthly payment
        JLabel totalPaymentsLabel = new JLabel("Total Payments: ");         //Label to display the total payments

        final JTextField principalAmountTextField = new JTextField();       //Text field to input the principal amount
        final JTextField interestRateTextField = new JTextField();          //Text field to input the interest rate
        final JTextField termMonthsTextField = new JTextField();            //Text field to input the term in months
        final JTextField monthlyPaymentTextField = new JTextField();        //Text field used to display the calculated monthly payment
        final JTextField totalPaymentsTextField = new JTextField();         //Text field used to display the calculated total payments

        monthlyPaymentTextField.setEditable(false);  //Disabled text field
        totalPaymentsTextField.setEditable(false);   //disabled text field

        GridLayout grid1= new GridLayout(6,1,10,10);

        principalAmountLabel.setHorizontalAlignment(JLabel.RIGHT);
        interestRateLabel.setHorizontalAlignment(JLabel.RIGHT);
        termMonthsLabel.setHorizontalAlignment(JLabel.RIGHT);
        monthlyPaymentLabel.setHorizontalAlignment(JLabel.RIGHT);
        totalPaymentsLabel.setHorizontalAlignment(JLabel.RIGHT);


        JButton tableButton = new JButton("Generate Table");    //button to display the table
        JButton graphButton = new JButton("Generate Graph");    //button to display the graph

        //tableButton.setEnabled(false); //table button is disabled initially
        //graphButton.setEnabled(false); //graph button is disabled initially

        tableButton.setToolTipText("Click to generate the table");
        graphButton.setToolTipText("Click to generate the graph");

        panel1.add(principalAmountLabel);
        panel1.add(interestRateLabel);
        panel1.add(termMonthsLabel);
        panel1.add(tableButton);
        panel1.add(monthlyPaymentLabel);
        panel1.add(totalPaymentsLabel);

        //Action Listener to the table button

        tableButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try { //checks whether the entered values for principal amount, interest rate and term (in months) are numeric or not

                    float pAmount, iRate,tMonths;
                    pAmount = Float.parseFloat(principalAmountTextField.getText());
                    iRate = Float.parseFloat(interestRateTextField.getText());
                    tMonths = Float.parseFloat(termMonthsTextField.getText());

                } catch (NumberFormatException exception) { // number format exception is caught when these values are not numeric

                    JOptionPane.showConfirmDialog(null, "Please enter numberic values only", "Exception", JOptionPane.PLAIN_MESSAGE);

                }

                amortizationCalculation am = new amortizationCalculation();
                String payInfo = null;
                payInfo = am.getpayInfo(principalAmountTextField.getText(),interestRateTextField.getText(),termMonthsTextField.getText());


                String mPay = payInfo.split("####")[0];
                String tPay = payInfo.split("####")[1];

                monthlyPaymentTextField.setText(mPay); //monthly payment
                totalPaymentsTextField.setText(tPay);  //total payment

                amortizationTable amT = new amortizationTable(); // an object of the amortization table
                amT.getTableInfo(principalAmountTextField.getText(),interestRateTextField.getText(),termMonthsTextField.getText());

            }

        });

        //action listener to the graph button

        graphButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try { //checks whether the entered values for principal amount, interest rate and term (in months) are numeric or not

                    float pAmount, iRate,tMonths;
                    pAmount = Float.parseFloat(principalAmountTextField.getText());
                    iRate = Float.parseFloat(interestRateTextField.getText());
                    tMonths = Float.parseFloat(termMonthsTextField.getText());

                } catch (NumberFormatException exception) { // number format exception is caught when these values are not numeric

                    JOptionPane.showConfirmDialog(null, "Please enter numberic values only", "Exception", JOptionPane.PLAIN_MESSAGE);

                }

                amortizationCalculation am = new amortizationCalculation();
                String payInfo = null;
                payInfo = am.getpayInfo(principalAmountTextField.getText(),interestRateTextField.getText(),termMonthsTextField.getText());

                String mPay = payInfo.split("####")[0]; // monthly payment
                String tPay = payInfo.split("####")[1]; //total payment
                monthlyPaymentTextField.setText(mPay);
                totalPaymentsTextField.setText(tPay);
                tMonths = Integer.parseInt(termMonthsTextField.getText());
                amortizationGraph amG = new amortizationGraph();
                System.out.println("check here:"+tMonths);
                amG.getGraphInfo(principalAmountTextField.getText(),interestRateTextField.getText(),termMonthsTextField.getText());

            }

        });

        panel2.setLayout(grid1);
        panel2.add(principalAmountTextField);
        panel2.add(interestRateTextField);
        panel2.add(termMonthsTextField);
        panel2.add(graphButton);
        panel2.add(monthlyPaymentTextField);
        panel2.add(totalPaymentsTextField);

        panel.setLayout(new GridLayout(1,2));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panel.add(panel1);
        panel.add(panel2);

        setTitle("Amortization Calculator");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

      }

    //main method where the program starts

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                amortization ex = new amortization();
                ex.setVisible(true);

            }

        });

    }

}

