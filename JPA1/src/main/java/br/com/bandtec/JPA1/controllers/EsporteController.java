package br.com.bandtec.JPA1.controllers;

import br.com.bandtec.JPA1.entidades.Esporte;
import br.com.bandtec.JPA1.repositorios.EsporteRepository;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/esportes")
public final class EsporteController {

    /*por quê final? tem uma galera, principalmente quem mexe com programação funcional que preza pela imutabilidade
    isso ajuda a deixar a programação mais previsível pois sabemos que a referência do objeto não vai mudar, então uma função aplicada a esse objeto
    sempre terá o mesmo retorno

    Esse artigo do uncle Bob é bem legal e incentiva a aproveitar o melhor da programação orientada a objetos e da programação funcional
    https://blog.cleancoder.com/uncle-bob/2018/04/13/FPvsOO.html
    */
    private final EsporteRepository repository;

    //Injeção de dependências com autowired não é mais recomendado pela galera do Spring, o recomendado é a injeção via construtor
    public EsporteController(final EsporteRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public ResponseEntity criarEsporte(final @RequestBody Esporte novoEsporte) {
        final Esporte esporte = this.repository.save(novoEsporte);
        // o null seria a URL do recurso recem criado
        // talvez seja interessante devolver a entidade persistida
        return ResponseEntity.created(null).body(esporte);
    }

    @GetMapping
    public ResponseEntity listaTodos() {
        final List<Esporte> esportes = this.repository.findAll();
        return !CollectionUtils.isEmpty(esportes)
            ? ResponseEntity.ok(esportes)
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getEsporte(final @PathVariable Integer id) {
        // o findAll() não retorna o obj.Ele retorna Um Optional da class Entidade
        // isso serve para previnir NullPointerException

        // justamente por ter o recurso do optional, podemos seguir aqui um estilo mais funcional de programação em java
        return this.repository.findById(id)
            .map(ResponseEntity::ok)// equivalente a: esporte -> ResponseEntity.ok(esporte)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirEsporte(final @PathVariable Integer id) {
        return this.repository.findById(id)
            .map(esporte -> {
              repository.deleteById(id);
              return ResponseEntity.ok().build();
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarEsporte(final @PathVariable Integer id, final  @RequestBody Esporte esporteAtualizado) {
        return this.repository.findById(id)
            .map(esporte -> Esporte.builder() //aqui entra um pouco da imutabilidade, não atualizei o objeto, criei outro a partir do objeto existente, o pattern Builder é bem legal pra isso
                .id(esporte.getId())
                .nome(esporteAtualizado.getNome())
                .olimpico(esporteAtualizado.isOlimpico())
                .build())
            .map(repository::save)
            .map(esporte -> ResponseEntity.ok().build())
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
