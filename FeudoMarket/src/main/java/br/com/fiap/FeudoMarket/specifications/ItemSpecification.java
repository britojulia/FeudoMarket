package br.com.fiap.FeudoMarket.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.FeudoMarket.model.Item;
import br.com.fiap.FeudoMarket.model.RaridadeType;
import br.com.fiap.FeudoMarket.model.TipoType;

public class ItemSpecification {
     public static Specification<Item> withFilters(String nome, TipoType tipo, RaridadeType raridade, Integer precoMin, Integer precoMax) {
        return (root, query, criteriaBuilder) -> {
            if (nome != null && !nome.isEmpty()) {
                query.where(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
            }
            if (tipo != null) {
                query.where(criteriaBuilder.equal(root.get("tipoType"), tipo));
            }
            if (raridade != null) {
                query.where(criteriaBuilder.equal(root.get("rarType"), raridade));
            }
            if (precoMin != null) {
                query.where(criteriaBuilder.greaterThanOrEqualTo(root.get("preco"), precoMin));
            }
            if (precoMax != null) {
                query.where(criteriaBuilder.lessThanOrEqualTo(root.get("preco"), precoMax));
            }
            return query.getRestriction();
        };
    }
}
