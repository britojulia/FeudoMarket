package br.com.fiap.FeudoMarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoType tipoType;

    @Enumerated(EnumType.STRING)
    private RaridadeType rarType;

    @Min(value = 0, message = "O valor não pode ser negativo")
    private Integer preco;

    @ManyToOne
    private Personagem dono;

    
}
