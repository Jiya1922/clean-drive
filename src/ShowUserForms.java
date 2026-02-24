import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ShowUserForms extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ShowUserForms() {

        setTitle("Submitted Forms");
        setSize(600, 400);
        setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Full Name");
        model.addColumn("Email");
        model.addColumn("Phone");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 540, 250);
        add(sp);

        loadData();

        setVisible(true);
    }

    public void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_details");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
