import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.BorderLayout;

public class FMicrocredit {
    String StoredPin = "";
    private static File selectedFile = null;
    public FMicrocredit() {
        JFrame UI = new JFrame();        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        int WidthUI = 420;
 
        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());
        int TextInput = 300;

        JLabel headerContent = new JLabel();
        headerContent.setText("REGISTRASI");
        headerContent.setFont(new Font("Roboto",Font.BOLD,30));
        headerContent.setForeground(Color.WHITE);
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel Header = new JPanel();
        Header.setBackground(new Color(0x123456));
        Header.setBounds(0, 0, 420, 100);
        Header.setLayout(new BorderLayout());
        Header.add(headerContent, BorderLayout.CENTER);

        JLabel TextInputNama = new JLabel();
        TextInputNama.setText("Nama Pengaju");
        TextInputNama.setForeground(Color.BLACK);
        TextInputNama.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputNama.setBounds((WidthUI - TextInput) / 2, 115, TextInput, 30);
        TextInputNama.setHorizontalAlignment(JLabel.CENTER);

        JTextField InputNama = new JTextField();
        InputNama.setBounds((WidthUI - TextInput) / 2, 140, TextInput, 20);
        InputNama.setBorder(border);
        InputNama.setFont(new Font("Roboto", Font.BOLD, 16));
        InputNama.setHorizontalAlignment(JTextField.CENTER);

        //part 2
        JLabel TextEmail = new JLabel();
        TextEmail.setText("Email Pengaju:");
        TextEmail.setForeground(Color.BLACK);
        TextEmail.setFont(new Font("Roboto", Font.BOLD, 13));
        TextEmail.setBounds((WidthUI - TextInput) / 2, 165, TextInput, 30);
        TextEmail.setHorizontalAlignment(JLabel.CENTER);

        JTextField Email = new JTextField();
        Email.setBounds((WidthUI - TextInput) / 2, 190, TextInput, 20);
        Email.setBorder(border);
        Email.setFont(new Font("Roboto", Font.BOLD, 16));
        Email.setHorizontalAlignment(JTextField.CENTER);

        //part 3
        JLabel TextBesarPangajuan = new JLabel();
        TextBesarPangajuan.setText("Besar Kredit Pengaju (maks 10.000.000):");
        TextBesarPangajuan.setForeground(Color.BLACK);
        TextBesarPangajuan.setFont(new Font("Roboto", Font.BOLD, 13));
        TextBesarPangajuan.setBounds((WidthUI - TextInput) / 2, 215, TextInput, 30);
        TextBesarPangajuan.setHorizontalAlignment(JLabel.CENTER);

        JTextField BesarKredit = new JTextField();
        BesarKredit.setBounds((WidthUI - TextInput) / 2, 240, TextInput, 20);
        BesarKredit.setBorder(border);
        BesarKredit.setFont(new Font("Roboto", Font.BOLD, 16));
        BesarKredit.setHorizontalAlignment(JTextField.CENTER);

        JButton MasukanDokumen = new JButton();
        MasukanDokumen.setText("Dokumen Persetujuan (bisa ambil dibank)");
        MasukanDokumen.setFont(new Font("Roboto", Font.BOLD, 12));
        MasukanDokumen.setBounds((WidthUI - TextInput) / 2, 270, TextInput, 30);
        MasukanDokumen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Masukan Dokumen (.pdf)");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));
                fileChooser.setMultiSelectionEnabled(false);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Dokumen: " + selectedFile.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak ada dokumen.");
                }
            }
        });

        JButton Submit = new JButton();
        Submit.setText("Ajukan");
        Submit.setFont(new Font("Roboto", Font.BOLD, 12));
        Submit.setBounds(60, 320, 140, 40);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NamaUser = InputNama.getText();
                String EmailUser = Email.getText();

                String BesarKreditUser = BesarKredit.getText();
                String FolderName = "DataBase";
                File folderDB = new File(FolderName);
                if (!folderDB.exists()) {
                    JOptionPane.showMessageDialog(null, "Buat akun terleih dahulu");
                    return;
                } else {
                    String folderPath = folderDB + File.separator + "pinakun.txt";
                    readFile(folderPath);
                }
                if(NamaUser.equals(null) && EmailUser.equals(null) && BesarKreditUser.equals(null)&& selectedFile == null){
                    JOptionPane.showMessageDialog(null, "Terdapat dalah satu bagian belum terisi");
                } else if (BesarKreditUser.equals("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Pengajuan kredit harus berangka");
                } else {
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
                            JOptionPane.showMessageDialog(null, "Formulir sukses terkirin! tunggu pemberitahuan mengena tindakan lanjutan, terima kasih");
                            UI.dispose();
                            new FMentoring();
                            break;
                        }
                    }
                }
            }
        });

        JButton Cancleling = new JButton();
        Cancleling.setText("Cancle");
        Cancleling.setFont(new Font("Roboto", Font.BOLD, 10));
        Cancleling.setBounds(220, 320, 140, 40);
        Cancleling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FMentoring();
                UI.dispose();
            }
        });

        UI.setLayout(null);
        UI.setTitle("Pengajuan Microcredit");
        UI.setVisible(true);
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420,420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
        UI.repaint();
        UI.revalidate();

        UI.add(Header);
        UI.add(TextInputNama); 
        UI.add(InputNama);
        UI.add(TextEmail);
        UI.add(Email);
        UI.add(TextBesarPangajuan);
        UI.add(BesarKredit);
        UI.add(MasukanDokumen);
        UI.add(Submit);
        UI.add(Cancleling);
    } private void readFile(String PinChecker) {
        try {
            BufferedReader ReadPin = new BufferedReader(new FileReader(PinChecker));
            StoredPin = ReadPin.readLine();
            ReadPin.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage());
        }
    } public static void main(String[] args) {
        new FMicrocredit();
    }
}
