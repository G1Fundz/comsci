import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // this sets the look and feel off the ui to the default of your computer system
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    // creates new login frame and displays it
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}