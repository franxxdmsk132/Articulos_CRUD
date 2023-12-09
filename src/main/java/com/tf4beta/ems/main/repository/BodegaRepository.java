package com.tf4beta.ems.main.repository;

import com.tf4beta.ems.main.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Integer> {


    //List<Bodega> findByNameBodegaAsc();
}
