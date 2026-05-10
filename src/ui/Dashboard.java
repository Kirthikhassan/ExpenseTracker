package ui;

import database.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dashboard extends JFrame {

    JLabel totalExpenseLabel;
    JLabel totalCountLabel;

    public Dashboard() {

        setTitle("Expense Dashboard");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("DASHBOARD");
        title.setBounds(150, 20, 150, 30);
        add(title);

        totalExpenseLabel = new JLabel("Total Expense: 0");
        totalExpenseLabel.setBounds(50, 80, 300, 30);
        add(totalExpenseLabel);

        totalCountLabel = new JLabel("Total Records: 0");
        totalCountLabel.setBounds(50, 120, 300, 30);
        add(totalCountLabel);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(120, 180, 120, 30);
        add(refreshBtn);

        refreshBtn.addActionListener(e -> loadData());

        loadData();

        setVisible(true);
    }

    private void loadData() {

        try {

            Connection con = DBConnection.connect();

            Statement st = con.createStatement();

            // Total expense
            ResultSet rs1 = st.executeQuery(
                    "SELECT SUM(amount) FROM expenses"
            );

            if (rs1.next()) {
                totalExpenseLabel.setText(
                        "Total Expense: " + rs1.getDouble(1)
                );
            }

            // Count records
            ResultSet rs2 = st.executeQuery(
                    "SELECT COUNT(*) FROM expenses"
            );

            if (rs2.next()) {
                totalCountLabel.setText(
                        "Total Records: " + rs2.getInt(1)
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }
}