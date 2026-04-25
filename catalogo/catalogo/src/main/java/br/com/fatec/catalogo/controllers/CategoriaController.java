package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.listar());
        return "lista-categorias";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("categoria", new CategoriaModel());
        return "cadastro-categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("categoria") CategoriaModel categoria,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "cadastro-categoria";
        }

        service.salvar(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id) {
        service.excluir(id);
        return "redirect:/categorias";
    }

    // ✅🔥 NOVO ENDPOINT PARA AJAX (ESSENCIAL)
    @PostMapping("/api/salvar")
    @ResponseBody
    public CategoriaModel salvarApi(@RequestBody CategoriaModel categoria) {
        return service.salvar(categoria);
    }
}