package com.tf4beta.ems.main.repository;

import com.tf4beta.ems.main.entity.IngresoDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IngresoDetalleRepository extends JpaRepository<IngresoDetalles, Integer> {


//List<IngresoDetalles> findAllByOrderByLastNameAsc();
}
