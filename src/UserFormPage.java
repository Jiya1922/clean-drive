import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UserFormPage extends JFrame implements ActionListener {

    JTextField txtCollege, txtFname, txtLname, txtDob, txtEmail, txtPhone;
    JButton btnSubmit, btnBack;
    String username;

    public UserFormPage(String username) {

        this.username = username;

        setTitle("Registration Form");
        setSize(450, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Registration Form");
        lblTitle.setBounds(150, 20, 200, 30);
        add(lblTitle);

        JLabel lblCollege = new JLabel("College:");
        lblCollege.setBounds(50, 70, 100, 30);
        add(lblCollege);

        txtCollege = new JTextField();
        txtCollege.setBounds(150, 70, 200, 30);
        add(txtCollege);

        JLabel lblFname = new JLabel("First Name:");
        lblFname.setBounds(50, 110, 100, 30);
        add(lblFname);

        txtFname = new JTextField();
        txtFname.setBounds(150, 110, 200, 30);
        add(txtFname);

        JLabel lblLname = new JLabel("Last Name:");
        lblLname.setBounds(50, 150, 100, 30);
        add(lblLname);

        txtLname = new JTextField();
        txtLname.setBounds(150, 150, 200, 30);
        add(txtLname);

        JLabel lblDob = new JLabel("DOB (YYYY-MM-DD):");
        lblDob.setBounds(50, 190, 150, 30);
        add(lblDob);

        txtDob = new JTextField();
        txtDob.setBounds(200, 190, 150, 30);
        add(txtDob);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 230, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 230, 200, 30);
        add(txtEmail);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(50, 270, 100, 30);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(150, 270, 200, 30);
        add(txtPhone);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(80, 350, 120, 35);
        add(btnSubmit);

        btnBack = new JButton("Back");
        btnBack.setBounds(230, 350, 120, 35);
        add(btnBack);

        btnSubmit.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSubmit) {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO user_details(username,college,fname,lname,dob,email,phone) VALUES(?,?,?,?,?,?,?)");

                ps.setString(1, username);
                ps.setString(2, txtCollege.getText());
                ps.setString(3, txtFname.getText());
                ps.setString(4, txtLname.getText());
                ps.setString(5, txtDob.getText());
                ps.setString(6, txtEmail.getText());
                ps.setString(7, txtPhone.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Form Submitted!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnBack) {
            new UserHomePage(username);
            dispose();
        }
    }
}
