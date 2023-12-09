package com.tf4beta.ems.main.controllers;

import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.service.ArticleService;
import com.tf4beta.ems.main.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articulos")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private BodegaService bodegaService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ModelAttribute("allBodegas")
    public List<Bodega> populateBodegas() {
        return bodegaService.findAll();
    }

    //@GetMapping("index")
    //public String articuloIndex() {
    //    return "articulos/articulos-index.html";
    //}

    @GetMapping("/nolist")
    public String listArticulos(Model theModel) {
        List<Articulo> articulos = articleService.findAll();
        theModel.addAttribute("articulo", articulos);
        return "articulos/list-articulos";
    }

    @GetMapping("/list")
    public String listArticulosBodega(Model theModel) {
        List<Articulo> articulos = articleService.findAll();
        theModel.addAttribute("articulo", articulos);
        return "articulos/list-articulos";
    }

    @RequestMapping("/showArticuloDetails")
    public String viewArticulos(@RequestParam("codigoA") String codigoA, Model model) {
        Articulo articulo = articleService.findByIdWithBodegaDetails(codigoA);
        model.addAttribute("articulo", articulo);
        return "articulos/articulos-view";
    }
/*
    @GetMapping("/searchName")
    public String searchName(@RequestParam("searchName") String searchName, Model model) {
        List<Articulo> articulos = articleService.searchByName(searchName);
        if (articulos.isEmpty()) {
            model.addAttribute("searchWarning", "Sorry! We not found that name.");
        }
        model.addAttribute("articulo", articulos);
        return "articulos/list-articulos";
    }*/

    @GetMapping("/searchName")
    public String searchName(@ModelAttribute("articulo") Articulo articulo, Model model) {
        List<Articulo> articulos = articleService.search(articulo);
        if (articulos.isEmpty()) {
            model.addAttribute("searchWarning", "Sorry! We did not find any matching articles.");
        }
        model.addAttribute("articulo", articulos);
        return "articulos/list-articulos";
    }



    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Articulo articulo = new Articulo();
        theModel.addAttribute("articulo", articulo);
        return "articulos/articulos-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("codigoA") String codigoa, Model theModel) {

        Articulo articulo = articleService.findByIdWithBodegaDetails(codigoa);
        // Obtener la lista de todas las bodegas
        List<Bodega> allBodegas = bodegaService.findAll();

        theModel.addAttribute("articulo", articulo);
        theModel.addAttribute("allBodegas", allBodegas);


        return "articulos/articulos-updateForm";
    }

    @PostMapping("/save")
    public String saveArticulo(@ModelAttribute("articulo") Articulo articulo) {

        articleService.save(articulo);

        return "redirect:/articulos/list";
    }

    @PostMapping("/update")
    public String updateArticulo(@ModelAttribute("articulo") Articulo articulo) {

        articleService.update(articulo);

        return "redirect:/articulos/list";
    }

    @GetMapping("/delete")
    public String deleteArticulo(@RequestParam("codigoA") String codigoa) {

        articleService.delateByCodigo(codigoa);

        return "redirect:/articulos/list";
    }

}