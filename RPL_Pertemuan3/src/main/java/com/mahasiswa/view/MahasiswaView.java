/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mahasiswa.view;

import com.mahasiswa.controller.MahasiswaController;
import com.mahasiswa.model.MahasiswaDAO;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class MahasiswaView {
    public static void main(String[] args) {
        MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();
        MahasiswaController mahasiswaController = new MahasiswaController(mahasiswaDAO);
        
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        
        while(true) {
            System.out.println("Menu: ");
            System.out.println("1. Tampilkan seluruh mhs");
            System.out.println("2. Tambahkan mhs");
            System.out.println("3. Update mhs");
            System.out.println("4. Hapus mhs");
            System.out.println("5. Cek Koneksi");
            System.out.println("6. Keluar");
            System.out.print("PILIH OPSI: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch(pilihan) {
                case 1:
                    mahasiswaController.displayAllMahasiswa();
                    break;
                case 2:
                    System.out.print("Masukkan NPM: ");
                    String npm = scanner.next();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.next();
                    System.out.print("Masukkan Semester: ");
                    int semester = scanner.nextInt();
                    System.out.print("Masukkan IPK: ");
                    float ipk = scanner.nextFloat();
                    
                    System.out.println(npm + nama + semester + ipk);
                    mahasiswaController.addMahasiswa(npm, nama, semester, ipk);
                    break;
                case 3:
                    System.out.print("Masukkan ID mahasiswa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Masukkan NPM: ");
                    String npmBaru = scanner.next();
                    System.out.print("Masukkan Nama: ");
                    String namaBaru = scanner.next();
                    System.out.print("Masukkan Semester: ");
                    int semesterBaru = scanner.nextInt();
                    System.out.print("Masukkan IPK: ");
                    float ipkBaru = scanner.nextFloat();
                    
                    System.out.println(npmBaru + namaBaru + semesterBaru + ipkBaru);
                    mahasiswaController.updateMahasiswa(id, npmBaru, namaBaru, semesterBaru, ipkBaru);
                    break;
                case 4:
                    System.out.print("Masukkan ID mahasiswa: ");
                    int idHapus = scanner.nextInt();
                    mahasiswaController.deleteMahasiswa(idHapus);
                    break;
                case 5:
                    mahasiswaController.checkDatabaseConnection();
                    break;
                case 6:
                    mahasiswaController.closeConnection();
                    System.out.println("Program Selesai");
                    return;
                default:
                    System.out.println("Input tidak valid");
            }
        }
    }
}
