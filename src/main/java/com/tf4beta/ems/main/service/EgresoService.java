package com.tf4beta.ems.main.service;

import com.tf4beta.ems.main.dao.EgresoDao;
import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.entity.Egreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EgresoService {

    @Autowired
    private EgresoDao egresoDao;

    public List<Egreso> findAll(){
        return egresoDao.findAll();
    }
    public List <Egreso> findAllWithBodegaDetails(){
        return egresoDao.findAllWithBodegaDetails();

    }
    public Egreso findById (Integer id_egreso_cab){
        Egreso Egreso = egresoDao.findById(id_egreso_cab);
        return Egreso;

    }
    public Egreso findByIdWithBodegaDetails (Integer id_egreso_cab){
        Egreso Egreso = egresoDao.findByIdAllDetails(id_egreso_cab);
        return Egreso;

    }
    public void save (Egreso egreso){
        egresoDao.save(egreso);

    }
    public void update(Egreso egreso){
        egresoDao.update(egreso);

    }
    public void delateByCodigo(Integer id_egreso_cab){

        egresoDao.delete(id_egreso_cab);
    }



}
