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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javax.swing.border.Border;
import java.awt.BorderLayout;

public class FbuatAkun{
    public FbuatAkun(){
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

        JLabel TextInputPin = new JLabel();
        TextInputPin.setText("Masukan Pin (6 numbers):");
        TextInputPin.setForeground(Color.BLACK);
        TextInputPin.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputPin.setBounds((WidthUI - TextInput) / 2, 115, TextInput, 30);
        TextInputPin.setHorizontalAlignment(JLabel.CENTER);

        JTextField PinNumb = new JTextField();
        PinNumb.setBounds((WidthUI - TextInput) / 2, 145, TextInput, 20);
        PinNumb.setBorder(border);
        PinNumb.setFont(new Font("Roboto", Font.BOLD, 16));
        PinNumb.setHorizontalAlignment(JTextField.CENTER);

        JLabel TextInputName = new JLabel();
        TextInputName.setText("Masukan Nama Akun:");
        TextInputName.setForeground(Color.BLACK);
        TextInputName.setFont(new Font("Roboto", Font.BOLD, 13));
        TextInputName.setBounds((WidthUI - TextInput) / 2, 175, TextInput, 30);
        TextInputName.setHorizontalAlignment(JLabel.CENTER);

        JTextField NamaAkun = new JTextField();
        NamaAkun.setBounds((WidthUI - TextInput) / 2, 215, TextInput, 20);
        NamaAkun.setBorder(border);
        NamaAkun.setFont(new Font("Roboto", Font.BOLD, 16));
        NamaAkun.setHorizontalAlignment(JTextField.CENTER);

        JButton Submit = new JButton();
        Submit.setText("Make Account");
        Submit.setFont(new Font("Roboto", Font.BOLD, 12));
        Submit.setBounds((WidthUI - TextInput) / 2, 270, TextInput, 40);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String pinAccount = PinNumb.getText();
                String namaAccount = NamaAkun.getText();
                if (namaAccount.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nama akun kosong");
                } else if (!pinAccount.matches("\\d{6}")) {
                    JOptionPane.showMessageDialog(null, "Pin anda memiliki huruf atau kurang dari 6 angka");
                } else {
                    BufferedWriter akun = null;
                    try{
                        String FolderName = "DataBase";
                        File folderDB = new File(FolderName);
                        if (!folderDB.exists()){
                            folderDB.mkdirs();
                        }
                        String folderPath = folderDB + File.separator + "pinakun.txt";
                        FileWriter DeletingContentsOfText = new FileWriter(folderPath);
                        DeletingContentsOfText.write("");
                        DeletingContentsOfText.close();
                        akun = new BufferedWriter(new FileWriter(folderPath, true));
                        akun.write(pinAccount);
                        akun.newLine();
                        akun.write(namaAccount);
                    } catch (IOException ex){
                        JOptionPane.showMessageDialog(null,"Something went wrong, try again"+ ex.getMessage());
                    } finally {
                        if (akun != null){
                            try{
                                akun.close();
                            } catch (IOException ex){
                                System.out.println("Error detected");
                            } 
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Akun tekah terbuat!");
                    UI.dispose();
                    new AFMenuSebelumLogin();
                }
            }
        });
        JButton Cancleling = new JButton();
        Cancleling.setText("Cancle");
        Cancleling.setFont(new Font("Roboto", Font.BOLD, 10));
        Cancleling.setBounds((WidthUI - TextInput) / 2, 315, 100, 30);
        Cancleling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new AFMenuSebelumLogin();
                UI.dispose();
            }
        });

        UI.setLayout(null);
        UI.setTitle("Membuat akun");
        UI.setVisible(true);
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420,420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
        UI.repaint();
        UI.revalidate();

        UI.add(Header);
        UI.add(PinNumb); 
        UI.add(TextInputPin);
        UI.add(TextInputName);
        UI.add(NamaAkun);
        UI.add(Submit);
        UI.add(Cancleling);
        Header.add(headerContent);

        UI.setVisible(true);
    }
    public static void main(String[] args) {
        new FbuatAkun();
    }
}