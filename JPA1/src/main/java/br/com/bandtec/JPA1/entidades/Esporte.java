package br.com.bandtec.JPA1.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// mapeamento Objeto Relacional (ORM - objeto relacional Mapping)
// entity significa que essa classe é uma tabela
@Entity
public class Esporte {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private boolean olimpico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isOlimpico() {
        return olimpico;
    }

    public void setOlimpico(boolean olimpico) {
        this.olimpico = olimpico;
    }
}
