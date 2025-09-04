package br.com.saboresconectados.cardapio.repository;

import br.com.saboresconectados.cardapio.model.Categoria;
import br.com.saboresconectados.cardapio.model.ItemCardapio;
import br.com.saboresconectados.cardapio.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    
    List<ItemCardapio> findByCategoria(Categoria categoria);
    
    List<ItemCardapio> findByStatus(Status status);
    
    List<ItemCardapio> findByDisponivelTrue();
    
    List<ItemCardapio> findByCategoriaAndStatus(Categoria categoria, Status status);
    
    @Query("SELECT i FROM ItemCardapio i WHERE i.nome LIKE %:nome%")
    List<ItemCardapio> findByNomeContaining(@Param("nome") String nome);
    
    @Query("SELECT i FROM ItemCardapio i WHERE i.preco BETWEEN :precoMin AND :precoMax")
    List<ItemCardapio> findByPrecoBetween(@Param("precoMin") java.math.BigDecimal precoMin, 
                                         @Param("precoMax") java.math.BigDecimal precoMax);
    
    Page<ItemCardapio> findByCategoriaAndStatusAndDisponivelTrue(Categoria categoria, Status status, Pageable pageable);
}
