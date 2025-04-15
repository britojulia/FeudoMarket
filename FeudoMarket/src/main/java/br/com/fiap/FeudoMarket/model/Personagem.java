package br.com.fiap.FeudoMarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Personagem {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private PersonagemType PerType;

    @Min(value = 1, message = "valor mínimo deve ser 1")
    @Max(value = 99, message = "valor máximo deve ser 99")
    private Integer nivel;

    @Min(value = 0, message = "O saldo de moedas não pode ser negativo")
    private Integer moedas;
    
}
