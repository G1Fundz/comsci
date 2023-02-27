import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class MealPrep extends JFrame {
    // components in the gui
    private JPanel mealFrame;
    private JLabel label;
    private JButton addMealButton;
    private JButton viewAllMealsButton;

    // file to store the meals and their calorie count
    private File mealFile = new File("meals.txt");

    // hash map which stores the meals and the amount of calories they contain
    private Map<String, Integer> meals = new HashMap<>();

    public MealPrep() {
        setTitle("Meal Prep");
        setSize(400, 200);
        setLocationRelativeTo(null);

        label = new JLabel("Insert your meal here!");

        addMealButton = new JButton("Add Meal");
        addMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //allow user to input meal
                String meal = JOptionPane.showInputDialog(mealFrame, "Enter a meal:");
                //allow user to input calories associated with meal
                String calories = JOptionPane.showInputDialog(mealFrame, "Enter the number of calories for this meal:");
                //converts calories from a string into an integer
                int calorieCount = Integer.parseInt(calories);
                //adds meal and calories to map
                meals.put(meal, calorieCount);
                //writes the meal to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(mealFile, true))) {
                    writer.println(meal + "," + calorieCount);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        viewAllMealsButton = new JButton("View Meals");
        viewAllMealsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                //loop through map and append each meal and calories to sb
                for (Map.Entry<String, Integer> entry : meals.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append(": ");
                    sb.append(entry.getValue());
                    sb.append(" calories");
                    sb.append("\n");
                }
                //print all meals and calories
                JOptionPane.showMessageDialog(mealFrame, sb.toString());
            }
        });

        // check if the file exists and read any existing meals
        if (mealFile.exists()) {
            try (Scanner scanner = new Scanner(mealFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] splitLine = line.split(",");
                    String meal = splitLine[0];
                    int calorieCount = Integer.parseInt(splitLine[1]);
                    meals.put(meal, calorieCount);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        mealFrame = new JPanel();
        mealFrame.add(label);
        mealFrame.add(addMealButton);
        mealFrame.add(viewAllMealsButton);
        add(mealFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}