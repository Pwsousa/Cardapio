package br.com.saboresconectados.cardapio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_cardapio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotNull
    @Enumerated(EnumType.STRING)
    private br.com.saboresconectados.cardapio.model.Status status;

    private String imagemUrl;

    private boolean disponivel;
}