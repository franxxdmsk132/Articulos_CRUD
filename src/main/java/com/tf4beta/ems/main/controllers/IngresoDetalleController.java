package com.tf4beta.ems.main.controllers;

import com.lowagie.text.DocumentException;
import com.tf4beta.ems.main.entity.*;
import com.tf4beta.ems.main.reportes.EgresoReporte;
import com.tf4beta.ems.main.reportes.IngresoReporte;
import com.tf4beta.ems.main.service.ArticleService;
import com.tf4beta.ems.main.service.BodegaService;
import com.tf4beta.ems.main.service.IngresoDetalleService;
import com.tf4beta.ems.main.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ingresoDetalles")
public class IngresoDetalleController {


    @Autowired
    private IngresoDetalleService ingresoDetalleService;
    @Autowired
    private BodegaService bodegaService;
    @Autowired
    private IngresoService ingresoService;
    @Autowired
    private ArticleService articleService;

    public IngresoDetalleController(IngresoDetalleService ingresoDetalleService) {
        this.ingresoDetalleService = ingresoDetalleService;
    }

    @ModelAttribute("allBodegas")
    public List<Bodega> populateBodegas() {
        return bodegaService.findAll();
    }

    @ModelAttribute("allArticulos")
    public List<Articulo> populateArticulos() {
        return articleService.findAll();
    }
    @ModelAttribute("allIngresos")
    public List<Ingreso>populateIngreso(){
        return ingresoService.findAll();}


    @GetMapping("/list")
    public String listaIngresoDetalle(Model model) {
        List<IngresoDetalles> ingresoDetalles = ingresoDetalleService.findAll();
        model.addAttribute("ingresoDetalles", ingresoDetalles);
        return "ingresos/list-ingresosDetalles";

    }

    @RequestMapping("/showIngresoDetalles")
    public String viewIngresoDetalles(@RequestParam("id_ingresos_detalle") Integer id_ingreso_detalle, Model model) {
        IngresoDetalles ingresoDetalles = ingresoDetalleService.findById(id_ingreso_detalle);
        model.addAttribute("ingresoDetalles", ingresoDetalles);
        return "ingresos/ingresosDetalles-view";
    }

    @GetMapping("/showFormForAdd")
    public String showFormFordAdd(Model model) {
        IngresoDetalles ingresoDetalles = new IngresoDetalles();
        Ingreso ingreso = new Ingreso();
        model.addAttribute("ingresos", ingreso);
        model.addAttribute("ingresosDetalles", ingresoDetalles);
        return "ingresos/ingresosDetalles-form";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormFormForUpdate(@RequestParam("id_ingresos_detalle") Integer id_ingresos_detalle, Model model) {
        IngresoDetalles ingresoDetalles = ingresoDetalleService.findById(id_ingresos_detalle);

        //lista ingresos
        List<Ingreso> allIngresos = ingresoService.findAll();
        //lista articulos
        List<Articulo> allArticulos = articleService.findAll();
        model.addAttribute("ingresoDetallesu", ingresoDetalles);
        model.addAttribute("allIngresos", allIngresos);
        model.addAttribute("allArticulos", allArticulos);
        return "ingresos/ingresosDetalles-updateForm";

    }

    @PostMapping("/save")
    public String saveIngresosDetalles(@ModelAttribute("ingresoDetalles") IngresoDetalles ingresoDetalles) {

        ingresoDetalleService.save(ingresoDetalles);
        return "redirect:/ingresoDetalles/list";

    }

    @PostMapping("/update")
    public String updateIngresoDetalles(@ModelAttribute("ingresoDetalles") IngresoDetalles ingresoDetalles) {
        ingresoDetalleService.update(ingresoDetalles);
        return "redirect:/ingresoDetalles/list";

    }

    @GetMapping("/delete")
    public String deleteIngresoDetalle(@RequestParam("id_ingresos_detalle") Integer id_ingreso_detalles) {

        ingresoDetalleService.deleteBycodigo(id_ingreso_detalles);
        return "redirect:/ingresoDetalles/list";
    }
    @GetMapping("/exportarPDF")
    public void exportarListadoDeEmpleadosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=INGRESOS_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<IngresoDetalles> ingresoDetalles = ingresoDetalleService.findAll();

        IngresoReporte exporter = new IngresoReporte(ingresoDetalles);
        exporter.exportar(response);
    }
}







