import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Product {
    String name;
    int quantity;
    double price;

    Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

public class PharmacyManagementSwing {
    private static Map<String, Product> productList = new HashMap<>();
    private static JTextArea textArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pharmacy Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text Area to display product details
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons and input fields
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Add Product Button
        JButton addButton = new JButton("Add Product");
        panel.add(addButton);

        // Update Product Button
        JButton updateButton = new JButton("Update Product");
        panel.add(updateButton);

        // View Products Button
        JButton viewButton = new JButton("View Products");
        panel.add(viewButton);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);

        frame.add(panel, BorderLayout.SOUTH);

        // Add Product Action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Update Product Action
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        // View Products Action
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });

        // Exit Action
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static void addProduct() {
        JTextField nameField = new JTextField(15);
        JTextField quantityField = new JTextField(5);
        JTextField priceField = new JTextField(5);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Product Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Add Product", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            Product product = new Product(name, quantity, price);
            productList.put(name, product);
            JOptionPane.showMessageDialog(null, "Product added successfully!");
        }
    }

    private static void updateProduct() {
        String name = JOptionPane.showInputDialog("Enter product name to update:");
        if (name != null && productList.containsKey(name)) {
            JTextField quantityField = new JTextField(5);
            JTextField priceField = new JTextField(5);

            JPanel panel = new JPanel();
            panel.add(new JLabel("New Quantity:"));
            panel.add(quantityField);
            panel.add(new JLabel("New Price:"));
            panel.add(priceField);

            int option = JOptionPane.showConfirmDialog(null, panel, "Update Product", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                Product product = productList.get(name);
                product.quantity = quantity;
                product.price = price;
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found.");
        }
    }

    private static void viewProducts() {
        if (productList.isEmpty()) {
            textArea.setText("No products available.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("--- Product List ---\n");
            for (Map.Entry<String, Product> entry : productList.entrySet()) {
                Product product = entry.getValue();
                sb.append("Name: ").append(product.name).append(", Quantity: ").append(product.quantity).append(", Price: ").append(product.price).append("\n");
            }
            textArea.setText(sb.toString());
        }
    }
}