import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;

public class FMenjadwalkanMentor {
    public FMenjadwalkanMentor() {
        JFrame UI = new JFrame();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        int WidthUI = 420;

        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());
        int TextInput = 300;

        JLabel headerContent = new JLabel();
        headerContent.setText("BUAT JADWAL");
        headerContent.setFont(new Font("Roboto", Font.BOLD, 30));
        headerContent.setForeground(Color.WHITE);
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel Header = new JPanel();
        Header.setBackground(new Color(0x123456));
        Header.setBounds(0, 0, 420, 100);
        Header.setLayout(new BorderLayout());
        Header.add(headerContent, BorderLayout.CENTER);

        // meminta input tanggal
        JLabel TextInputHari = new JLabel();
        TextInputHari.setText("Tanggal (format = DD/MM/YYYY)");
        TextInputHari.setForeground(Color.BLACK);
        TextInputHari.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputHari.setBounds((WidthUI - TextInput) / 2, 105, TextInput, 30);
        TextInputHari.setHorizontalAlignment(JLabel.CENTER);

        JTextField InputHari = new JTextField();
        InputHari.setBounds((WidthUI - TextInput) / 2, 130, TextInput, 20);
        InputHari.setBorder(border);
        InputHari.setFont(new Font("Roboto", Font.BOLD, 16));
        InputHari.setHorizontalAlignment(JTextField.CENTER);

        // meminta waktu mentor
        JLabel TextInputJam = new JLabel();
        TextInputJam.setText("Jam (format = HH:MM)");
        TextInputJam.setForeground(Color.BLACK);
        TextInputJam.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputJam.setBounds((WidthUI - TextInput) / 2, 155, TextInput, 30);
        TextInputJam.setHorizontalAlignment(JLabel.CENTER);

        JTextField InputJam = new JTextField();
        InputJam.setBounds((WidthUI - TextInput) / 2, 180, TextInput, 20);
        InputJam.setBorder(border);
        InputJam.setFont(new Font("Roboto", Font.BOLD, 16));
        InputJam.setHorizontalAlignment(JTextField.CENTER);

        // meminta nama mentor
        JLabel TextNamaMentor = new JLabel();
        TextNamaMentor.setText("Pilih mentor:");
        TextNamaMentor.setForeground(Color.BLACK);
        TextNamaMentor.setFont(new Font("Roboto", Font.BOLD, 13));
        TextNamaMentor.setBounds((WidthUI - TextInput) / 2, 200, TextInput, 40);
        TextNamaMentor.setHorizontalAlignment(JLabel.CENTER);

        JButton Cancleling = new JButton();
        Cancleling.setText("Cancel");
        Cancleling.setFont(new Font("Roboto", Font.BOLD, 10));
        Cancleling.setBounds((WidthUI - TextInput) / 2, 340, 100, 30);
        Cancleling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UI.dispose();
                new FMentoring();
            }
        });

        JPanel panel = new JPanel();
        panel.setBounds((WidthUI - TextInput) / 2, 235, TextInput, 40);
        panel.setBorder(border);
        String[] items = { "A. Ayman M.Sci, S.Sci", "Joel C. S.Sos" };
        JComboBox<String> dropdown = new JComboBox<>(items);
        dropdown.setFont(new Font("Roboto", Font.BOLD, 13));
        dropdown.setPreferredSize(new Dimension(TextInput, 40));

        JButton Submit = new JButton();
        Submit.setText("Make Account");
        Submit.setFont(new Font("Roboto", Font.BOLD, 12));
        Submit.setBounds((WidthUI - TextInput) / 2, 300, TextInput, 40);
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Tanggal = InputHari.getText();
                String waktu = InputJam.getText();
                String selectedOption = (String) dropdown.getSelectedItem();
                try {
                    String[] TanggalMentoring = Tanggal.split("/");
                    String[] JamMentoring = waktu.split(":");

                    int hari = Integer.parseInt(TanggalMentoring[0]);
                    int bulan = Integer.parseInt(TanggalMentoring[1]);
                    int tahun = Integer.parseInt(TanggalMentoring[2]);
                    int jam = Integer.parseInt(JamMentoring[0]);
                    int menit = Integer.parseInt(JamMentoring[1]);

                    boolean cekValidTanggal = CekValidation(hari, bulan, tahun);
                    boolean cekValidJam = cekValidationJam(jam, menit);

                    if (!cekValidJam) {
                        JOptionPane.showMessageDialog(null, "Jam invalid, coba lagi");
                    } else if (!cekValidTanggal) {
                        JOptionPane.showMessageDialog(null, "Tanggal invalid, coba lagi");
                    } else {
                        BufferedWriter akun = null;
                        try {
                            String FolderName = "DataBase";
                            File folderDB = new File(FolderName);
                            if (!folderDB.exists()) {
                                folderDB.mkdirs();
                            }
                            String folderPath = folderDB + File.separator + "dataMentoring.txt";
                            akun = new BufferedWriter(new FileWriter(folderPath, true));
                            akun.write("Tanggal: " + Tanggal);
                            akun.newLine();
                            akun.write("Jam: " + waktu);
                            akun.newLine();
                            akun.write("Mentor: " + selectedOption);
                            akun.newLine();
                            akun.write("---------------------------------");
                            akun.newLine();
                            akun.close();
                            JOptionPane.showMessageDialog(null, "Jadwal terbuat");
                            UI.dispose();
                            new FMentoring();
                        } catch (IOException eh) {
                            JOptionPane.showMessageDialog(null, "Error menulis file");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input format: " + ex.getMessage());
                }
            }
        });

        panel.add(dropdown);
        UI.setLayout(null);
        UI.add(Header);
        UI.add(TextInputHari);
        UI.add(InputHari);
        UI.add(TextInputJam);
        UI.add(InputJam);
        UI.add(TextNamaMentor);
        UI.add(panel);
        UI.add(Submit);
        UI.add(Cancleling);

        UI.setVisible(true);
        UI.setTitle("Pengajuan Microcredit");
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420, 420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
        UI.repaint();
        UI.revalidate();
    }

    public boolean CekValidation(int hari, int bulan, int Tahun) {
        if (hari > 30 || hari < 0 || bulan < 0 || bulan > 12 || Tahun != 2025) {
            return false;
        } else {
            return true;
        }
    }

    public boolean cekValidationJam(int jam, int menit) {
        if (jam > 23 || jam < 0 || menit < 0 || menit > 59) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        new FMenjadwalkanMentor();
    }
}
