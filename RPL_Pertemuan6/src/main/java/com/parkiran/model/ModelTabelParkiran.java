package com.parkiran.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabelParkiran extends AbstractTableModel {
    private List<ModelParkiran> parkiranList;
    private String[] columnNames = {"Jenis Kendaraan", "Nomor Plat", "Waktu Masuk"};
    
    public ModelTabelParkiran(List<ModelParkiran> parkiranList) {
        this.parkiranList = parkiranList;
    }
    
    @Override
    public int getRowCount() {
        return parkiranList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelParkiran parkiran = parkiranList.get(rowIndex);
        switch(columnIndex) {
            case 0:
                int jenis = parkiran.getJenisKendaraan();
                String jenis_kendaraan;
                if (jenis == 1) {
                    jenis_kendaraan = "Motor";
                } else {
                    jenis_kendaraan = "Mobil";
                }
                return jenis_kendaraan;
            case 1:
                return parkiran.getPlatNomor();
            case 2:
                return parkiran.getWaktuMasuk();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public void setParkiranList(List<ModelParkiran> parkiranList) {
        this.parkiranList = parkiranList;
        fireTableDataChanged();
    }
    
}
