package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaModel> listar() {
        return repository.findAll();
    }

    public CategoriaModel buscarPorId(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com ID: " + id));
    }

    public CategoriaModel salvar(CategoriaModel categoria) {
        return repository.save(categoria);
    }

    public void excluir(long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Categoria não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}