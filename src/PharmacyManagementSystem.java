import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PharmacyManagementSystem {
    private JFrame frame;
    private UserDAO userDAO;
    private MedicineDAO medicineDAO;
    private OrderDAO orderDAO;

    public PharmacyManagementSystem() {
        userDAO = new UserDAO();
        medicineDAO = new MedicineDAO();
        orderDAO = new OrderDAO();

        frame = new JFrame("Pharmacy Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        showLoginScreen();
        frame.setVisible(true);
    }

    private void showLoginScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(loginButton);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (userDAO.authenticateUser(email, password)) {
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password.");
            }
        });

        registerButton.addActionListener(e -> showRegistrationScreen());

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showRegistrationScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(registerButton);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(backButton);

        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (userDAO.registerUser(email, password, name)) {
                JOptionPane.showMessageDialog(frame, "Registration successful. Please log in.");
                showLoginScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Registration failed. Please try again.");
            }
        });

        backButton.addActionListener(e -> showLoginScreen());

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton viewMedicinesButton = new JButton("View Medicines");
        JButton orderMedicinesButton = new JButton("Order Medicines");
        JButton consultDoctorButton = new JButton("Consult Doctor");
        JButton trackOrdersButton = new JButton("Track Orders");
        JButton logoutButton = new JButton("Logout");

        panel.add(viewMedicinesButton);
        panel.add(orderMedicinesButton);
        panel.add(consultDoctorButton);
        panel.add(trackOrdersButton);
        panel.add(logoutButton);

        viewMedicinesButton.addActionListener(e -> showMedicines());
        orderMedicinesButton.addActionListener(e -> showOrderMedicines());
        consultDoctorButton.addActionListener(e -> consultDoctor());
        trackOrdersButton.addActionListener(e -> trackOrders());
        logoutButton.addActionListener(e -> showLoginScreen());

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showMedicines() {
        List<Medicine> medicines = medicineDAO.getAllMedicines();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (Medicine medicine : medicines) {
            textArea.append("ID: " + medicine.getId() + ", Name: " + medicine.getName() + 
                            ", Price: $" + medicine.getPrice() + ", Quantity: " + medicine.getQuantity() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainMenu());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showOrderMedicines() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel medicineIdLabel = new JLabel("Medicine ID: ");
        JTextField medicineIdField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField quantityField = new JTextField();
        JButton orderButton = new JButton("Order");
        JButton backButton = new JButton("Back");

        panel.add(medicineIdLabel);
        panel.add(medicineIdField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(orderButton);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(backButton);

        orderButton.addActionListener(e -> {
            int medicineId = Integer.parseInt(medicineIdField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            if (orderDAO.placeOrder(1, medicineId, quantity)) {  // Assuming user ID = 1 for now
                JOptionPane.showMessageDialog(frame, "Order placed successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Order failed. Please try again.");
            }
        });

        backButton.addActionListener(e -> showMainMenu());

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void consultDoctor() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea("Consultation feature is under development...");
        textArea.setEditable(false);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainMenu());

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void trackOrders() {
        List<Order> orders = orderDAO.getOrdersByUser(1);  // Assuming user ID = 1 for now
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (Order order : orders) {
            textArea.append("Order ID: " + order.getId() + ", Medicine ID: " + order.getMedicineId() +
                            ", Quantity: " + order.getQuantity() + ", Total Price: $" + order.getTotalPrice() +
                            ", Status: " + order.getStatus() + ", Order Date: " + order.getOrderDate() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainMenu());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PharmacyManagementSystem());
    }
}
        