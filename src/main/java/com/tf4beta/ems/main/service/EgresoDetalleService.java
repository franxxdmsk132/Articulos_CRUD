package com.tf4beta.ems.main.service;

import com.tf4beta.ems.main.dao.EgresoDetallesDao;
import com.tf4beta.ems.main.entity.EgresoDetalles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EgresoDetalleService {

    @Autowired
    private EgresoDetallesDao egresoDetallesDao;


    public List<EgresoDetalles> findAll(){
        return egresoDetallesDao.findAllWithAllDetails();
    }

    public EgresoDetalles findById (Integer id_egreso_detalles){
        EgresoDetalles egresoDetalles = egresoDetallesDao.findByIdWithAllDetails(id_egreso_detalles);
        return egresoDetalles;
    }

    public void save (EgresoDetalles egresoDetalles){
        egresoDetallesDao.save(egresoDetalles);
    }

    public void update (EgresoDetalles egresoDetalles){
        egresoDetallesDao.update(egresoDetalles);
    }

    public void deleteByCodigo(Integer id_egreso_detalles){
        egresoDetallesDao.deleteEgresoDetallesAndEgreso(id_egreso_detalles);
    }




}
