
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DOCTORS extends JFrame {

    private JLabel heading;
    private JLabel doctdescp;
    private JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6;

    public DOCTORS() {
        setTitle("Doctors Panel");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(0, 153, 153));
        setLayout(new BorderLayout(10, 10));

        // Top section - title and buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(0, 153, 153));
        JLabel title = new JLabel("DOCTORS RECORDS");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        topPanel.add(title);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(new Color(0, 153, 153));

        jButton1 = createButton("ADD DOCTORS");
        jButton2 = createButton("EDIT DOCTORS DETAIL");
        jButton3 = createButton("FIRE DOCTOR");
        jButton4 = createButton("VIEW DOCTORS DETAIL");
        jButton5 = createButton("BACK", new Color(51, 153, 255), Color.WHITE);
        jButton6 = createButton("LOGOUT", new Color(255, 51, 51), Color.WHITE);

        buttonPanel.add(jButton1);
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton3);
        buttonPanel.add(jButton4);
        buttonPanel.add(jButton5);
        buttonPanel.add(jButton6);

        // Center section - description and heading
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(0, 153, 153));

        heading = new JLabel("<html><div style='text-align: center;'>Welcome to the <b>Riphah Clinic</b></div></html>", SwingConstants.CENTER);
        heading.setFont(new Font("Arial Black", Font.PLAIN, 22));
        heading.setForeground(Color.WHITE);

        doctdescp = new JLabel("<html><div style='text-align: center;'>"
                + "Our hospital is home to some of the most talented doctors in Pakistan. With highly skilled professionals "
                + "from various fields, including cardiology, pediatrics, and general surgery, our team is recognized as among the "
                + "best in the country. These top-ranked doctors are dedicated to providing exceptional patient care and innovative treatments, "
                + "continually advancing medical practices and achieving outstanding patient outcomes."
                + "</div></html>", SwingConstants.CENTER);
        doctdescp.setFont(new Font("Cambria", Font.ITALIC, 16));
        doctdescp.setForeground(Color.WHITE);

        centerPanel.add(heading, BorderLayout.NORTH);
        centerPanel.add(doctdescp, BorderLayout.CENTER);

        // Add everything to main frame
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        // Add action listeners
        jButton1.addActionListener(e -> {
            new addDoctor().setVisible(true);
            dispose();
        });

        jButton2.addActionListener(e -> {
            new editDoctor().setVisible(true);
            dispose();
        });

        jButton3.addActionListener(e -> {
            new fireDoctor().setVisible(true);
            dispose();
        });

        jButton4.addActionListener(e -> {
            new viewdetailDoc().setVisible(true);
            dispose();
        });

        jButton5.addActionListener(e -> {
            new welcome().setVisible(true);
            dispose();
        });

        jButton6.addActionListener(e -> {
            new LoginPage().setVisible(true);
            dispose();
        });
    }

    private JButton createButton(String text) {
        return createButton(text, new Color(0, 255, 153), Color.BLACK);
    }

    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Cambria", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DOCTORS().setVisible(true));
    }
}
