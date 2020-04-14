package br.com.bandtec.JPA1.controllers;

import br.com.bandtec.JPA1.entidades.Cidade;
import br.com.bandtec.JPA1.repositorios.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public ResponseEntity trazerCidade() {
        if (this.cidadeRepository.count() > 0) {
            return ResponseEntity.ok(this.cidadeRepository.findAll()); // status 200
        } else {
            return ResponseEntity.noContent().build(); // status 204 = no content
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCidade(@PathVariable Integer id) {

        Optional<Cidade> consultarCidade = this.cidadeRepository.findById(id);
        //verifica se tem, caso não demonstra que esta vazia
        if (consultarCidade.isPresent()) {
            return ResponseEntity.ok(consultarCidade.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contagem")
    public ResponseEntity getContagemCidade() {
        //verificação se é diferente de 0 caso sim traz a contagem das cidades cadastradas
        if (this.cidadeRepository.count() != 0){
            return ResponseEntity.ok(this.cidadeRepository.count());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createCidade(@RequestBody Cidade newCidade){
        this.cidadeRepository.save(newCidade);
        return ResponseEntity.created(null).build();
    }

    //deletando cidade aparti do id dela
    @DeleteMapping("{id}")
    public ResponseEntity deleteCidade(@PathVariable Integer id){
        if (this.cidadeRepository.existsById(id)){
            this.cidadeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
