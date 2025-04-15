package br.com.fiap.FeudoMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FeudoMarket.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
