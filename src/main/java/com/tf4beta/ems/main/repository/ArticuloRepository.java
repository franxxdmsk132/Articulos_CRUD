package com.tf4beta.ems.main.repository;

import com.tf4beta.ems.main.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo , Integer> {
}
