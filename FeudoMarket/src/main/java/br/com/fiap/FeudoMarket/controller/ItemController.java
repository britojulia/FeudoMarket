package br.com.fiap.FeudoMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.fiap.FeudoMarket.model.Item;
import br.com.fiap.FeudoMarket.model.Personagem;
import br.com.fiap.FeudoMarket.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController // component
@RequestMapping("/item")
@Slf4j
public class ItemController {
    @Autowired
    private ItemRepository repository;

    //find all
    @GetMapping
    @Cacheable("item")
    @Operation(description = "Listar todos os itens", tags = "itens", summary = "Lista de itens")
    public List<Item> index() { 
        log.info("Buscando todos os itens já salvos");
        return repository.findAll();
    }

    //cadastrar  
    @PostMapping
    @CacheEvict(value = "itens", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação do item")})
    public Item create(@RequestBody @Valid Item item) {
        log.info("Cadastrando item " + item.getNome());
        return repository.save(item);
    }

    //retornar por id
    @GetMapping("{id}")
    public Item get(@PathVariable Long id) {
        log.info("Buscando item " + id);
        return getItem(id);
    }

    //deletar 
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando item " + id);
        repository.delete(getItem(id));
    }

    //ataualizar
    @PutMapping("{id}")
    public Item update(@PathVariable Long id, @RequestBody @Valid Item item) {
        log.info("Atualizando item... " + id + " " + item);

        getItem(id);
        item.setId(id);
        return repository.save(item);
    }

//classe para verificar se personagem esta cadastrado
    private Item getItem(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "item não encontrado"));
    }

}