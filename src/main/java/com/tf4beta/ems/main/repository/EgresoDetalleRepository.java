package com.tf4beta.ems.main.repository;

import com.tf4beta.ems.main.entity.EgresoDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoDetalleRepository extends JpaRepository<EgresoDetalles, Integer> {
}
