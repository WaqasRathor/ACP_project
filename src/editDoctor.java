import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class editDoctor extends JFrame {

    private JTextField di, dn, ds;
    private JButton search, update, clear, logout, Back;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4;

    public editDoctor() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
    }

    private void initComponents() {
        jLabel1 = new JLabel("Edit Doctors Records");
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));

        jLabel2 = new JLabel("Doctor ID");
        jLabel3 = new JLabel("Doctor Name");
        jLabel4 = new JLabel("Specialization");

        di = new JTextField(20);
        dn = new JTextField(20);
        ds = new JTextField(20);

        search = new JButton("Search");
        update = new JButton("Update");
        clear = new JButton("Clear");
        logout = new JButton("Logout");
        Back = new JButton("Back");

        // Add action listeners
        search.addActionListener(e -> searchActionPerformed());
        update.addActionListener(e -> updateActionPerformed());
        clear.addActionListener(e -> clearActionPerformed());
        logout.addActionListener(e -> logoutActionPerformed());
        Back.addActionListener(e -> BackActionPerformed());

        // Main form panel
        JPanel formPanel = new JPanel();
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(jLabel1)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(di)
                        .addComponent(dn)
                        .addComponent(ds))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(search)
                        .addComponent(update)
                        .addComponent(clear)))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(di)
                    .addComponent(search))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dn)
                    .addComponent(update))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ds)
                    .addComponent(clear))
        );

        // Button panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Back);
        buttonPanel.add(logout);

        // Add everything to the frame
        getContentPane().add(formPanel, "Center");
        getContentPane().add(buttonPanel, "South");

        pack();
    }

    private void BackActionPerformed() {
        new DOCTORS().setVisible(true);
        dispose();
    }

    private void logoutActionPerformed() {
        new LoginPage().setVisible(true);
        dispose();
    }

    private void updateActionPerformed() {
        String did = di.getText().trim();
        String dname = dn.getText().trim();
        String dspec = ds.getText().trim();

        if (did.isEmpty() || dname.isEmpty() || dspec.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insert all data first");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");
            String sql = "UPDATE doctor_record SET DoctorName = ?, Specialization = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dname);
            stmt.setString(2, dspec);
            stmt.setString(3, did);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record updated successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    private void clearActionPerformed() {
        di.setText("");
        dn.setText("");
        ds.setText("");
    }

    private void searchActionPerformed() {
        String did = di.getText().trim();

        if (did.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Doctor ID");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");
            String sql = "SELECT * FROM doctor_record WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, did);
            java.sql.ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dn.setText(rs.getString("DoctorName"));
                ds.setText(rs.getString("Specialization"));
            } else {
                JOptionPane.showMessageDialog(this, "No record found for the given ID");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new editDoctor().setVisible(true));
    }
}
