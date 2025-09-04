package br.com.saboresconectados.cardapio.dto;

import br.com.saboresconectados.cardapio.model.Categoria;
import br.com.saboresconectados.cardapio.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCardapioDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private Status status;
    private String imagemUrl;
    private boolean disponivel;
}
