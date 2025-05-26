
import java.awt.Color;
import java.awt.Cursor;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

public class dischargePatient extends javax.swing.JFrame {

    public dischargePatient() {
        initComponents();
        this.getContentPane().setBackground(new Color(0, 153, 153));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Set cursor for buttons
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // JOptionPane theme
        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel("Discharge Patient", SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));

        jTable1 = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Patient Name", "Disease", "Date"}));
        jScrollPane1 = new JScrollPane(jTable1);

        jLabel2 = new JLabel("Enter Patient ID to Discharge:");

        pd = new JTextField(10);

        jButton1 = new JButton("VIEW RECORD");
        jButton1.setBackground(new Color(0, 153, 102));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton1.addActionListener(evt -> viewPatientRecords());

        jButton2 = new JButton("Discharge");
        jButton2.setBackground(new Color(204, 0, 51));
        jButton2.setForeground(Color.WHITE);
        jButton2.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton2.addActionListener(evt -> dischargePatientById());

        jButton3 = new JButton("Back");
        jButton3.setBackground(new Color(51, 153, 255));
        jButton3.setForeground(Color.WHITE);
        jButton3.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton3.addActionListener(evt -> goBack());

        jButton4 = new JButton("Logout");
        jButton4.setBackground(new Color(255, 0, 102));
        jButton4.setForeground(Color.WHITE);
        jButton4.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton4.addActionListener(evt -> logout());

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(pd, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addComponent(jButton3)
                    .addGap(18, 18, 18)
                    .addComponent(jButton4))
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)))
            .addContainerGap()));
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(20)
            .addComponent(jLabel1)
            .addGap(20)
            .addComponent(jButton1)
            .addGap(15)
            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(pd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2)
                .addComponent(jButton3)
                .addComponent(jButton4))
            .addContainerGap(40, Short.MAX_VALUE));

        pack();
    }

    private void viewPatientRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patient_record");
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);
            while (rs.next()) {
                Object[] row = {rs.getInt("ID"), rs.getString("Name"), rs.getString("Disease"), rs.getString("Date")};
                tm.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void dischargePatientById() {
        String pid = pd.getText().trim();
        if (pid.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");

            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM patient_record WHERE id = ?");
            checkStmt.setString(1, pid);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "ID not found in Database");
            } else {
                PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM patient_record WHERE id = ?");
                deleteStmt.setString(1, pid);
                deleteStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Patient discharged successfully");
                pd.setText("");
            }

            rs.close();
            checkStmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void goBack() {
        new PATIENT().setVisible(true);
        dispose();
    }

    private void logout() {
        new LoginPage().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new dischargePatient().setVisible(true));
    }

    // Variables
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JLabel jLabel1, jLabel2;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField pd;
}
