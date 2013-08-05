/**
 * Name: Rajesh Somavarapu
 * NetID: rxs125730
 * Date created: 13 October 2012
 * Purpose: Assignment 2 (Object-Oriented Program)
 * 
 */

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.NumberFormat;
import javax.swing.*;

   public class amortizationGraph extends JPanel {
        
	double principalAmount;
	double interestRate;
        
	JFrame chartFrame = null;
	JScrollPane scrollingPane = null;
	double[] dGraph = new double[amortization.tMonths];
	final int PAD = 100;
        private double monthlyPayment;
        private double interestPaid;
        private double principalPaid;

	 //This class adds the table to the JFrame

	public void getGraphInfo(String principalAmountTextField, String interestRateTextField, String termMonthsTextField) {
            
            double principalAmount = Double.parseDouble(principalAmountTextField); //principal amount
            double interestRate = Double.parseDouble(interestRateTextField); //interest rate
            int termMonths = Integer.parseInt(termMonthsTextField);  // term (in months)
            double newPrincipal = principalAmount; 
            interestRate = interestRate/100.0;
            double monthlyInterestRate = interestRate/12.0; //monthly interest rate
            monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1+monthlyInterestRate, -termMonths))); //calculated monthly payment
		
		 if (principalAmount > 0 && interestRate != 0 && termMonths != 0) { 
            
                    for (int numberOfTerms = 0; numberOfTerms <termMonths; numberOfTerms++) { //the data is calculated for each of the terms
              
                        interestPaid = principalAmount * (monthlyInterestRate); //interest paid
                        principalPaid = monthlyPayment - interestPaid; //principal paid
                        principalAmount = principalAmount - principalPaid; //principal amount
                        newPrincipal = principalAmount;
                        this.dGraph[numberOfTerms] = principalAmount; // graph is drawn ( number of terms vs remaining balance)
                        
                    }
           
                }
		  
	        JFrame f = new JFrame();
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        JScrollPane scrollPane = new JScrollPane(this);
	        scrollPane.setBounds(10, 101, 742, 276);
	           
	        f.add(this);
	        f.setSize(500,500);
	        f.setLocation(200,200);
                f.setTitle("Amortization Graph"); //the title of the table
	        f.setVisible(true);
	        f.setBackground(Color.WHITE);
	  
	}

       //paint component     
        
        protected void paintComponent(Graphics g) {
            
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD)); // draw the y-axis
        
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD)); // draw the labels
        
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        
        String s = "Remaining Amount"; // x-axis label
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
            
        }
       
        s = "Number of Terms"; //y-axis label
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        
        double xInc = (double)(w - 2*PAD)/(dGraph.length-1); //draw the lines
        double scale = (double)(h - 2*PAD)/getMax();
        g2.setPaint(Color.green.darker());
        
        for(int i = 0; i < dGraph.length-1; i++) {
            
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*dGraph[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*dGraph[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
            
        }
         
        //draw the data points
        
        g2.setPaint(Color.blue);
        
        for(int i = 0; i < dGraph.length; i++) {
            
            double x = PAD + i*xInc;
            double y = h - PAD - scale*dGraph[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            
        }
        
    }
 
    private double getMax() {
        
        double max = -Double.MAX_VALUE;
        
        for(int i = 0; i < dGraph.length; i++) {
            
            if(dGraph[i] > max)
                max = dGraph[i];
            
        }
        
        return max;
        
    }
 
}