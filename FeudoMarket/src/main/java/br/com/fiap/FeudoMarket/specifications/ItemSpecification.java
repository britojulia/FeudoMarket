package br.com.fiap.FeudoMarket.specifications;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.FeudoMarket.controller.ItemController.ItemFilter;
import br.com.fiap.FeudoMarket.model.Item;


public class ItemSpecification {
     public static Specification<Item> withFilters(ItemFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
                    //busca nome
                    if (filter.nome() != null && !filter.nome().isBlank()) {
                        predicates.add(
                                cb.like(
                                        cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
                    }
        
                    // buscar tipo
                    if (filter.tipo() != null) {
                        predicates.add(cb.equal(root.get("tipoType"), filter.tipo()));
                    }
        
                    // buscar por preço mínimo e máximo
                    if (filter.precoMin() != null) {
                        predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), filter.precoMin()));
                    }
        
                    if (filter.precoMax() != null) {
                        predicates.add(cb.lessThanOrEqualTo(root.get("preco"), filter.precoMax()));
                    }
        
                    // buscar raridade
                    if (filter.raridade() != null) {
                        predicates.add(cb.equal(root.get("rarType"), filter.raridade()));
                    }
                    
                    return cb.and(predicates.toArray(new Predicate[0]));
                };
            }

}
