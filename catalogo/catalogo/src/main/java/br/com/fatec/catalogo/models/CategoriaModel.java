package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "TB_CATEGORIA")
public class CategoriaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    public CategoriaModel() {}

    public CategoriaModel(String nome) {
        this.nome = nome;
    }

    // GETTERS E SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}