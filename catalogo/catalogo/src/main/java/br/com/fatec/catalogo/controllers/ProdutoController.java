package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.ProdutoService;
import br.com.fatec.catalogo.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(@RequestParam(required = false) String nome, Model model) {

        if (nome != null && !nome.isEmpty()) {
            model.addAttribute("produtos", service.buscarPorNome(nome));
        } else {
            model.addAttribute("produtos", service.listar());
        }

        return "lista-produtos";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new ProdutoModel());
        model.addAttribute("categorias", categoriaService.listar());
        return "cadastro-produto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") ProdutoModel produto,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "cadastro-produto";
        }

        try {
            service.salvar(produto);
        } catch (RuntimeException e) {
            result.rejectValue("nome", "erro.nome", e.getMessage());
            model.addAttribute("categorias", categoriaService.listar());
            return "cadastro-produto";
        }

        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", service.buscarPorId(id));
        model.addAttribute("categorias", categoriaService.listar());
        return "cadastro-produto";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}