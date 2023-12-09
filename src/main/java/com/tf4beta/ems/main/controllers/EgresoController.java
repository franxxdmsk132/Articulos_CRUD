package com.tf4beta.ems.main.controllers;
import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Egreso;
import com.tf4beta.ems.main.entity.EgresoDetalles;
import com.tf4beta.ems.main.service.BodegaService;
import com.tf4beta.ems.main.service.EgresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/egresos")
public class EgresoController {

    @Autowired
    private EgresoService egresoService;
    @Autowired
    private BodegaService bodegaService;

    @ModelAttribute("allBodegas")
    public List<Bodega> populateBodegas() {
        return bodegaService.findAll();
    }

    public EgresoController (EgresoService egresoService){
        this.egresoService = egresoService;
    }


    @GetMapping("/list")
    public String listEgresosBodega(Model theModel) {
        List<Egreso> egresos = egresoService.findAll();
        theModel.addAttribute("egresos", egresos);
        return "egresos/list-egresos";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        EgresoDetalles egresoDetalles = new EgresoDetalles();
        Egreso egreso = new Egreso();
        model.addAttribute("egresos", egreso);
        // Agrega la lista de todas las bodegas al modelo
        List<Bodega> allBodegas = bodegaService.findAll();
        model.addAttribute("allBodegas", allBodegas);

        // Agrega el atributo para la bodega seleccionada
        model.addAttribute("selectedBodegaId", null);
        return "egresos/egresos-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id_egreso_cab") Integer id_egreso_cab, Model model) {
        Egreso egreso = egresoService.findById(id_egreso_cab);

        // Obtener la lista de todas las bodegas
        List<Bodega> allBodegas = bodegaService.findAll();
        model.addAttribute("egresos", egreso);
        model.addAttribute("allBodegas", allBodegas);


        return "egresos/egresos-updateForm";
    }


    @PostMapping("/save")
    public String saveEgreso(@ModelAttribute("egresos") Egreso egreso , Model model) {

        egresoService.save(egreso);
        // Agrega el id de la bodega seleccionada al modelo
        model.addAttribute("selectedBodegaId", egreso.getBodega().getCodigo_bodega());


        return "redirect:/egresosDetalles/showFormForAdd";
    }

    @PostMapping("/update")
    public String updateEgreso(@ModelAttribute("egreso") Egreso egreso) {

        egresoService.update(egreso);

        return "redirect:/egresosDetalles/showFormForUpdate";

    }

    @GetMapping("/delete")
    public String deleteEgreso(@RequestParam("id_egreso_cab") Integer id_egreso_cab) {

        egresoService.delateByCodigo(id_egreso_cab);

        return "redirect:/egresosDetalles/list";

    }



}
