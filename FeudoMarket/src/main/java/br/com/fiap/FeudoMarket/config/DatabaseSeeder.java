package br.com.fiap.FeudoMarket.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.FeudoMarket.model.Item;
import br.com.fiap.FeudoMarket.model.Personagem;
import br.com.fiap.FeudoMarket.model.PersonagemType;
import br.com.fiap.FeudoMarket.model.RaridadeType;
import br.com.fiap.FeudoMarket.model.TipoType;
import br.com.fiap.FeudoMarket.repository.ItemRepository;
import br.com.fiap.FeudoMarket.repository.PersonagemRepository;
import jakarta.annotation.PostConstruct;

public class DatabaseSeeder {
    
    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init(){

    var personagens = List.of(
    Personagem.builder().nome("Arthas").PerType(PersonagemType.GUERREIRO).nivel(45).moedas(1200).build(),
    Personagem.builder().nome("Merlin").PerType(PersonagemType.MAGO).nivel(60).moedas(1500).build(),
    Personagem.builder().nome("Legolas").PerType(PersonagemType.ARQUEIRO).nivel(38).moedas(900).build(),
    Personagem.builder().nome("Morgana").PerType(PersonagemType.MAGO).nivel(72).moedas(2000).build(),
    Personagem.builder().nome("Thrain").PerType(PersonagemType.GUERREIRO).nivel(50).moedas(1300).build()
    );

    personagemRepository.saveAll(personagens);

    var itens = List.of(
            Item.builder().nome("Espada Mágica").tipoType(TipoType.ARMA).rarType(RaridadeType.EPICO).preco(500).dono(personagens.get(0)).build(),
            Item.builder().nome("Escudo de Mithril").tipoType(TipoType.ARMADURA).rarType(RaridadeType.LENDARIO).preco(1500).dono(personagens.get(1)).build(),
            Item.builder().nome("Poção de Vida").tipoType(TipoType.POÇÃO).rarType(RaridadeType.COMUM).preco(50).dono(personagens.get(2)).build(),
            Item.builder().nome("Arco Longo").tipoType(TipoType.ARMA).rarType(RaridadeType.RARO).preco(800).dono(personagens.get(3)).build(),
            Item.builder().nome("Anel da Sorte").tipoType(TipoType.ACESSORIO).rarType(RaridadeType.EPICO).preco(1200).dono(personagens.get(4)).build(),
            Item.builder().nome("Capa de Invisibilidade").tipoType(TipoType.ARMADURA).rarType(RaridadeType.RARO).preco(600).dono(personagens.get(1)).build(),
            Item.builder().nome("Cajado de Fogo").tipoType(TipoType.ARMA).rarType(RaridadeType.LENDARIO).preco(2000).dono(personagens.get(2)).build(),
            Item.builder().nome("Poção de Mana").tipoType(TipoType.POÇÃO).rarType(RaridadeType.COMUM).preco(30).dono(personagens.get(0)).build(),
            Item.builder().nome("Botas da Agilidade").tipoType(TipoType.ACESSORIO).rarType(RaridadeType.RARO).preco(900).dono(personagens.get(3)).build(),
            Item.builder().nome("Espada de Cristal").tipoType(TipoType.ARMA).rarType(RaridadeType.EPICO).preco(1500).dono(personagens.get(4)).build()
        );

        itemRepository.saveAll(itens);

       
    }
}
