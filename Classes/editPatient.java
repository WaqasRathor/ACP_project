
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class editPatient extends javax.swing.JFrame {

    public editPatient() {
        initComponents();

        this.getContentPane().setBackground(new Color(0, 153, 153));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Joptionpane styling
        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pi = new javax.swing.JTextField();
        pn = new javax.swing.JTextField();
        pd = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setText("EDIT PATIENT RECORD");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("Patient ID");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setText("Patient Name");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel4.setForeground(Color.WHITE);
        jLabel4.setText("Patient Disease");

        update.setBackground(new Color(0, 102, 102));
        update.setFont(new java.awt.Font("Cambria", 1, 14));
        update.setForeground(Color.WHITE);
        update.setText("UPDATE");
        update.addActionListener(evt -> updateActionPerformed());

        Back.setBackground(new Color(51, 153, 255));
        Back.setFont(new java.awt.Font("Cambria", 1, 14));
        Back.setForeground(Color.WHITE);
        Back.setText("BACK");
        Back.addActionListener(evt -> BackActionPerformed());

        logout.setBackground(new Color(255, 51, 51));
        logout.setFont(new java.awt.Font("Cambria", 1, 14));
        logout.setForeground(Color.WHITE);
        logout.setText("LOGOUT");
        logout.addActionListener(evt -> logoutActionPerformed());

        search.setBackground(new Color(51, 51, 255));
        search.setFont(new java.awt.Font("Cambria", 1, 14));
        search.setForeground(Color.WHITE);
        search.setText("SEARCH");
        search.addActionListener(evt -> searchActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pi)
                            .addComponent(pn)
                            .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(Back)
                .addGap(40, 40, 40)
                .addComponent(search)
                .addGap(40, 40, 40)
                .addComponent(update)
                .addGap(40, 40, 40)
                .addComponent(logout)
                .addGap(80, 80, 80))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back)
                    .addComponent(search)
                    .addComponent(update)
                    .addComponent(logout))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    private void updateActionPerformed() {
        String pid = pi.getText();
        String pname = pn.getText();
        String pdis = pd.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");

            String sql = "UPDATE `patient_record` SET `Name`=?, `Disease`=? WHERE id=?";
            PreparedStatement ptstmt = conn.prepareStatement(sql);
            ptstmt.setString(1, pname);
            ptstmt.setString(2, pdis);
            ptstmt.setString(3, pid);
            ptstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record updated successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void BackActionPerformed() {
        new PATIENT().setVisible(true);
        dispose();
    }

    private void logoutActionPerformed() {
        new LoginPage().setVisible(true);
        dispose();
    }

    private void searchActionPerformed() {
        String pid = pi.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");

            String sql = "SELECT `Name`, `Disease` FROM `patient_record` WHERE `id`=?";
            PreparedStatement ptstmt = conn.prepareStatement(sql);
            ptstmt.setString(1, pid);
            java.sql.ResultSet rs = ptstmt.executeQuery();

            if (rs.next()) {
                pn.setText(rs.getString("Name"));
                pd.setText(rs.getString("Disease"));
            } else {
                JOptionPane.showMessageDialog(null, "Record not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new editPatient().setVisible(true));
    }

    // Variables
    private javax.swing.JButton Back, logout, update, search;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private javax.swing.JTextField pi, pn, pd;
}
