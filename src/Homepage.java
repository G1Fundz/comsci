import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Homepage extends JFrame implements ActionListener { // inheriting JFrame


    JFrame home; //create home jframe
    JButton button = new JButton("BMI Calculator"); //create bmi calculator button
    JButton button2 = new JButton("Stopwatch"); //create stopwatch button
    JButton button3 = new JButton("Workouts"); //create workouts button
    JButton button4 = new JButton("Meal Plan"); //create meal plan button

    Homepage() {
        setTitle("Homepage"); //setting title fo jframe to Homepage


        add(button); // adding bmi button on frame
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBounds(100, 100, 200, 40);
        button.setFont(new Font("Comic Sans",Font.BOLD,15));



        add(button2);  //adding stopwatch button on frame
        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setBounds(100, 160, 200, 40);
        button2.setFont(new Font("Comic Sans",Font.BOLD,15));



        add(button3); //adding workouts button on frame
        button3.addActionListener(this);
        button3.setFocusable(false);
        button3.setBounds(100, 220, 200, 40);
        button3.setFont(new Font("Comic Sans",Font.BOLD,15));



        add(button4);  //adding meal plan button on frame
        button4.addActionListener(this);
        button4.setFocusable(false);
        button4.setBounds(100, 280, 200, 40);
        button4.setFont(new Font("Comic Sans",Font.BOLD,15));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close frame when you press X
        setSize(400, 500); //size of frame
        setLayout(null); //sets absolute positioning of frame
        setVisible(true); //frame is displayed
        setResizable(false); //cant resize frame
        setLocationRelativeTo(null); //centres frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            new BMIGUI();

        } else if (e.getSource() == button2){

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.setTitle("Stopwatch");
            stopwatch.pack();
            stopwatch.setVisible(true);
        } else if(e.getSource() == button3) {

            WorkoutCreator app = new WorkoutCreator();
            app.showEvent();

        } else if(e.getSource() == button4 ) {

            EventQueue.invokeLater(() -> {
                MealPrep app = new MealPrep();
                app.setVisible(true);
            });
        }
    }
}
