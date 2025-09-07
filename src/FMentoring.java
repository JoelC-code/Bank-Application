import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.awt.Color;

public class FMentoring {
    public FMentoring(){
        JFrame UI = new JFrame();
        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());

        JLabel headerContent = new JLabel();
        headerContent.setText("MENTORING");
        headerContent.setFont(new Font("Roboto",Font.BOLD,27));
        headerContent.setForeground(Color.WHITE);
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel Header = new JPanel();
        Header.setBackground(new Color(0x123456));
        Header.setBounds(0, 0, 420, 100);
        Header.setLayout(new BorderLayout());
        Header.add(headerContent, BorderLayout.CENTER);

        JButton Microcredit = new JButton();
        Microcredit.setText("Ajukan Microcredit");
        Microcredit.setFont(new Font ("Roboto", Font.BOLD, 16));
        Microcredit.setBounds(20, 130, 370, 50);
        Microcredit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UI.dispose();
                new FMicrocredit();
            }
        });

        JButton MenjadwalkanMentoring = new JButton();
        MenjadwalkanMentoring.setText("Jadwalkan Mentor");
        MenjadwalkanMentoring.setFont(new Font ("Roboto", Font.BOLD, 16));
        MenjadwalkanMentoring.setBounds(20, 180, 370, 50);
        MenjadwalkanMentoring.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UI.dispose();
                new FMenjadwalkanMentor();
            }
        });        

        JButton JadwalMentoring = new JButton();
        JadwalMentoring.setText("Melihat jadwal");
        JadwalMentoring.setFont(new Font ("Roboto", Font.BOLD, 16));
        JadwalMentoring.setBounds(20, 230, 370, 50);
        JadwalMentoring.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UI.dispose();
                new FjadwalMentoringjadi();
            }
        });

        JButton Keluar = new JButton();
        Keluar.setText("keluar");
        Keluar.setFont(new Font ("Roboto", Font.BOLD, 16));
        Keluar.setBounds(20, 290, 370, 50);
        Keluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                UI.dispose();
                new FMainMenu();
            }
        });

        UI.add(Header);
        UI.add(Keluar);
        UI.add(Microcredit);
        UI.add(MenjadwalkanMentoring);
        UI.add(JadwalMentoring);
        Header.add(headerContent);

        UI.setLayout(null);
        UI.setTitle("Mentoring");
        UI.setVisible(true);
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420,420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
        UI.repaint();
        UI.revalidate();
    }
    public static void main(String[] args) {
        new FMentoring();
    }
}
