package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listar() {
        return repository.findAll();
    }

    public ProdutoModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public List<ProdutoModel> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoModel salvar(ProdutoModel produto) {

        if (produto.getIdProduto() != null) {

            boolean nomeExisteEmOutro = repository
                    .existsByNomeAndIdProdutoNot(
                            produto.getNome(),
                            produto.getIdProduto()
                    );

            if (nomeExisteEmOutro) {
                throw new RuntimeException("Já existe um produto com esse nome");
            }

        }

        else {

            if (repository.existsByNome(produto.getNome())) {
                throw new RuntimeException("Já existe um produto com esse nome");
            }
        }

        return repository.save(produto);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}