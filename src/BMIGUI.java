import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMIGUI extends JFrame {
    // Creating instance variables for the components
    private JLabel weightLbl, heightLbl, resultLbl, categoryLbl;
    private JTextField weightField, heightField;
    private JButton calculateBtn;

    public BMIGUI() {
        super("BMI"); // calling constructor of parent class and setting title of JFrame

        // Initialize all the components
        weightLbl = new JLabel("Weight (in kg):");
        heightLbl = new JLabel("Height (in m):");
        resultLbl = new JLabel();
        categoryLbl = new JLabel();

        weightField = new JTextField(10);
        heightField = new JTextField(10);

        calculateBtn = new JButton("Get BMI");

        // Add an ActionListener to the calculateBtn to handle clicks
        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialising the variables to hold the weight and height entered by user
                double weight = 0, height = 0;

                try {
                    // try to parse the weight and height as doubles from user input
                    weight = Double.parseDouble(weightField.getText());
                    height = Double.parseDouble(heightField.getText());
                } catch (NumberFormatException ex) {
                    // If the input isn't valid, show an error message and return
                    JOptionPane.showMessageDialog(BMIGUI.this,
                            "Please enter a valid number for weight and height",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (weight > 200 || height > 2.5) {
                    // If the weight or height is too high, show an error message and return
                    JOptionPane.showMessageDialog(BMIGUI.this,
                            "Please enter a weight below 200 kg and a height below 2.5 m",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new instance of BMICalculator class and calculate the BMI
                BMICalculator bmiCalculator = new BMICalculator(weight, height);
                double bmi = bmiCalculator.calculateBMI();

                // Get the weight category from BMICalculator class
                String category = bmiCalculator.getWeightCat(bmi);

                // Update resultLbl and categoryLbl with the calculated BMI and weight category
                resultLbl.setText(String.format("Your BMI is %.2f", bmi));
                categoryLbl.setText(String.format("You are %s", category));
            }
        });

        // Set layout of the JFrame to a 5x2 grid
        setLayout(new GridLayout(5, 2));

        // Adding all components to the JFrame
        add(weightLbl);
        add(weightField);
        add(heightLbl);
        add(heightField);
        add(new JLabel()); // Add an empty label to space out the components
        add(calculateBtn);
        add(resultLbl);
        add(categoryLbl);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes tab when you press X
        setResizable(false); // JFrame not resizable
        setSize(400,200); // Size of JFrame
        setLocationRelativeTo(null); // Center JFrame on the screen
        setVisible(true); // Make JFrame visible
    }
}