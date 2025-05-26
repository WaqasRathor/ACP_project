import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class admitPatient extends javax.swing.JFrame {

    public admitPatient() {
        initComponents();
        this.getContentPane().setBackground(new Color(0, 153, 153));
        this.setLocationRelativeTo(null); // Center the frame
        this.setResizable(false); // Fixed size

        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // JOptionPane Styling
        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));

        showDate();
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        pad.setText(sd.format(d));
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pi = new javax.swing.JTextField();
        pn = new javax.swing.JTextField();
        pd = new javax.swing.JTextField();
        pad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("ADMIT PATIENT");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("Enter Patient's ID:");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setText("Enter Patient's Name:");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel4.setForeground(Color.WHITE);
        jLabel4.setText("Enter Patient's Disease:");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14));
        jLabel5.setForeground(Color.WHITE);
        jLabel5.setText("Enter Admit Date:");

        // Text field alignment
        pi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pad.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setBackground(new Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton1.setForeground(Color.WHITE);
        jButton1.setText("ADMIT");
        jButton1.addActionListener(evt -> admitPatient());

        jButton2.setBackground(new Color(51, 153, 255));
        jButton2.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton2.setForeground(Color.WHITE);
        jButton2.setText("BACK");
        jButton2.addActionListener(evt -> backToMenu());

        jButton3.setBackground(new Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton3.setForeground(Color.WHITE);
        jButton3.setText("LOGOUT");
        jButton3.addActionListener(evt -> logout());

        // Layout with centered components
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(pad, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addGap(20)
                            .addComponent(jButton1)
                            .addGap(20)
                            .addComponent(jButton3)))
                    .addGap(30))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addComponent(jLabel1)
                .addGap(20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(20)
        );

        pack();
    }

    private void admitPatient() {
        if (pi.getText().isEmpty() || pn.getText().isEmpty() || pd.getText().isEmpty() || pad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insert data first");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234")) {
                    String sql = "INSERT INTO patient_record (id, Name, Disease, Date) VALUES (?, ?, ?, ?)";
                    PreparedStatement ptstmt = conn.prepareStatement(sql);
                    ptstmt.setString(1, pi.getText());
                    ptstmt.setString(2, pn.getText());
                    ptstmt.setString(3, pd.getText());
                    ptstmt.setString(4, pad.getText());
                    ptstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data inserted successfully");
                }
                pi.setText("");
                pn.setText("");
                pd.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }

    private void backToMenu() {
        new PATIENT().setVisible(true); // Ensure PATIENT class exists
        dispose();
    }

    private void logout() {
        new LoginPage().setVisible(true); // Ensure LoginPage class exists
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new admitPatient().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField pad;
    private javax.swing.JTextField pd;
    private javax.swing.JTextField pi;
    private javax.swing.JTextField pn;
    // End of variables declaration
}
