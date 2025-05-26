
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

public class viewrecordsPatient extends javax.swing.JFrame {

    public viewrecordsPatient() {
        initComponents();
        
        // Style the main frame
        this.getContentPane().setBackground(new Color(0, 153, 153));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Style buttons
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Customize JOptionPane
        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));
    }

    private void initComponents() {
        jLabel1 = new JLabel("VIEW DETAILS", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Times New Roman", Font.BOLD, 24));

        jButton1 = new JButton("VIEW RECORDS");
        jButton1.setFont(new Font("Cambria", Font.BOLD, 14));
        jButton1.setBackground(new Color(0, 153, 153));
        jButton1.setForeground(Color.WHITE);
        jButton1.addActionListener(evt -> loadRecords());

        jButton2 = new JButton("BACK");
        jButton2.setFont(new Font("Cambria", Font.BOLD, 14));
        jButton2.setBackground(new Color(51, 204, 255));
        jButton2.setForeground(Color.WHITE);
        jButton2.addActionListener(evt -> goBack());

        jButton3 = new JButton("LOGOUT");
        jButton3.setFont(new Font("Cambria", Font.BOLD, 14));
        jButton3.setBackground(new Color(255, 51, 51));
        jButton3.setForeground(Color.WHITE);
        jButton3.addActionListener(evt -> logout());

        jTable1 = new JTable(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Patient Name", "Disease", "Date"}
        ));
        jScrollPane1 = new JScrollPane(jTable1);

        // Layout
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(jLabel1, BorderLayout.CENTER);
        topPanel.add(jButton1, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(jButton2);
        bottomPanel.add(jButton3);

        add(topPanel, BorderLayout.NORTH);
        add(jScrollPane1, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
    }

    private void loadRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");

            String sql = "SELECT * FROM patient_record";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("Name"),
                    rs.getString("Disease"),
                    rs.getString("Date")
                };
                tm.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new viewrecordsPatient().setVisible(true));
    }

    // UI Components
    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel1;
    private JTable jTable1;
    private JScrollPane jScrollPane1;
}
