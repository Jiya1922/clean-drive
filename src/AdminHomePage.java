import javax.swing.*;
import java.awt.event.*;

public class AdminHomePage extends JFrame implements ActionListener {

    JButton btnShowUsers, btnShowForms, btnLogout;
    String username;

    public AdminHomePage(String username) {

        this.username = username;

        setTitle("Admin Home");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblHeading = new JLabel("CLEAN DRIVE - ADMIN");
        lblHeading.setBounds(20, 20, 250, 30);
        add(lblHeading);

        btnShowUsers = new JButton("Show Registered Users");
        btnShowUsers.setBounds(130, 100, 220, 35);
        add(btnShowUsers);

        btnShowForms = new JButton("Show Submitted Forms");
        btnShowForms.setBounds(130, 150, 220, 35);
        add(btnShowForms);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(180, 220, 120, 35);
        add(btnLogout);

        btnShowUsers.addActionListener(this);
        btnShowForms.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnShowUsers) {
            new ShowRegistered();
        }

        if (e.getSource() == btnShowForms) {
            new ShowUserForms();
        }

        if (e.getSource() == btnLogout) {
            new LoginPage();
            dispose();
        }
    }
}
