import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class FMainMenu {
    public FMainMenu(){
        JFrame frame = new JFrame();
        frame.setTitle("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420,420);
        frame.setVisible(true);
        frame.setLayout(null);

        ImageIcon image = new ImageIcon("Logo.png");
        frame.setIconImage(image.getImage());

        JButton button = new JButton();
        button.setBounds(210,210, 140, 60);
        button.setText("EduMoney");
        frame.add(button);

        JButton button2 = new JButton();
        button2.setBounds(210, 130, 140, 60);
        button2.setText("Cardless");
        frame.add(button2);

        JButton button3 = new JButton();
        button3.setBounds(50, 210, 140, 60);
        button3.setText("Mentoring");
        frame.add(button3);

        JButton button4 = new JButton();
        button4.setBounds(50, 130, 140, 60);
        button4.setText("Transfer");
        frame.add(button4);

        JButton button5 = new JButton();
        button5.setBounds(130, 290, 140, 60);
        button5.setText("Log out");
        frame.add(button5);

        JLabel label = new JLabel();
        label.setFont(new Font("Roboto",Font.BOLD,27));
        label.setText("Selamat datang!");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 420, 100);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FEduMoney();
                frame.dispose();
                new FEduMoney();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                new FCardless();
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FMentoring();
                frame.dispose();
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FTransfernInformasi();
                frame.dispose();
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new AFMenuSebelumLogin();
                frame.dispose();
            }
        });

        panel.add(label);
        frame.add(panel);

        frame.getContentPane().setBackground(new Color(0xFFFFFF));

        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args) {
        new FMainMenu();
    }
}