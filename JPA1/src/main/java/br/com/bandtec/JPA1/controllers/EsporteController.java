package br.com.bandtec.JPA1.controllers;

import br.com.bandtec.JPA1.entidades.Esporte;
import br.com.bandtec.JPA1.repositorios.EsporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/esportes")
public class EsporteController {

    @Autowired // isso injeta uma instancia do EsporteRepository, no caso...
    private EsporteRepository repository;


    @PostMapping
    public ResponseEntity criarEsporte(@RequestBody Esporte novoEsporte) {
        this.repository.save(novoEsporte);

        return ResponseEntity.created(null).build();
        // o null seria a URL do recurso recem criado
    }

    @GetMapping
    public ResponseEntity listaTodos() {
        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll()); // status 200
        } else {
            return ResponseEntity.noContent().build(); // status 204 = no content
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getEsporte(@PathVariable Integer id) {
        // o findAll() não retorna o obj.Ele retorna Um Optional da class Entidade
        // isso serve para previnir NullPointerException
        Optional<Esporte> consultaEsporte = this.repository.findById(id);
        if (consultaEsporte.isPresent()) {
            // o get() do Optional traz o valor em si (no caso, um esporte)
            return ResponseEntity.ok(consultaEsporte.get());
        } else { // se o isPresente retornar false, sig que a consulta nao trouxe
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirEsporte(@PathVariable Integer id) {
        if (this.repository.existsById(id)) {// verifica se ele existe no banco e caso sim ele
            //existe se nao ele fala que nao tem
            this.repository.deleteById(id);
            return ResponseEntity.ok().build(); // 200 ok
        } else {
            return ResponseEntity.notFound().build(); // 404 no-found (nao encontrado)
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarEsporte(@PathVariable Integer id, @RequestBody Esporte esporteAtualizado) {
        Optional<Esporte> consultaExistente = this.repository.findById(id);
        if (consultaExistente.isPresent()) {
            Esporte esporteEncontrado = consultaExistente.get();
            esporteEncontrado.setNome(esporteAtualizado.getNome());
            esporteEncontrado.setOlimpico(esporteAtualizado.isOlimpico());
            this.repository.save(esporteEncontrado);
            return ResponseEntity.ok().build(); // status 200
        } else {
            return ResponseEntity.notFound().build(); // status 404
        }
    }
}
