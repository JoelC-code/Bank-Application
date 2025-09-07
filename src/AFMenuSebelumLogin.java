import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AFMenuSebelumLogin {
    private String StoredPin = "";
    public AFMenuSebelumLogin() {
        int WidthUI = 420;
        int Besartext = 20;
        JFrame UI = new JFrame();
        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());

        JLabel headerContent = new JLabel();
        headerContent.setText("BANK KOTAK");
        headerContent.setForeground(Color.WHITE);
        headerContent.setFont(new Font("Roboto", Font.BOLD, 30));
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel header = new JPanel();
        header.setBackground(new Color(0x004B84));
        header.setBounds(0, 0, 420, 100);
        header.setLayout(new BorderLayout());
        header.add(headerContent, BorderLayout.CENTER);

        JButton banking = new JButton();
        banking.setBounds((WidthUI - 330) / 2, 120, 330, 50);
        banking.setForeground(new Color(0x000000));
        banking.setText("Login Akun");
        banking.setFont(new Font("Roboto", Font.BOLD, Besartext));
        banking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FolderName = "DataBase";
                File folderDB = new File(FolderName);
                if (!folderDB.exists()) {
                    JOptionPane.showMessageDialog(null, "Buat akun terleih dahulu");
                    return;
                } else {
                    String folderPath = folderDB + File.separator + "pinakun.txt";
                    readFile(folderPath);
                } for (int i = 2; i >= 0; i--) {
                    String Respond = JOptionPane.showInputDialog(null, "Masukan pin anda:", JOptionPane.INFORMATION_MESSAGE);
                    if (Respond == null) {
                        JOptionPane.showMessageDialog(null, "Membatalkan");
                        return;
                    }
                    if (!Respond.equals(StoredPin)) {
                        JOptionPane.showMessageDialog(null, "Salah pin, percobaan ke: " + i);
                        if (i == 0) {
                            JOptionPane.showMessageDialog(null, "PIN salah 3 kali, keluar dari aplikasi");
                            UI.dispose();
                        }
                    } else {
                        UI.dispose();
                        new FMainMenu();
                        break;
                    }
                }
            }
        });

        JButton buatAkun = new JButton();
        buatAkun.setBounds((WidthUI - 330) / 2, 190, 330, 50);
        buatAkun.setForeground(new Color(0x000000));
        buatAkun.setText("Buat Akun");
        buatAkun.setFont(new Font("Roboto", Font.BOLD, Besartext));
        buatAkun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FbuatAkun();
                UI.dispose();
            }
        });

        JButton exit = new JButton();
        exit.setBounds((WidthUI - 330) / 2, 260, 330, 50);
        exit.setForeground(new Color(0x000000));
        exit.setText("Keluar");
        exit.setFont(new Font("Roboto", Font.BOLD, Besartext));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        UI.setTitle("Welcome!");
        UI.setVisible(true);
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420, 420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));

        UI.repaint();
        UI.revalidate();

        UI.setLayout(null);
        header.add(headerContent);
        UI.add(banking);
        UI.add(buatAkun);
        UI.add(header);
        UI.add(exit);
    }

    private void readFile(String PinChecker) {
        try {
            BufferedReader ReadPin = new BufferedReader(new FileReader(PinChecker));
            StoredPin = ReadPin.readLine();
            ReadPin.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occured: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AFMenuSebelumLogin();
    }
}
