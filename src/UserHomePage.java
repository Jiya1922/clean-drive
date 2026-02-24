import javax.swing.*;
import java.awt.event.*;

public class UserHomePage extends JFrame implements ActionListener {

    JButton btnAbout, btnCoordinator, btnRegister, btnLogout;
    String username;

    public UserHomePage(String username) {

        this.username = username;

        setTitle("Clean Drive");
        setSize(600, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblHeading = new JLabel("CLEAN DRIVE");
        lblHeading.setBounds(20, 20, 200, 30);
        add(lblHeading);

        btnAbout = new JButton("About");
        btnAbout.setBounds(350, 20, 100, 30);
        add(btnAbout);

        btnCoordinator = new JButton("Coordinator");
        btnCoordinator.setBounds(460, 20, 110, 30);
        add(btnCoordinator);

        btnRegister = new JButton("Register Now");
        btnRegister.setBounds(220, 130, 150, 40);
        add(btnRegister);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(250, 200, 100, 35);
        add(btnLogout);

        btnAbout.addActionListener(this);
        btnCoordinator.addActionListener(this);
        btnRegister.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAbout) {
            JOptionPane.showMessageDialog(this,
                    "Clean Drive promotes cleanliness and environmental awareness.");
        }

        if (e.getSource() == btnCoordinator) {
            JOptionPane.showMessageDialog(this,
                    "Coordinator: Mr. Akshara\nEmail: coordinator@cleandrive.com");
        }

        if (e.getSource() == btnRegister) {
            new UserFormPage(username);
            dispose();
        }

        if (e.getSource() == btnLogout) {
            new LoginPage();
            dispose();
        }
    }
}
