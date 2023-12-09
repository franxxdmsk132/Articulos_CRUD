package com.tf4beta.ems.main.controllers;

import com.lowagie.text.DocumentException;
import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Egreso;
import com.tf4beta.ems.main.entity.EgresoDetalles;
import com.tf4beta.ems.main.service.ArticleService;
import com.tf4beta.ems.main.service.BodegaService;
import com.tf4beta.ems.main.service.EgresoDetalleService;
import com.tf4beta.ems.main.service.EgresoService;
import com.tf4beta.ems.main.reportes.EgresoReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/egresosDetalles")
public class EgresoDetalleController {

    @Autowired
    private EgresoDetalleService egresoDetalleService;
    @Autowired
    private BodegaService bodegaService;
    @Autowired
    private EgresoService egresoService;
    @Autowired
    private ArticleService articleService;


    public EgresoDetalleController(EgresoDetalleService egresoDetalleService) {
        this.egresoDetalleService = egresoDetalleService;
    }

    @ModelAttribute("allBodegas")
    public List<Bodega> populateBodegas() {
        return bodegaService.findAll();
    }

    @ModelAttribute("allArticulos")
    public List<Articulo> populateArticulos() {
        return articleService.findAll();
    }

    @ModelAttribute("allEgresos")
    public List<Egreso> populateEgresos() {
        return egresoService.findAll();
    }

    @GetMapping("/list")
    public String listaEgresosDetalles(Model model) {
        List<EgresoDetalles> egresoDetalles = egresoDetalleService.findAll();
        model.addAttribute("egresosDetalles", egresoDetalles);
        return "egresos/list-egresosDetalles";
    }
    @GetMapping("/json")
    public ResponseEntity<?> listAll() {

        return ResponseEntity.ok(egresoDetalleService.findAll());
    }

    @RequestMapping("/showEgresoDetallesDetails")
    public String viewEgresosDetalles(@RequestParam("id_egreso_detalles") Integer id_egreso_detalles, Model model) {
        EgresoDetalles egresoDetalles = egresoDetalleService.findById(id_egreso_detalles);
        model.addAttribute("egresosDetalles", egresoDetalles);
        return "egresos/egresosDetalles-view";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        EgresoDetalles egresoDetalles = new EgresoDetalles();
        Egreso egreso = new Egreso();
        model.addAttribute("egresos", egreso);
        model.addAttribute("egresosDetalles", egresoDetalles);
        return "egresos/egresosDetalles-form";
    }

    /*
@GetMapping("/showFormForAdd")
public String showFormForAdd(@RequestParam(value = "selectedBodegaId", required = false) Integer selectedBodegaId, Model model) {
    EgresoDetalles egresoDetalles = new EgresoDetalles();
    Egreso egreso = new Egreso();

    model.addAttribute("egresos", egreso);
    model.addAttribute("egresosDetalles", egresoDetalles);

    // Agrega la lista de todos los artículos de la bodega seleccionada al modelo
    List<Articulo> articulosDeBodega = articleService.findByIdWithBodegaDetails(selectedBodegaId);
    model.addAttribute("allArticulos", articulosDeBodega);

    return "egresos/egresosDetalles-form";
}*/


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id_egreso_detalles") Integer id_egreso_detalles, Model model) {
        EgresoDetalles egresoDetalles = egresoDetalleService.findById(id_egreso_detalles);
        //lista egreso
        List<Egreso> allEgresos = egresoService.findAll();
        //lista Articulos
        List<Articulo> allArticulos = articleService.findAll();

        model.addAttribute("egresosDetallesU", egresoDetalles);
        model.addAttribute("allEgresos", allEgresos);
        model.addAttribute("allArticulos", allArticulos);
        return "egresos/egresosDetalles-updateForm";
    }

    @PostMapping("/save")
    public String saveEgresoDetalles(@ModelAttribute("egresoDetalels") EgresoDetalles egresoDetalles) {

        egresoDetalleService.save(egresoDetalles);

        return "redirect:/egresosDetalles/list";
    }

    @PostMapping("/update")
    public String updateEgresosDetalles(@ModelAttribute("egresoDetalles") EgresoDetalles egresoDetalles) {

        egresoDetalleService.update(egresoDetalles);

        return "redirect:/egresosDetalles/list";

    }

    @GetMapping("/delete")
    public String deleteEgresosDetalles(@RequestParam("id_egreso_detalles") Integer id_egreso_detalles) {

        egresoDetalleService.deleteByCodigo(id_egreso_detalles);

        return "redirect:/egresosDetalles/list";

    }
    @GetMapping("/exportarPDF")
    public void exportarListadoDeEmpleadosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=EGRESOS_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<EgresoDetalles> egresoDetalles = egresoDetalleService.findAll();

        EgresoReporte exporter = new EgresoReporte(egresoDetalles);
        exporter.exportar(response);
    }

}
