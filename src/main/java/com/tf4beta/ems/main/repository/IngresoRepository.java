package com.tf4beta.ems.main.repository;

import com.tf4beta.ems.main.entity.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso , Integer> {
}
