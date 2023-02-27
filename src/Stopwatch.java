import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener {

    // Declare variables
    private JLabel timeLbl;
    private JButton startBtn;
    private JButton stopBtn;
    private JButton resetButton;
    private JButton lapBtn;
    private Timer timer;
    private long start;
    private long stop;
    private boolean hasStarted;
    private int laps;
    private DecimalFormat formatter;


    public Stopwatch() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set up the GUI
        timeLbl = new JLabel("00:00:00.000", SwingConstants.CENTER);
        timeLbl.setFont(new Font("SansSerif", Font.PLAIN, 48));
        timeLbl.setPreferredSize(new Dimension(400, 100));


        //buttons
        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        resetButton = new JButton("Reset");
        lapBtn = new JButton("Lap");


        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        resetButton.addActionListener(this);
        lapBtn.addActionListener(this);

        //add the buttons to the panel
        JPanel btnPanel = new JPanel();
        btnPanel.add(startBtn);
        btnPanel.add(stopBtn);
        btnPanel.add(resetButton);
        btnPanel.add(lapBtn);

        JPanel lapPanel = new JPanel();
        lapPanel.setLayout(new BoxLayout(lapPanel, BoxLayout.Y_AXIS)); //vertical layout
        lapPanel.setBorder(BorderFactory.createTitledBorder("Laps")); //setting the title of the border
        lapPanel.setPreferredSize(new Dimension(400, 200)); //setting the size


        setLayout(new BorderLayout());
        add(timeLbl, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);
        add(lapPanel, BorderLayout.CENTER);

        // set up the timer
        timer = new Timer(100, this);
        timer.setInitialDelay(0);

        // set up the lap feature
        formatter = new DecimalFormat("00");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer) {

            // Calculate elapsed time in milliseconds by subtracting `start` time from current system time
            long elapsedTime = System.currentTimeMillis() - start;

            // Calculate number of hours elapsed by dividing elapsed time in milliseconds by number of milliseconds in an hour
            int hours = (int) (elapsedTime / 3600000);

            // Calculate number of elapsed minutes by taking the remainder of elapsed time in milliseconds after dividing by the number of milliseconds in an hour
            // Then divide by the number of milliseconds in a minute
            int minutes = (int) ((elapsedTime % 3600000) / 60000);

            // Calculate number of seconds elapsed by taking the remainder of the millisecond elapsed(in s) after dividing by the number of milliseconds in a minute
            // Then dividing by the number of milliseconds in a second
            int seconds = (int) ((elapsedTime % 60000) / 1000);

            // Calculate the number of milliseconds elapsed by taking the remainder of the elapsed time in milliseconds after dividing by the number of milliseconds in a second
            int millis = (int) (elapsedTime % 1000);

            // Set the text of the `timeLbl` label to a formatted string containing the elapsed time in hours, minutes, seconds, and milliseconds
            timeLbl.setText(String.format("%s:%s:%s.%s", formatter.format(hours), formatter.format(minutes), formatter.format(seconds), formatter.format(millis)));
        } else if (event.getSource() == startBtn) {

            // start the timer
            start = System.currentTimeMillis
                    ();
            timer.start();
            hasStarted = true;
        } else if (event.getSource() == stopBtn) {

            // stop the timer
            stop = System.currentTimeMillis();
            timer.stop();
            hasStarted = false;
        } else if (event.getSource() == resetButton) {

            // reset the timer
            timer.stop();
            hasStarted = false;
            start = 0;
            stop = 0;
            laps = 0;
            timeLbl.setText("00:00:00.000");
            ((JPanel) getContentPane().getComponent(2)).removeAll();
            ((JPanel) getContentPane().getComponent(2)).repaint();
        } else if (event.getSource() == lapBtn) {

            if (laps < 5) { // Check if number of laps recorded is less than 5
                long lapTime = System.currentTimeMillis() - start; // Calculate elapsed time for the lap
                int hours = (int) (lapTime / 3600000); // Convert elapsed time to hours
                int minutes = (int) ((lapTime % 3600000) / 60000); // Convert the remaining time to minutes
                int seconds = (int) ((lapTime % 60000) / 1000); // Convert the remaining time to seconds
                int millis = (int) (lapTime % 1000); // Get the remaining milliseconds
                String lapString = String.format("Lap %d: %s:%s:%s.%s", ++laps, formatter.format(hours), formatter.format(minutes), formatter.format(seconds), formatter.format(millis)); // Create a string to display the lap time
                JLabel lapLabel = new JLabel(lapString, SwingConstants.LEFT); // Create JLabel to display the lap time
                lapLabel.setFont(new Font("SansSerif", Font.PLAIN, 24)); // Set font of the JLabel
                lapLabel.setPreferredSize(new Dimension(400, 30)); // Set preferred size of the JLabel
                ((JPanel) getContentPane().getComponent(2)).add(lapLabel); // Add JLabel to the content pane
                ((JPanel) getContentPane().getComponent(2)).revalidate(); // Revalidate content pane to update the display
            } else {
                JOptionPane.showMessageDialog(this, "Cannot record more than 5 laps!", "Error", JOptionPane.ERROR_MESSAGE); // Show an error message if the maximum number of laps has been recorded
            }
        }
    }
}