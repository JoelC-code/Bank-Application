import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
public class FCardless {
    public FCardless() {
        JFrame frame = new JFrame();
        frame.setTitle("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420,420);
        frame.setVisible(true);
        frame.setLayout(null);

        JButton button = new JButton();
        button.setBounds(135,130, 140, 60);
        button.setText("Tarik Tunai");
        frame.add(button);

        JButton button2 = new JButton();
        button2.setBounds(135,220, 140, 60);
        button2.setText("Setor Tunai");
        frame.add(button2);

        JButton button3 = new JButton();
        button3.setBounds(10,340, 100, 30);
        button3.setText("Back");
        frame.add(button3);

        JLabel label = new JLabel();
        label.setFont(new Font("Roboto",Font.BOLD,27));
        label.setText("Cardless");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 420, 100);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FTarik();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FSetor();
                frame.dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FMainMenu();
                frame.dispose();
            }
        });

        panel.add(label);
        frame.add(panel);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0xFFFFFF));

        frame.revalidate();
        frame.repaint();
    } public static void main(String[] args) {
        new FCardless();
    }
}
