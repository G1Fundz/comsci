import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    private JPanel loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login"); //sets the title of the frame
        setResizable(false); //can't change the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //press X to close frame
        setBounds(100, 100, 450, 300); //sets position of frame
        setLocationRelativeTo(null); //centres the frame
        loginFrame = new JPanel();
        loginFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(loginFrame);
        loginFrame.setLayout(null);

        JLabel lblUsername = new JLabel("Username"); //sets the jlabel text
        lblUsername.setBounds(138, 48, 84, 14);
        loginFrame.add(lblUsername);

        JLabel lblPassword = new JLabel("Password"); //sets the jlabel text
        lblPassword.setBounds(138, 100, 84, 14);
        loginFrame.add(lblPassword);

        usernameField = new JTextField(); //create textfield to enter username
        usernameField.setBounds(222, 45, 86, 20); //set position of textfield
        loginFrame.add(usernameField); //adding textfield to frame
        usernameField.setColumns(10);

        passwordField = new JPasswordField(); //create textfield to enter password
        passwordField.setBounds(222, 97, 86, 20); //set pos of textfield
        loginFrame.add(passwordField); //adding textfield to frame

        JButton btnLogin = new JButton("Login"); //sets the jlabel text
        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    // reads the username and password from the file
                    BufferedReader reader = new BufferedReader(new FileReader("login.txt"));
                    String username = reader.readLine();
                    String password = reader.readLine();
                    reader.close();

                    // checks if the username and password match
                    if (usernameField.getText().equals(username) && passwordField.getText().equals(password)) {
                        JOptionPane.showMessageDialog(null, "Logged in successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new Homepage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnLogin.setBounds(129, 154, 89, 23);
        loginFrame.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the register window
                Register register = new Register();
                register.setVisible(true);
            }
        });
        btnRegister.setBounds(229, 154, 89, 23);
        loginFrame.add(btnRegister);
    }
}

class Register extends JFrame {

    private JPanel theRegister;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Register() {
        setTitle("Register");
        setResizable(false); //can't resize the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //closes app when you press the X
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        theRegister = new JPanel();
        theRegister.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(theRegister);
        theRegister.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(138, 48, 84, 14); //sets position of username lbl
        theRegister.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(138, 100, 84, 14); //sets position of pw label
        theRegister.add(lblPassword);

        usernameField = new JTextField();

        usernameField.setBounds(222, 45, 86, 20); //sets position
        theRegister.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(222, 97, 86, 20); //sets the position
        theRegister.add(passwordField);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Writes the username and password to the file
                    BufferedWriter writer = new BufferedWriter(new FileWriter("login.txt"));
                    writer.write(usernameField.getText());
                    writer.newLine();
                    writer.write(passwordField.getText());
                    writer.close();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnRegister.setBounds(229, 154, 89, 23);
        theRegister.add(btnRegister);
    }
}