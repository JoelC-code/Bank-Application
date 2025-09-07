import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FTarik {
    JButton button2;
    JTextField noRekening;
    JTextField nominal;
    AkunBank akun;

    public FTarik() {
        // Baca saldo awal dari file data.txt
        String rekening = "12345678";
        double saldoAwal = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String lastLine = reader.readLine();
            if (lastLine != null) {
                String[] parts = lastLine.split(" ");
                saldoAwal = Double.parseDouble(parts[parts.length - 1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        akun = new AkunBank(rekening, saldoAwal);

        JFrame frame = new JFrame();
        frame.setTitle("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420, 420);
        frame.setVisible(true);
        frame.setLayout(null);

        JButton button = new JButton();
        button.setBounds(10, 340, 100, 30);
        button.setText("Back");
        frame.add(button);

        button2 = new JButton();
        button2.setBounds(295, 340, 100, 30);
        button2.setText("Ok");
        frame.add(button2);

        JLabel label = new JLabel();
        label.setFont(new Font("Roboto", Font.BOLD, 27));
        label.setText("Tarik Tunai");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x123456));
        panel.setBounds(0, 0, 420, 100);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        JLabel namaNotransfer = new JLabel();
        namaNotransfer.setFont(new Font("Roboto", Font.BOLD, 18));
        namaNotransfer.setText("Rekening");
        namaNotransfer.setForeground(Color.BLACK);
        namaNotransfer.setBounds(100, 120, 200, 20);

        JLabel namaNominal = new JLabel();
        namaNominal.setFont(new Font("Roboto", Font.BOLD, 18));
        namaNominal.setText("Nominal");
        namaNominal.setForeground(Color.BLACK);
        namaNominal.setBounds(100, 190, 200, 20);

        noRekening = new JTextField(20);
        noRekening.setBounds(100, 150, 200, 30);

        nominal = new JTextField(20);
        nominal.setBounds(100, 220, 200, 30);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FCardless();
                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button2) {
                    String rekening = noRekening.getText();
                    String dana = nominal.getText();
                    double jumlah = Double.parseDouble(dana);

                    if (akun.getAccountNumber().equals(rekening)) {
                        if (jumlah > AkunBank.MAX_WITHDRAWAL_LIMIT) {
                            JOptionPane.showMessageDialog(frame, "Jumlah penarikan melebihi batas maksimal Rp 1.000.000");
                        } else {
                            boolean berhasil = akun.withdraw(jumlah);
                            if (berhasil) {
                                System.out.println("Rekening: " + rekening);
                                System.out.println("Nominal: " + dana);
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
                                    writer.write("Rekening: " + rekening + " Nominal: " + dana + " Saldo Baru: " + akun.getBalance() + "\n");
                                    System.out.println("Data saved successfully.");
                                } catch (IOException ex) {
                                    System.out.println("An error occurred while saving data.");
                                    ex.printStackTrace();
                                }
                            } else {
                                System.out.println("Saldo tidak cukup");
                            }
                        }
                    } else {
                        System.out.println("Nomor Akun Salah");
                    }

                    new FCardless();
                    frame.dispose();
                }
            }
        });

        panel.add(label);
        frame.add(panel);
        frame.add(namaNotransfer);
        frame.add(namaNominal);
        frame.add(noRekening);
        frame.add(nominal);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0xFFFFFF));

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new FTarik();
    }
}