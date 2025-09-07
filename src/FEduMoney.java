import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FEduMoney {
    public FEduMoney() {
        JFrame frame = new JFrame();
        frame.setTitle("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420,420);
        frame.setVisible(true);
        frame.setLayout(null);
        int width = 420;

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0xFFFFFF));

        JButton button = new JButton();
        button.setBounds(10,340, 100, 30);
        button.setText("Back");
        frame.add(button);

        JLabel label = new JLabel();
        label.setFont(new Font("Roboto",Font.BOLD,27));
        label.setText("EduMoney");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 420, 100);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        JLabel namaNotransfer = new JLabel();
        namaNotransfer.setFont(new Font("Roboto", Font.BOLD, 18));
        namaNotransfer.setText("Deskripsi: ");
        namaNotransfer.setForeground(Color.BLACK);
        namaNotransfer.setBounds(10, 110, 200, 20);

        JTextArea namaNominal = new JTextArea();
        namaNominal.setFont(new Font("Roboto", Font.PLAIN, 16));
        namaNominal.setText("Aplikasi bank yang bisa dipakai untuk menyimpan uang, transfer uang ke Nomor rekening orang lain, pembayaran belanja, dan mengelola keuangan anda. Menabung penting karena:\n" + //
                        "\n" + //
                        "-Keamanan Finansial: Untuk kebutuhan mendesak.\n" + //
                        "\n" + //
                        "-Pencapaian Tujuan: Meraih impian finansial.\n" + //
                        "\n" + //
                        "-Kebebasan Finansial: Mengurangi tekanan keuangan.\n" + //
                        "\n" + //
                        "-Menghadapi Ketidakpastian: Siap hadapi tantangan.\n" + //
                        "\n" + //
                        "Menabung adalah kunci stabilitas finansial");
        namaNominal.setForeground(Color.BLACK);
        namaNominal.setBounds((width - 340)/2, 135, 340, 200);
        namaNominal.setLineWrap(true);
        namaNominal.setWrapStyleWord(true);
        namaNominal.setEditable(false);

        JScrollPane scrollDown = new JScrollPane(namaNominal);
        scrollDown.setBounds(30, 135, 300, 200);
        scrollDown.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollDown.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        namaNominal.setCaretPosition(0);

        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                new FMainMenu();
                frame.dispose();
            }
        });

        panel.add(label);
        frame.add(panel);
        frame.add(namaNotransfer);
        frame.add(scrollDown);

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new FEduMoney();
    }
}
