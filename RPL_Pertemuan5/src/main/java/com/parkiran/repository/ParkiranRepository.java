package com.parkiran.repository;

import com.parkiran.model.ModelParkiran;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkiranRepository extends JpaRepository<ModelParkiran, Long> {
    public Optional<ModelParkiran> findByPlatNomor(String platNomor);
    
    // Method untuk mengambil data kendaraan yang belum keluar
    List<ModelParkiran> findAll();
}
