import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.Set;

public class FTransfering {
    JButton Submit;
    JTextField AngkaInputRek;
    JTextField NominalInputKirim;
    AkunBank akun;
    Set<String> validAccounts;

    public FTransfering() {
        // Load valid accounts
        validAccounts = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DataBase/dataTransfer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                validAccounts.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        JFrame UI = new JFrame();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        int WidthUI = 420;

        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());
        int TextInput = 300;

        JLabel headerContent = new JLabel();
        headerContent.setText("TRANSFER AKUN");
        headerContent.setFont(new Font("Roboto", Font.BOLD, 30));
        headerContent.setForeground(Color.WHITE);
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel Header = new JPanel();
        Header.setBackground(new Color(0x123456));
        Header.setBounds(0, 0, 420, 100);
        Header.setLayout(new BorderLayout());
        Header.add(headerContent, BorderLayout.CENTER);

        JLabel TextInputRek = new JLabel();
        TextInputRek.setText("Masukan Rekening (8 angka):");
        TextInputRek.setForeground(Color.BLACK);
        TextInputRek.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputRek.setBounds((WidthUI - TextInput) / 2, 115, TextInput, 30);
        TextInputRek.setHorizontalAlignment(JLabel.CENTER);

        AngkaInputRek = new JTextField();
        AngkaInputRek.setBounds((WidthUI - TextInput) / 2, 145, TextInput, 20);
        AngkaInputRek.setBorder(border);
        AngkaInputRek.setFont(new Font("Roboto", Font.BOLD, 16));
        AngkaInputRek.setHorizontalAlignment(JTextField.CENTER);

        JLabel TextInputNominal = new JLabel();
        TextInputNominal.setText("Masukan Nominal transfer (maks 10.000.000):");
        TextInputNominal.setForeground(Color.BLACK);
        TextInputNominal.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputNominal.setBounds((WidthUI - TextInput) / 2, 175, TextInput, 30);
        TextInputNominal.setHorizontalAlignment(JLabel.CENTER);

        NominalInputKirim = new JTextField();
        NominalInputKirim.setBounds((WidthUI - TextInput) / 2, 215, TextInput, 20);
        NominalInputKirim.setBorder(border);
        NominalInputKirim.setFont(new Font("Roboto", Font.BOLD, 16));
        NominalInputKirim.setHorizontalAlignment(JTextField.CENTER);

        Submit = new JButton();
        Submit.setText("Submit");
        Submit.setFont(new Font("Roboto", Font.BOLD, 12));
        Submit.setBounds((WidthUI - TextInput) / 2, 270, TextInput, 40);
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Submit) {
                    String rekening = AngkaInputRek.getText();
                    String dana = NominalInputKirim.getText();
                    double jumlah = Double.parseDouble(dana);

                    if (validAccounts.contains(rekening)) {
                        if (jumlah > AkunBank.MAX_WITHDRAWAL_LIMIT) {
                            JOptionPane.showMessageDialog(UI, "Jumlah penarikan melebihi batas maksimal Rp 1.000.000");
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

                    new FTransfernInformasi();
                    UI.dispose();
                }
            }
        });

        JButton Cancleling = new JButton();
        Cancleling.setText("Cancle");
        Cancleling.setFont(new Font("Roboto", Font.BOLD, 10));
        Cancleling.setBounds((WidthUI - TextInput) / 2, 315, 100, 30);
        Cancleling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI.dispose();
                new FTransfernInformasi();
            }
        });
        UI.setLayout(null);
        UI.setTitle("Membuat akun");
        UI.setVisible(true);
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420, 420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
        UI.repaint();
        UI.revalidate();

        UI.add(Header);
        UI.add(TextInputRek);
        UI.add(AngkaInputRek);
        UI.add(TextInputNominal);
        UI.add(NominalInputKirim);
        UI.add(Submit);
        UI.add(Cancleling);
        Header.add(headerContent);

        UI.setVisible(true);
    }

    public static void main(String[] args) {
        new FTransfering();
    }
}