package br.com.fiap.FeudoMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FeudoMarket.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    
}
