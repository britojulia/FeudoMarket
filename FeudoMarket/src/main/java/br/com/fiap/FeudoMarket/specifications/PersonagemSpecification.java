package br.com.fiap.FeudoMarket.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.FeudoMarket.model.Personagem;
import br.com.fiap.FeudoMarket.model.PersonagemType;

public class PersonagemSpecification {
    public static Specification<Personagem> withFilters(String nome, PersonagemType classe) {
        return (root, query, criteriaBuilder) -> {
            if (nome != null && !nome.isEmpty()) {
                query.where(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
            }
            if (classe != null) {
                query.where(criteriaBuilder.equal(root.get("classe"), classe));
            }
            return query.getRestriction();
        };
    }
}
