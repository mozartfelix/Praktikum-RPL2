package com.parkiran.view;

import com.parkiran.controller.ParkiranController;
import com.parkiran.model.ModelParkiran;
import com.parkiran.model.ModelTabelParkiran;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import javax.swing.table.JTableHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkiranGUI extends JFrame {

    @Autowired
    private ParkiranController parkiranController;

    private JTextField platNomorField;
    private JRadioButton motorRadioButton;
    private JRadioButton mobilRadioButton;
    private JButton masukButton;
    private JButton keluarButton;
    private JTextField platKeluarField;
    private JTable parkiranTable;
    private ModelTabelParkiran tableModel;

    public ParkiranGUI(ParkiranController parkiranController) {
        this.parkiranController = parkiranController;
        // Set up JFrame
        setTitle("Parkiran Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout());

        // Panel for form input
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        JLabel platNomorLabel = new JLabel("Plat Nomor:");
        platNomorField = new JTextField();
        formPanel.add(platNomorLabel);
        formPanel.add(platNomorField);

        JLabel jenisKendaraanLabel = new JLabel("Jenis Kendaraan:");
        motorRadioButton = new JRadioButton("Motor");
        mobilRadioButton = new JRadioButton("Mobil");

        ButtonGroup kendaraanGroup = new ButtonGroup();
        kendaraanGroup.add(motorRadioButton);
        kendaraanGroup.add(mobilRadioButton);

        formPanel.add(jenisKendaraanLabel);
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(motorRadioButton);
        radioPanel.add(mobilRadioButton);
        formPanel.add(radioPanel);

        masukButton = new JButton("Masuk");
        keluarButton = new JButton("Keluar");

        JLabel platKeluarLabel = new JLabel("Plat Nomor Keluar:");
        platKeluarField = new JTextField();
        formPanel.add(platKeluarLabel);
        formPanel.add(platKeluarField);
        formPanel.add(masukButton);
        formPanel.add(keluarButton);

        add(formPanel, BorderLayout.NORTH);

        // Table for displaying parkiran data
        tableModel = new ModelTabelParkiran(new ArrayList<>());
        parkiranTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(parkiranTable);

        // Set header style
        JTableHeader header = parkiranTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));

        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        masukButton.addActionListener(e -> handleMasukParkir(kendaraanGroup));
        keluarButton.addActionListener(e -> handleKeluarParkir());
        updateTableData();
    }

    private void handleMasukParkir(ButtonGroup kendaraanGroup) {
        String platNomor = platNomorField.getText();

        int jenisKendaraan;
        if (motorRadioButton.isSelected()) {
            jenisKendaraan = 1; // Motor
        } else if (mobilRadioButton.isSelected()) {
            jenisKendaraan = 2; // Mobil
        } else {
            JOptionPane.showMessageDialog(this, "Pilih jenis kendaraan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelParkiran parkiran = new ModelParkiran(platNomor, LocalDateTime.now(), jenisKendaraan);

        // Call controller method
        parkiranController.masukParkir(parkiran);

        JOptionPane.showMessageDialog(this, "Kendaraan berhasil masuk parkir!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Refresh table data
        updateTableData();

        // Clear input fields
        platNomorField.setText("");
        kendaraanGroup.clearSelection();
    }

    private void handleKeluarParkir() {
        String platNomorKeluar = platKeluarField.getText();

        // Call controller method and get the result
        String result = parkiranController.keluarParkir(platNomorKeluar);

        if (result.startsWith("Kendaraan")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Biaya Parkir: Rp " + result, "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        // Refresh table data
        updateTableData();

        // Clear input field
        platKeluarField.setText("");
    }

    private void updateTableData() {
        // Call controller to fetch updated data
        List<ModelParkiran> updatedData = parkiranController.getAllParkiran();
        tableModel.setParkiranList(updatedData);
    }
    
    public static void main(String[] args) {
        // Assume ParkiranController is already properly configured via Spring
        ParkiranController controller = new ParkiranController(); // Placeholder
        ParkiranGUI gui = new ParkiranGUI(controller);
        gui.setVisible(true);
    }
}
