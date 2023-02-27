import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WorkoutCreator {

    // declare variables in gui
    private JFrame workoutFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JTextField workoutField;
    private JTextField setsField;
    private JTextField repsField;
    private JButton addButton;
    private JButton viewButton;
    private JButton deleteButton;

    // declaring a 2d array in order to store user's workouts
    String[][] workouts;
    int numWorkouts;



    public WorkoutCreator() {

       GUI();
        //allows user to store 10 workouts with 3 values - name,sets,reps
        workouts = new String[10][3];
        // tracks number of workouts stored in array
        numWorkouts = 0;
    }

    private void GUI() {
        workoutFrame = new JFrame("Workout Creator");
        workoutFrame.setSize(400, 400);
        workoutFrame.setLayout(new GridLayout(3, 1));


        workoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        workoutFrame.setLocationRelativeTo(null);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);


        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        workoutFrame.add(headerLabel);
        workoutFrame.add(controlPanel);
        workoutFrame.add(statusLabel);
        workoutFrame.setVisible(true);
    }

    // Method to show the event
    void showEvent() {
        headerLabel.setText("Create Your Own Workout Below:");

        JLabel workoutLabel = new JLabel("Workout: ", JLabel.RIGHT);
        JLabel setsLabel = new JLabel("Sets: ", JLabel.RIGHT);
        JLabel repsLabel = new JLabel("Reps: ", JLabel.RIGHT);

        workoutField = new JTextField(6);
        setsField = new JTextField(3);
        repsField = new JTextField(3);

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        deleteButton = new JButton("Delete");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String workout = workoutField.getText();
                String sets = setsField.getText();
                String reps = repsField.getText();
                //checks if number of workouts stored in array is less than 10
                if (numWorkouts < 10) {
                    //if workouts are less than 10, store the workouts, sets and reps
                    workouts[numWorkouts][0] = workout;
                    workouts[numWorkouts][1] = sets;
                    workouts[numWorkouts][2] = reps;
                    //increment by 1 everytime a new workout is added
                    numWorkouts++;
                    workoutField.setText("");
                    setsField.setText("");
                    repsField.setText("");
                    statusLabel.setText("Workout added to the list!");
                } else {
                    statusLabel.setText("The list is full. In order to add a new workout delete a current one.");
                }
            }
        });
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                //iterate through the workouts array
                for (int i = 0; i < numWorkouts; i++) {
                    //adds a string to the sb object
                    //name of workout , sets , reps
                    sb.append(workouts[i][0] + " | " + workouts[i][1] + " sets | " + workouts[i][2] + " reps\n");
                }
                if (numWorkouts == 0) {
                    sb.append("You have no workouts.");
                }
                JOptionPane.showMessageDialog(workoutFrame, sb.toString());
            }
        });

        //method to delete a workout from the array
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String workout = JOptionPane.showInputDialog(workoutFrame, "Which workout would you like to delete:");
                //check if workout was found in array
                boolean found = false;
                for (int i = 0; i < numWorkouts; i++) {
                    //check if workout name entered is the same as workout in the array
                    if (workout.equals(workouts[i][0])) {
                        //set true if same workout found
                        found = true;
                        for (int j = i; j < numWorkouts - 1; j++) {
                            workouts[j][0] = workouts[j+1][0];
                            workouts[j][1] = workouts[j+1][1];
                            workouts[j][2] = workouts[j+1][2];
                        }
                        //decrement the number of workouts
                        numWorkouts--;
                        //break out of the loop if the workout is found
                        break;
                    }
                }
                if (found) {
                    statusLabel.setText("Workout deleted from the list!");
                } else {
                    statusLabel.setText("Workout not found in the list.");
                }
            }
        });

        controlPanel.add(workoutLabel);
        controlPanel.add(workoutField);
        controlPanel.add(setsLabel);
        controlPanel.add(setsField);
        controlPanel.add(repsLabel);
        controlPanel.add(repsField);
        controlPanel.add(addButton);
        controlPanel.add(viewButton);
        controlPanel.add(deleteButton);
        workoutFrame.setVisible(true);
    }
}

