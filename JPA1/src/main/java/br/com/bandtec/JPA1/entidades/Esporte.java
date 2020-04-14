package br.com.bandtec.JPA1.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// mapeamento Objeto Relacional (ORM - objeto relacional Mapping)
// entity significa que essa classe é uma tabela
@Entity
@NoArgsConstructor // construtor default
@AllArgsConstructor // construtor com todos os objetos
@Builder // annotation do lombock para criar um builder para a entidade
@Getter // annotation do lombock para criar os getters
//@Setter // como prezei pela imutabilidade dos objetos não criei os setters, asssim não é possível modificar os valores do objeto
public class Esporte {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private boolean olimpico;

}
