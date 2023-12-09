package com.tf4beta.ems.main.controllers;


import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Ingreso;
import com.tf4beta.ems.main.entity.IngresoDetalles;
import com.tf4beta.ems.main.service.BodegaService;
import com.tf4beta.ems.main.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingresos")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;
    @Autowired
    private BodegaService bodegaService;

    @ModelAttribute("/allBodegas")
    public List<Bodega> populateBodega() {
        return bodegaService.findAll();

    }

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @GetMapping("/list")
    public String listIngresodBodega(Model theModel) {
        List<Ingreso> ingresos = ingresoService.findAll();
        theModel.addAttribute("ingresos", ingresos);
        return "ingresos/list-ingresos";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        IngresoDetalles ingresoDetalles = new IngresoDetalles();
        Ingreso ingreso = new Ingreso();
        model.addAttribute("ingresos", ingreso);
        List<Bodega> allBodegas = bodegaService.findAll();
        model.addAttribute("allBodegas", allBodegas);

        model.addAttribute("SelectBodegaId", null);
        return "ingresos/ingresos-form";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id_ingreso_cab") Integer id_ingreso_cab, Model model) {
        Ingreso ingreso = ingresoService.findById(id_ingreso_cab);

        List<Bodega> allBodegas = bodegaService.findAll();
        model.addAttribute("ingresos", ingreso);
        model.addAttribute("allBodegas", allBodegas);

        return "ingresos/ingresos-updateForm";
    }

    @PostMapping("/save")
    public String saveIngreso(@ModelAttribute("ingreso") Ingreso ingreso, Model model) {
        ingresoService.save(ingreso);
        model.addAttribute("selectBodegaId", ingreso.getBodega().getCodigo_bodega());

        return "redirect:/ingresoDetalles/showFormForAdd";
    }

    @PostMapping("/update")
    public String updateIngreso(@ModelAttribute("ingreso") Ingreso ingreso) {
        ingresoService.update(ingreso);
        return "redirect:/ingresoDetalles/showFormForUpdate";
    }

    @GetMapping("/delete")
    public String deleteIngreso(@RequestParam("id_ingreso_cab") Integer id_ingreso_cab) {

        ingresoService.delateByCodigo(id_ingreso_cab);
        return "redirect:/ingresoDetalles/list";
    }
}




