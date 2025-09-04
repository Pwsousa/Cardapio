package br.com.saboresconectados.cardapio.controller;

import br.com.saboresconectados.cardapio.dto.ItemCardapioDTO;
import br.com.saboresconectados.cardapio.dto.DisponibilidadeDTO;
import br.com.saboresconectados.cardapio.dto.StatusDTO;
import br.com.saboresconectados.cardapio.model.Categoria;
import br.com.saboresconectados.cardapio.model.Status;
import br.com.saboresconectados.cardapio.service.ItemCardapioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cardapio/itens")
public class ItemCardapioController {
    
    @Autowired
    private ItemCardapioService service;

    @GetMapping()
    public Page<ItemCardapioDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.obterTodos(paginacao);
    }

    @GetMapping("/todos")
    public List<ItemCardapioDTO> listarTodos() {
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> detalhar(@PathVariable @NotNull Long id) {
        ItemCardapioDTO dto = service.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta) {
        return String.format("Requisição respondida pela instância executando na porta %s", porta);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "cardapio-service");
        health.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(health);
    }

    @PostMapping()
    public ResponseEntity<ItemCardapioDTO> cadastrar(@RequestBody @Valid ItemCardapioDTO dto,
                                                    UriComponentsBuilder uriBuilder) {
        ItemCardapioDTO item = service.criarItem(dto);
        URI endereco = uriBuilder
                .path("/cardapio/itens/{id}")
                .buildAndExpand(item.getId())
                .toUri();
        return ResponseEntity.created(endereco).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> atualizar(@PathVariable @NotNull Long id,
                                                    @RequestBody @Valid ItemCardapioDTO dto) {
        ItemCardapioDTO atualizado = service.atualizarItem(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable @NotNull Long id) {
        service.excluirItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ItemCardapioDTO> obterPorCategoria(@PathVariable Categoria categoria) {
        return service.obterPorCategoria(categoria);
    }

    @GetMapping("/status/{status}")
    public List<ItemCardapioDTO> obterPorStatus(@PathVariable Status status) {
        return service.obterPorStatus(status);
    }

    @GetMapping("/disponiveis")
    public List<ItemCardapioDTO> obterDisponiveis() {
        return service.obterDisponiveis();
    }

    @GetMapping("/categoria/{categoria}/status/{status}")
    public List<ItemCardapioDTO> obterPorCategoriaEStatus(@PathVariable Categoria categoria,
                                                          @PathVariable Status status) {
        return service.obterPorCategoriaEStatus(categoria, status);
    }

    @GetMapping("/buscar")
    public List<ItemCardapioDTO> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/preco")
    public List<ItemCardapioDTO> obterPorFaixaPreco(@RequestParam BigDecimal precoMin,
                                                    @RequestParam BigDecimal precoMax) {
        return service.obterPorFaixaPreco(precoMin, precoMax);
    }

    @PutMapping("/{id}/disponibilidade")
    public ResponseEntity<ItemCardapioDTO> alterarDisponibilidade(@PathVariable @NotNull Long id,
                                                                  @RequestBody @Valid DisponibilidadeDTO dto) {
        ItemCardapioDTO item = service.alterarDisponibilidade(id, dto.isDisponivel());
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ItemCardapioDTO> alterarStatus(@PathVariable @NotNull Long id,
                                                        @RequestBody @Valid StatusDTO dto) {
        ItemCardapioDTO item = service.alterarStatus(id, dto.getStatus());
        return ResponseEntity.ok(item);
    }
}
