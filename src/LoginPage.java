import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin, btnRegister;

    public LoginPage() {

        setTitle("Login");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("User Login");
        lblTitle.setBounds(150, 20, 200, 30);
        add(lblTitle);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50, 80, 100, 30);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(150, 80, 150, 30);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 120, 100, 30);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 120, 150, 30);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(60, 180, 100, 30);
        add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 180, 100, 30);
        add(btnRegister);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogin) {

            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "SELECT role FROM users WHERE username=? AND password=?");

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");

                    if (role.equals("admin")) {
                        new AdminHomePage(username);
                    } else {
                        new UserHomePage(username);
                    }

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Login!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnRegister) {
            new RegisterPage();
            dispose();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
