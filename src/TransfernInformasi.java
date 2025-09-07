import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;

public class TransfernInformasi {
    AkunBank akun;

    public TransfernInformasi() {
        JFrame UI = new JFrame();
        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());

        JLabel headerContent = new JLabel();
        headerContent.setText("TRANSFER & INFORMASI");
        headerContent.setFont(new Font("Roboto", Font.BOLD, 27));
        headerContent.setForeground(Color.WHITE);
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel Header = new JPanel();
        Header.setBackground(new Color(0x123456));
        Header.setBounds(0, 0, 420, 100);
        Header.setLayout(new BorderLayout());
        Header.add(headerContent, BorderLayout.CENTER);

        JButton Balance = new JButton();
        Balance.setText("Balance");
        Balance.setFont(new Font("Roboto", Font.BOLD, 16));
        Balance.setBounds(20, 130, 370, 50);
        Balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
                    String lastLine = null;
                    String currentLine;
                    while ((currentLine = reader.readLine()) != null) {
                        lastLine = currentLine;
                    }
                    if (lastLine != null) {
                        String[] parts = lastLine.split(" ");
                        String saldo = parts[parts.length - 1];
                        JOptionPane.showMessageDialog(UI, "Saldo saat ini: " + saldo);
                    } else {
                        JOptionPane.showMessageDialog(UI, "Tidak ada data saldo.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(UI, "An error occurred while reading data: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
                

        JButton AddTransferAcc = new JButton();
        AddTransferAcc.setText("Add Transfer Account");
        AddTransferAcc.setFont(new Font("Roboto", Font.BOLD, 16));
        AddTransferAcc.setBounds(20, 180, 370, 50);
        AddTransferAcc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userPinNumb;
                do {
                    userPinNumb = JOptionPane.showInputDialog(null, "Masukan rekening pengguna:");
                    if (userPinNumb == null) {
                        UI.setVisible(true);
                        return;
                    } else if (!userPinNumb.matches("\\d{8}")) {
                        JOptionPane.showMessageDialog(null, "Rekening salah, coba lagi");
                    }
                } while (!userPinNumb.matches("\\d{8}"));
                String dataTransfer = "dataTrasnfer.txt";
                String FolderName = "DataBase";
                File folderDB = new File(FolderName);
                if (!folderDB.exists()) {
                    folderDB.mkdirs();
                }
                String FolderPath = folderDB + File.separator + dataTransfer;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FolderPath, true))) {
                    writer.write(userPinNumb);
                    writer.newLine();
                    JOptionPane.showMessageDialog(null, "Rekening telah ditambahkan!");
                } catch (IOException ex) {
                    System.out.println("error" + ex.getMessage());
                }
            }
        });

        JButton Transfer = new JButton();
        Transfer.setText("Transfer");
        Transfer.setFont(new Font("Roboto", Font.BOLD, 16));
        Transfer.setBounds(20, 230, 370, 50);
        Transfer.addActionListener(null);
        Transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Transfering();
                UI.dispose();
            }
        });

        JButton Exit = new JButton();
        Exit.setText("Exit");
        Exit.setFont(new Font("Roboto", Font.BOLD, 16));
        Exit.setBounds(20, 280, 370, 50);
        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UI.dispose();
            }
        });

        UI.setLayout(null);
        UI.setTitle("Transfer dan Informasi");
        UI.setVisible(true);
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420, 420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
        UI.repaint();
        UI.revalidate();

        UI.add(Header);
        UI.add(Balance);
        UI.add(AddTransferAcc);
        UI.add(Transfer);
        UI.add(Exit);
        Header.add(headerContent);
    }

    public static void main(String[] args) {
        new TransfernInformasi();
    }
}