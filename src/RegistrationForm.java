import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame implements ActionListener {

    JTextField txtCollege, txtFname, txtLname, txtEmail,
            txtPhone, txtDob, txtAddress, txtDistrict;

    JComboBox<String> comboOrg;
    JButton btnSubmit, btnBack;

    String username;

    public RegistrationForm(String username) {

        this.username = username;

        setTitle("Clean Drive Registration Form");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setLayout(null);

        int y = 30;

        addLabel("College:", 50, y);
        txtCollege = addTextField(250, y);
        y += 40;

        addLabel("Organization:", 50, y);
        String[] orgs = {"Select", "NSS", "NCC", "Nature Club", "Youth Club"};
        comboOrg = new JComboBox<>(orgs);
        comboOrg.setBounds(250, y, 200, 25);
        add(comboOrg);
        y += 40;

        addLabel("First Name:", 50, y);
        txtFname = addTextField(250, y);
        y += 40;

        addLabel("Last Name:", 50, y);
        txtLname = addTextField(250, y);
        y += 40;

        addLabel("Email:", 50, y);
        txtEmail = addTextField(250, y);
        y += 40;

        addLabel("Phone:", 50, y);
        txtPhone = addTextField(250, y);
        y += 40;

        addLabel("Date of Birth (YYYY-MM-DD):", 50, y);
        txtDob = addTextField(250, y);
        y += 40;

        addLabel("Address:", 50, y);
        txtAddress = addTextField(250, y);
        y += 40;

        addLabel("District:", 50, y);
        txtDistrict = addTextField(250, y);
        y += 50;

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(180, y, 100, 35);
        add(btnSubmit);

        btnBack = new JButton("Back");
        btnBack.setBounds(320, y, 100, 35);
        add(btnBack);

        btnSubmit.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 25);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 200, 25);
        add(field);
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBack) {
            dispose();
            new CleanDrivePage(username, false);
        }

        if (e.getSource() == btnSubmit) {

            // 🔴 Compulsory Field Validation
            if (txtCollege.getText().isEmpty() ||
                    comboOrg.getSelectedIndex() == 0 ||
                    txtFname.getText().isEmpty() ||
                    txtLname.getText().isEmpty() ||
                    txtEmail.getText().isEmpty() ||
                    txtPhone.getText().isEmpty() ||
                    txtDob.getText().isEmpty() ||
                    txtAddress.getText().isEmpty() ||
                    txtDistrict.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "All fields are compulsory!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO clean_drive_registration "
                        + "(username, college, organization, first_name, last_name, email, phone, dob, address, district) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, username);
                pst.setString(2, txtCollege.getText());
                pst.setString(3, comboOrg.getSelectedItem().toString());
                pst.setString(4, txtFname.getText());
                pst.setString(5, txtLname.getText());
                pst.setString(6, txtEmail.getText());
                pst.setString(7, txtPhone.getText());
                pst.setDate(8, Date.valueOf(txtDob.getText()));
                pst.setString(9, txtAddress.getText());
                pst.setString(10, txtDistrict.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration Successful!");

                con.close();
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Date Format or Database Error!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
