package ui;

import database.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddExpense extends JFrame {

    JTextField amountField;
    JComboBox<String> categoryBox;
    JTextField descriptionField;
    JButton saveBtn;

    public AddExpense() {

        setTitle("Add Expense");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(50, 50, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 150, 30);
        add(amountField);

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(50, 100, 100, 30);
        add(categoryLabel);

        String categories[] = {"Food", "Travel", "Shopping", "Bills", "Others"};

        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(150, 100, 150, 30);
        add(categoryBox);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(50, 150, 100, 30);
        add(descriptionLabel);

        descriptionField = new JTextField();
        descriptionField.setBounds(150, 150, 150, 30);
        add(descriptionField);

        saveBtn = new JButton("Save Expense");
        saveBtn.setBounds(120, 230, 150, 35);
        add(saveBtn);

        saveBtn.addActionListener(e -> {

            try {

                Connection con = DBConnection.connect();

                String query = "INSERT INTO expenses(amount, category, description) VALUES (?, ?, ?)";

                PreparedStatement pst = con.prepareStatement(query);

                pst.setDouble(1, Double.parseDouble(amountField.getText()));
                pst.setString(2, categoryBox.getSelectedItem().toString());
                pst.setString(3, descriptionField.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null,
                        "Expense Saved to Database");

                amountField.setText("");
                descriptionField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, ex);
            }

        });

        setVisible(true);
    }
}