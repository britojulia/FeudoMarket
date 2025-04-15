package br.com.fiap.FeudoMarket.specifications;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.FeudoMarket.controller.PersonagemController.PersonagemFilter;
import br.com.fiap.FeudoMarket.model.Personagem;
import br.com.fiap.FeudoMarket.model.PersonagemType;

public class PersonagemSpecification {
    public static Specification<Personagem> withFilters(PersonagemFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(
                    cb.like(
                        cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"
                    )
                );
            }

            if (filter.perType() != null) {
                predicates.add(
                    cb.equal(root.get("perType"), filter.perType())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
