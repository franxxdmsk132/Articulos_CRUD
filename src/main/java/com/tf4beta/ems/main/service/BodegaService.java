package com.tf4beta.ems.main.service;

import com.tf4beta.ems.main.dao.BodegaDao;
import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.reportes.EgresoReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BodegaService {

    @Autowired
    private BodegaDao bodegaDao;
    @Autowired
    private EgresoReporte egresoReporte;

    public List<Bodega> findAll(){
        return bodegaDao.findAllBodegas();
    }

    public Bodega findById (int codigo_bodega){
        Bodega bodega = bodegaDao.findByCodigoBodega(codigo_bodega);
        return bodega;
    }
    public  void save(Bodega bodega){
        bodegaDao.save(bodega);
    }

    public void update (Bodega bodega){
        bodegaDao.update(bodega);
    }

    public  void deleteByCodigoBodega(int codigoBodega){
        bodegaDao.delete(codigoBodega);
    }

    public List<Bodega> findByNameOrLocate (String search){
        List<Bodega> foundBodegas = bodegaDao.findByNameB(search);
        return foundBodegas;
    }

}
