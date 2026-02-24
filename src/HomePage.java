import javax.swing.*;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener {

    JButton btnShowUsers, btnShowForms, btnLogout;
    String username;
    boolean isAdmin;

    public HomePage(String username, String role) {

        this.username = username;
        this.isAdmin = role.equals("admin");

        setTitle("Home - " + username);
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblWelcome = new JLabel("Welcome " + username);
        lblWelcome.setBounds(120, 30, 200, 30);
        add(lblWelcome);

        btnShowUsers = new JButton("Show Registered Users");
        btnShowUsers.setBounds(80, 80, 220, 30);
        add(btnShowUsers);

        btnShowForms = new JButton("Show Submitted Forms");
        btnShowForms.setBounds(80, 120, 220, 30);
        add(btnShowForms);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(120, 180, 150, 30);
        add(btnLogout);

        btnShowUsers.addActionListener(this);
        btnShowForms.addActionListener(this);
        btnLogout.addActionListener(this);

        // Hide admin buttons if not admin
        if (!isAdmin) {
            btnShowUsers.setVisible(false);
            btnShowForms.setVisible(false);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (isAdmin && e.getSource() == btnShowUsers) {
            new ShowRegistered();
        }

        if (isAdmin && e.getSource() == btnShowForms) {
            new ShowUserForms();
        }

        if (e.getSource() == btnLogout) {
            new LoginPage();
            dispose();
        }
    }
}
