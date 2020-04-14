package br.com.bandtec.JPA1.repositorios;

import br.com.bandtec.JPA1.entidades.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

// repository é um padrão de projeto, nele cada repository é responsavel por uma tabela
public interface EsporteRepository extends JpaRepository<Esporte, Integer> {
}
