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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.border.Border;
import java.awt.BorderLayout;

public class Transfering {
    String StoredPin = "";
    int limitSistem = 0;

    public Transfering() {
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

        JTextField AngkaInputRek = new JTextField();
        AngkaInputRek.setBounds((WidthUI - TextInput) / 2, 145, TextInput, 20);
        AngkaInputRek.setBorder(border);
        AngkaInputRek.setFont(new Font("Roboto", Font.BOLD, 16));
        AngkaInputRek.setHorizontalAlignment(JTextField.CENTER);

        JLabel TextInputNominal = new JLabel();
        TextInputNominal.setText("Masukan nominal transfer (maks 10.000.000):");
        TextInputNominal.setForeground(Color.BLACK);
        TextInputNominal.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputNominal.setBounds((WidthUI - TextInput) / 2, 175, TextInput, 30);
        TextInputNominal.setHorizontalAlignment(JLabel.CENTER);

        JTextField NominalInputKirim = new JTextField();
        NominalInputKirim.setBounds((WidthUI - TextInput) / 2, 215, TextInput, 20);
        NominalInputKirim.setBorder(border);
        NominalInputKirim.setFont(new Font("Roboto", Font.BOLD, 16));
        NominalInputKirim.setHorizontalAlignment(JTextField.CENTER);

        JButton Submit = new JButton();
        Submit.setText("Make Account");
        Submit.setFont(new Font("Roboto", Font.BOLD, 12));
        Submit.setBounds((WidthUI - TextInput) / 2, 270, TextInput, 40);
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String folderPath = "DataBase" + File.separator + "dataTrasnfer.txt";
                File folderDB = new File(folderPath);
                if (!folderDB.exists() || !folderDB.isFile()) {
                    JOptionPane.showMessageDialog(null, "Isi akun terlebih dahulu!");
                    return;
                }
                File file = new File(folderPath);
                if (file.exists() && file.canRead()) {
                    System.out.println("File exists and can be read: " + file.getAbsolutePath());
                } else {
                    System.err.println("File does not exist or cannot be read: " + file.getAbsolutePath());
                }   

                readFile(folderPath);
                System.err.println(folderPath);
                String Nominal = NominalInputKirim.getText();
                int NominalAngka = Integer.parseInt(Nominal);
                String Rekening = AngkaInputRek.getText();
                int noRekening = Integer.parseInt(Rekening);
                boolean AdaNomor = adaRekening(Rekening, folderPath);
                //sampai sini untuk baca filenya
                if (!Nominal.matches("\\d+") || !Rekening.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Nominal dan rekening harus dalam bentuk angka");
                    return;
                } else if (NominalAngka > 10000000) {
                    JOptionPane.showMessageDialog(null, "Maksimal limit sistem 1.000.000");
                    return;
                } else if (!AdaNomor) {
                    JOptionPane.showMessageDialog(null, "Nomor rekening salah atau tidak ada");
                    return;
                } else if (AdaNomor) {
                    String InputPin = JOptionPane.showInputDialog(null, "Masukan nomor pin anda:",JOptionPane.INFORMATION_MESSAGE);
                    for (int i = 2; i >= 0; i--) {
                        if (InputPin == null) {
                            JOptionPane.showMessageDialog(Submit, "Membatalkan");
                            return;
                        } else if (!InputPin.equalsIgnoreCase(StoredPin)) {
                            JOptionPane.showMessageDialog(null, "Pin salah, " + i + " kali percobaan tersisa");
                            if (i == 0) {
                                JOptionPane.showMessageDialog(null, "Pin anda salah 3 kali, keluar dari aplikasi...");
                                System.exit(0);
                            }
                        } else {
                            limitSistem = limitSistem + NominalAngka;
                            JOptionPane.showMessageDialog(null, "uang sebesar: " + Nominal + " || ke: " + noRekening);
                            break;
                        }
                    }
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

    // Ini function untuk carinya
    public static boolean adaRekening(String searchLine, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals(searchLine.trim())) {
                    return true;
                }
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return false;
    } private void readFile(String PinChecker) {
        try (BufferedReader ReadPin = new BufferedReader(new FileReader(PinChecker))){
            StoredPin = ReadPin.readLine();
            ReadPin.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage());
        }
    } public static void main(String[] args) {
        new Transfering();
    }
}
