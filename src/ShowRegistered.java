import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ShowRegistered extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;
    JButton btnDelete;

    public ShowRegistered() {

        setTitle("Registered Users");
        setSize(500, 400);
        setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Role");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 440, 200);
        add(sp);

        btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(170, 250, 150, 30);
        add(btnDelete);

        btnDelete.addActionListener(this);

        loadData();

        setVisible(true);
    }

    public void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("role")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {

        int row = table.getSelectedRow();

        if (row >= 0) {

            int id = (int) model.getValueAt(row, 0);

            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM users WHERE id=?"
                );

                ps.setInt(1, id);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "User Deleted!");

                model.removeRow(row);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
