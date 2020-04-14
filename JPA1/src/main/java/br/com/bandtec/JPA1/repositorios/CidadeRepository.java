package br.com.bandtec.JPA1.repositorios;

import br.com.bandtec.JPA1.entidades.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
