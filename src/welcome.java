
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.*;

public class welcome extends JFrame {

    private JLabel heading;
    private JLabel welCenterText;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel clinicTitle;

    public welcome() {
        initComponents();

        // Frame styling
        this.getContentPane().setBackground(new Color(0, 153, 153));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Set texts
        clinicTitle.setText("Riphah Clinic");
        clinicTitle.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48));
        clinicTitle.setForeground(Color.WHITE);

        heading.setText("<html><b>Welcome to the Riphah Clinic</b></html>");
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        welCenterText.setText("<html>"
                + "This system is designed to manage hospital operations efficiently.<br>"
                + "It provides functionalities for <b>patients</b> and <b>doctors</b> "
                + "where you can add, delete, update, and read data."
                + "</html>");
        welCenterText.setForeground(Color.WHITE);
        welCenterText.setHorizontalAlignment(SwingConstants.CENTER);

        // Set button cursors
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initComponents() {
        heading = new JLabel();
        welCenterText = new JLabel();
        jButton1 = new JButton("Doctor's Record");
        jButton2 = new JButton("Patient's Record");
        jButton3 = new JButton("LOGOUT");
        clinicTitle = new JLabel();

        // Button styling
        jButton1.setBackground(new Color(0, 204, 153));
        jButton1.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 14));
        jButton1.setForeground(Color.WHITE);
        jButton1.addActionListener(evt -> openDoctors());

        jButton2.setBackground(new Color(0, 204, 153));
        jButton2.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 14));
        jButton2.setForeground(Color.WHITE);
        jButton2.addActionListener(evt -> openPatients());

        jButton3.setBackground(new Color(255, 0, 51));
        jButton3.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 14));
        jButton3.setForeground(Color.WHITE);
        jButton3.addActionListener(evt -> logout());

        // Layout using GroupLayout (lightweight version)
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 153, 153));

        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(clinicTitle)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                .addComponent(heading)
                .addComponent(welCenterText)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30)
                .addComponent(clinicTitle)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(50)
                .addComponent(heading)
                .addGap(20)
                .addComponent(welCenterText)
                .addGap(30)
        );

        this.setContentPane(contentPane);
        this.pack();
    }

    private void openDoctors() {
        new DOCTORS().setVisible(true);
        dispose();
    }

    private void openPatients() {
        new PATIENT().setVisible(true);
        dispose();
    }

    private void logout() {
        new LoginPage().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new welcome().setVisible(true));
    }
}
