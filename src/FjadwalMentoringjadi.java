import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FjadwalMentoringjadi {
    private DefaultTableModel tableModel;

    public FjadwalMentoringjadi() {
        JFrame UI = new JFrame();
        UI.setLayout(new BorderLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        String[] titleColumn = {"Tanggal", "Waktu", "Mentor"};
        tableModel = new DefaultTableModel(titleColumn, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        UI.add(scrollPane, BorderLayout.CENTER);
        table.setBorder(border);

        ImageIcon logo = new ImageIcon("Logo.png");
        UI.setIconImage(logo.getImage());

        JLabel headerContent = new JLabel();
        headerContent.setText("JADWAL MENTORING");
        headerContent.setFont(new Font("Roboto", Font.BOLD, 30));
        headerContent.setForeground(Color.WHITE);
        headerContent.setHorizontalAlignment(JLabel.CENTER);

        JPanel Header = new JPanel();
        Header.setBackground(new Color(0x123456));
        Header.setLayout(new BorderLayout());
        Header.add(headerContent, BorderLayout.CENTER);
        
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(Header, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Roboto", Font.BOLD, 12));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UI.dispose();
                new FMentoring();
            }
        });
            

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        
        dataJadwaKeTable();
        
        UI.setContentPane(contentPane);
        UI.setVisible(true);
        UI.setTitle("Jadwal Mentoring");
        UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.setSize(420, 420);
        UI.setResizable(false);
        UI.getContentPane().setBackground(new Color(0xFFFFFF));
    }

    public void dataJadwaKeTable() {
        try {
            String filePath = "DataBase" + File.separator + "dataMentoring.txt";
            System.out.println("Attempting to read file from: " + filePath);
            File file = new File(filePath);
            if (!file.exists() || !file.canRead()) {
                System.err.println("Data tidak bisa dibaca: " + file.getAbsolutePath());
                return;
            }
            tableModel.setRowCount(0);
            try (BufferedReader readingData = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = readingData.readLine()) != null) {
                    if (line.startsWith("Tanggal: ")) {
                        String tanggal = line.split(": ")[1];
                        String waktu = readingData.readLine().split(": ")[1];
                        String MentorNama = readingData.readLine().split(": ")[1];
                        tableModel.addRow(new Object[]{tanggal, waktu, MentorNama});
                        readingData.readLine();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FjadwalMentoringjadi();
    }
}
