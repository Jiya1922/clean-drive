import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CleanDrivePage extends JFrame implements ActionListener {

    JButton btnRegister, btnShow, btnHome;
    String username;
    boolean isAdmin;

    public CleanDrivePage(String username, boolean isAdmin) {

        this.username = username;
        this.isAdmin = isAdmin;

        setTitle("Clean Drive");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl = new JLabel("CLEAN DRIVE");
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setBounds(20, 20, 200, 30);
        add(lbl);

        btnRegister = new JButton("Register for Drive");
        btnRegister.setBounds(200, 120, 180, 40);
        add(btnRegister);

        btnHome = new JButton("Logout");
        btnHome.setBounds(450, 20, 100, 30);
        add(btnHome);

        btnRegister.addActionListener(this);
        btnHome.addActionListener(this);

        if (isAdmin) {
            btnShow = new JButton("Show Registered");
            btnShow.setBounds(200, 180, 180, 40);
            add(btnShow);
            btnShow.addActionListener(this);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRegister) {
            new RegistrationForm(username);
        }

        if (e.getSource() == btnHome) {
            dispose();
            new LoginPage();
        }

        if (isAdmin && e.getSource() == btnShow) {
            new ShowRegistered();
        }
    }
}
