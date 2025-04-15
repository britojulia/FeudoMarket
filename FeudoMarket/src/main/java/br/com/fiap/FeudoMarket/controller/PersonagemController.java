package br.com.fiap.FeudoMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import br.com.fiap.FeudoMarket.model.Personagem;
import br.com.fiap.FeudoMarket.model.PersonagemType;
import br.com.fiap.FeudoMarket.repository.PersonagemRepository;
import br.com.fiap.FeudoMarket.specifications.PersonagemSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController // component
@RequestMapping("personagem")
@Slf4j
public class PersonagemController {
    @Autowired
    private PersonagemRepository repository;

        public record PersonagemFilter(
        String nome,
        PersonagemType perType)
        
    {
    }


    @GetMapping
    public Page<Personagem> index(PersonagemFilter filter,
        @PageableDefault(size = 5, sort = "nome", direction = Direction.DESC) Pageable pageable) {
        var specification = PersonagemSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }

    //crud

    //cadastrar  
    @PostMapping
    @CacheEvict(value = "personagens", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação do personagens")})
    public Personagem create(@RequestBody @Valid Personagem personagem) {
        log.info("Cadastrando personagem " + personagem.getNome());
        return repository.save(personagem);
    }

    //retornar por id
    @GetMapping("{id}")
    public Personagem get(@PathVariable Long id) {
        log.info("Buscando personagem " + id);
        return getPersonagem(id);
    }

    //deletar 
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando personagem " + id);
        repository.delete(getPersonagem(id));
    }

    //ataualizar
    @PutMapping("{id}")
    public Personagem update(@PathVariable Long id, @RequestBody @Valid Personagem personagem) {
        log.info("Atualizando personagem... " + id + " " + personagem);

        getPersonagem(id);
        personagem.setId(id);
        return repository.save(personagem);
    }


    //classe para verificar se personagem esta cadastrado
    private Personagem getPersonagem(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Perosnagem não encontrado"));
    }

}
