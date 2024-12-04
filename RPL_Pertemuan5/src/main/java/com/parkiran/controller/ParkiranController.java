package com.parkiran.controller;

import com.parkiran.model.ModelParkiran;
import com.parkiran.repository.ParkiranRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParkiranController {
    
    @Autowired
    private ParkiranRepository parkiranRepository;
    
    public void tampilkanMenu() {
        Scanner scanner = new Scanner(System.in);
        int opsi;
        
        do {
            System.out.println("\n------ Menu Parkiran ------ ");
            System.out.println("1. Masuk Parkir");
            System.out.println("2. Keluar Parkir");
            System.out.println("3. Lihat Kendaraan di Parkiran");
            System.out.println("4. Exit Program");
            System.out.print("Masukkan Input anda: ");
            opsi = scanner.nextInt();
            scanner.nextLine();
            
            switch (opsi) {
                case 1:
                    masukParkir(scanner);
                    break;
                case 2:
                    keluarParkir(scanner);
                    break;
                case 3:
                    tampilkanKendaraanBelumKeluar();
                    break;
                case 4:
                    System.out.println("Keluar dari program");
                    break;
                default:
                    System.out.println("Opsi tidak valid");
            }
            
        } while (opsi != 4);
    }
    
    private void masukParkir(Scanner scanner) {
        System.out.print("Masukkan Plat Nomor: ");
        String platNomor = scanner.nextLine();
        
        String jenisKendaraan;
        do {
            System.out.print("Masukkan Jenis Kendaraan (Motor/Mobil): ");
            jenisKendaraan = scanner.nextLine().trim().toLowerCase();
            if (!jenisKendaraan.equals("motor") && !jenisKendaraan.equals("mobil")) {
                System.out.println("Jenis kendaraan tidak valid. Harap masukkan 'Motor' atau 'Mobil'.");
            }
        } while (!jenisKendaraan.equals("motor") && !jenisKendaraan.equals("mobil"));
        
        ModelParkiran parkiran = new ModelParkiran(platNomor, LocalDateTime.now(), jenisKendaraan);
        parkiranRepository.save(parkiran);
        System.out.println("Berhasil disimpan!");
    }
    
    private void keluarParkir(Scanner scanner) {
        System.out.print("Masukkan Plat Nomor: ");
        String platNomor = scanner.nextLine();
        
        Optional<ModelParkiran> optionalParkiran = parkiranRepository.findByPlatNomor(platNomor);
        if (optionalParkiran.isPresent()) {
            ModelParkiran parkiran = optionalParkiran.get();
            LocalDateTime waktuKeluar = LocalDateTime.now();
            
            // Hitung durasi parkir
            Duration durasi = Duration.between(parkiran.getWaktuMasuk(), waktuKeluar);
            long jamParkir = durasi.toHours();
            if (durasi.toMinutesPart() > 0) {
                jamParkir++;
            }
            
            // Hitung biaya parkir
            int tarifPerJam = parkiran.getJenisKendaraan().equals("motor") ? 5000 : 10000;
            long totalBiaya = jamParkir * tarifPerJam;
            
            System.out.println("Jenis Kendaraan: " + parkiran.getJenisKendaraan());
            System.out.println("Waktu Masuk: " + parkiran.getWaktuMasuk());
            System.out.println("Waktu Keluar: " + waktuKeluar);
            System.out.println("Lama Parkir: " + jamParkir + " jam");
            System.out.println("Total Biaya Parkir: Rp " + totalBiaya);
            
            // Hapus data parkir dari database setelah keluar
            parkiranRepository.delete(parkiran);
        } else {
            System.out.println("Kendaraan depan plat nomor: " + platNomor + " tidak ditemukan.");
        }
    }
    
    private void tampilkanKendaraanBelumKeluar() {
        List<ModelParkiran> kendaraanBelumKeluar = parkiranRepository.findAll();

        if (kendaraanBelumKeluar.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang sedang parkir.");
        } else {
            System.out.println("\n------ Kendaraan di Parkiran ------");
            for (ModelParkiran parkiran : kendaraanBelumKeluar) {
                System.out.println("ID: " + parkiran.getId());
                System.out.println("Plat Nomor: " + parkiran.getPlatNomor());
                System.out.println("Waktu Masuk: " + parkiran.getWaktuMasuk());
                System.out.println("Jenis Kendaraan: " + parkiran.getJenisKendaraan());
                System.out.println("-----------------------------");
            }
        }
    }
    
}
