//Software Engineering with Test
// Team Project - GearCalculator
// Rokas Cesiunas

// ResultFrame.java
// A JFrame class to display the result calculated from main GUI Frame
// Shows summary of data entered and calculated profit / loss

package ie.gmit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultFrame {

    // private ints to set default location on screen when opened
    private final int defaultX = 20;
    private final int defaultY = 20;
    // private ints to set default window size
    private final int defaultWidth = 600;
    private final int defaultHeight = 500;

    // panels to organize GUI
    private JPanel leftPanel, rightPanel, topPanel, bottomPanel;

    // HashMap to store JTextField references for quick access
    private HashMap fields;

    // static Strings that represent name of each text field.
    // These are placed on JLabels and used as keys in
    // HashMap fields.
    private static final String
            GEAR_SELL_PRICE = "Gear Price",
            TOTAL_MATERIAL_COST = "Total Material Cost (-)",
            GEAR_SELL_COMMISSION = "Gear Sell Commission (-)",
            GEAR_SELL_DEPOSIT = "Gear Sell Deposit (-)",
            PROFIT_LOSS = "Profit / Loss";

    Font labelFont = new Font("Monospaced", Font.PLAIN, 15);
    Font numberFont = new Font("Serif", Font.BOLD, 30);
    Font profitFont = new Font("Monospaced", Font.BOLD, 55);

    Border numberBorder = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.DARK_GRAY);

    // default row number for ResultJPanel
    private static final int frameRows = 4;

    // private ints to store data from main frame
    private double gearSalePrice = 0.0f;
    private double totalMaterialSaleCost = 0.0f;
    private double saleCommission = 0.0f;
    private double saleDeposit = 0.0f;
    private double totalProfit = 0.0f;

    // construct default GUI
    public ResultFrame(float gearSalePrice, float totalMaterialSaleCost, float saleCommission, float saleDeposit, float totalProfit) {
        logger.log(Level.FINE, "created JFrame, setting GUI");

        this.gearSalePrice = gearSalePrice;
        this.totalMaterialSaleCost = totalMaterialSaleCost;
        this.saleCommission = saleCommission;
        this.saleDeposit = saleDeposit;
        this.totalProfit = totalProfit;

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Calculated Result");
        frame.setLayout(new GridLayout(2, 1, 0, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fields = new HashMap();

        leftPanel = new JPanel();
        leftPanel.setLayout( new GridLayout( frameRows, 1));
        rightPanel = new JPanel();
        rightPanel.setLayout( new GridLayout( frameRows, 1));

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        topPanel.add(leftPanel);
        topPanel.add(rightPanel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout( new BorderLayout(5, 0));

        createRow(GEAR_SELL_PRICE);
        createRow(TOTAL_MATERIAL_COST);
        createRow(GEAR_SELL_COMMISSION);
        createRow(GEAR_SELL_DEPOSIT);
        createRow(PROFIT_LOSS);

        setField(GEAR_SELL_PRICE, String.valueOf(gearSalePrice));
        setField(TOTAL_MATERIAL_COST, String.valueOf(totalMaterialSaleCost));
        setField(GEAR_SELL_COMMISSION, String.valueOf(saleCommission));
        setField(GEAR_SELL_DEPOSIT, String.valueOf(saleDeposit));
        setField(PROFIT_LOSS, totalProfit + " Shekels");

        JTextField field = (JTextField) fields.get(PROFIT_LOSS);

        if (totalProfit <= 0) {
            field.setBackground(Color.RED);
        } else {
            field.setBackground(Color.GREEN);
        }

        rightPanel.setBorder(numberBorder);
        bottomPanel.setBackground(Color.ORANGE);

        frame.add(topPanel);
        frame.add(bottomPanel);

        frame.setBounds(defaultX, defaultY, defaultWidth, defaultHeight);

        frame.setVisible(true);

        logger.log(Level.FINE, "default constructor created");
    }

    // set text in JTextField by specifying field's
    // name and value
    private void setField( String fieldName, String value )
    {
        JTextField field =
                ( JTextField ) fields.get(fieldName);

        field.setText(value);
    }

    // get text in JTextField by specifying field's name
    public String getField( String fieldName )
    {
        JTextField field = (JTextField) fields.get(fieldName);
        return field.getText();
    }

    // utility method used by constructor to create one row in
    // GUI containing JLabel and JTextField
    private void createRow( String name )
    {
        logger.log(Level.FINE, "Creating Row");

        JLabel label = new JLabel( name, SwingConstants.RIGHT );
        label.setFont(labelFont);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
        if (name.equals(PROFIT_LOSS))
            bottomPanel.add(label, BorderLayout.NORTH);
        else
            leftPanel.add(label);

        JTextField field = new JTextField( 30 );
        field.setEditable(false);
        field.setFont(numberFont);
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        if (name.equals(PROFIT_LOSS)) {
            field.setFont(profitFont);
            bottomPanel.add(field, BorderLayout.CENTER);
        } else
            rightPanel.add(field);

        fields.put(name, field);

        logger.log(Level.FINE, "Created Row");
    }

    public double getGearSalePrice() {
        return gearSalePrice;
    }

    public double getTotalMaterialSaleCost() {
        return totalMaterialSaleCost;
    }

    public double getSaleCommission() {
        return saleCommission;
    }

    public double getSaleDeposit() {
        return saleDeposit;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    private static final Logger logger =
            Logger.getLogger(ResultFrame.class.getName());
}
