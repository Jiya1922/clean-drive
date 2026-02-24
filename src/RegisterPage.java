import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterPage extends JFrame implements ActionListener {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnRegister, btnBack;

    public RegisterPage() {

        setTitle("Register");
        setSize(400, 300);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50, 60, 100, 30);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(150, 60, 150, 30);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 100, 100, 30);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 100, 150, 30);
        add(txtPass);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(50, 160, 100, 30);
        add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 160, 100, 30);
        add(btnBack);

        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRegister) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO users(username,password,role) VALUES(?,?,?)"
                );

                ps.setString(1, txtUser.getText());
                ps.setString(2, new String(txtPass.getPassword()));
                ps.setString(3, "user");

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registered Successfully!");
                new LoginPage();
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnBack) {
            new LoginPage();
            dispose();
        }
    }
}
