import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class addDoctor extends javax.swing.JFrame {

    public addDoctor() {
        initComponents();
        this.getContentPane().setBackground(new Color(0, 153, 153));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // JOptionPane styling
        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));
    }

    private void initComponents() {

        jLabel1 = new JLabel("ADD DOCTOR DETAILS", SwingConstants.CENTER);
        jLabel1.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 28));
        jLabel1.setForeground(Color.WHITE);

        jLabel2 = new JLabel("Doctor Name:");
        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14));
        jLabel2.setForeground(Color.WHITE);

        jLabel3 = new JLabel("Doctor ID:");
        jLabel3.setFont(new java.awt.Font("Cambria", 0, 14));
        jLabel3.setForeground(Color.WHITE);

        jLabel4 = new JLabel("Doctor Specialization:");
        jLabel4.setFont(new java.awt.Font("Cambria", 0, 14));
        jLabel4.setForeground(Color.WHITE);

        di = new JTextField();
        dn = new JTextField();
        ds = new JTextField();

        jButton1 = new JButton("ADD");
        jButton1.setBackground(new Color(0, 153, 102));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 14));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2 = new JButton("BACK");
        jButton2.setBackground(new Color(51, 153, 255));
        jButton2.setForeground(Color.WHITE);
        jButton2.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 14));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3 = new JButton("LOGOUT");
        jButton3.setBackground(new Color(204, 0, 51));
        jButton3.setForeground(Color.WHITE);
        jButton3.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 14));
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel5 = new JLabel(); // Placeholder for image, if any.

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(jLabel1)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jButton2))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(di, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dn, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(ds, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addGap(30)
                            .addComponent(jButton3))))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30)
                .addComponent(jLabel1)
                .addGap(40)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(di, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(30)
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        new DOCTORS().setVisible(true);
        dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginPage().setVisible(true);
        dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (di.getText().trim().isEmpty() || dn.getText().trim().isEmpty() || ds.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill The Data First");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");

                String sql = "INSERT INTO doctor_record VALUES (?, ?, ?)";
                PreparedStatement ptstmt = conn.prepareStatement(sql);
                ptstmt.setString(1, di.getText());
                ptstmt.setString(2, dn.getText());
                ptstmt.setString(3, ds.getText());
                ptstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data inserted successfully");

                di.setText("");
                dn.setText("");
                ds.setText("");

                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new addDoctor().setVisible(true));
    }

    // Variable declarations
    private JTextField di;
    private JTextField dn;
    private JTextField ds;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
}
