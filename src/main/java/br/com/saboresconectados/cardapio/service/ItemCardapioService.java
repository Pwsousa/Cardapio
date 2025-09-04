package br.com.saboresconectados.cardapio.service;

import br.com.saboresconectados.cardapio.dto.ItemCardapioDTO;
import br.com.saboresconectados.cardapio.model.Categoria;
import br.com.saboresconectados.cardapio.model.ItemCardapio;
import br.com.saboresconectados.cardapio.model.Status;
import br.com.saboresconectados.cardapio.repository.ItemCardapioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCardapioService {
    
    @Autowired
    private ItemCardapioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ItemCardapioDTO> obterTodos(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class));
    }

    public List<ItemCardapioDTO> obterTodos() {
        return repository.findAll().stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public ItemCardapioDTO obterPorId(Long id) {
        ItemCardapio item = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com ID: " + id));
        return modelMapper.map(item, ItemCardapioDTO.class);
    }

    public ItemCardapioDTO criarItem(ItemCardapioDTO dto) {
        ItemCardapio item = modelMapper.map(dto, ItemCardapio.class);
        if (item.getStatus() == null) {
            item.setStatus(Status.ATIVO);
        }
        if (!item.isDisponivel()) {
            item.setDisponivel(true);
        }
        repository.save(item);
        return modelMapper.map(item, ItemCardapioDTO.class);
    }

    public ItemCardapioDTO atualizarItem(Long id, ItemCardapioDTO dto) {
        ItemCardapio itemExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com ID: " + id));
        
        // Atualizar apenas os campos fornecidos
        if (dto.getNome() != null) {
            itemExistente.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            itemExistente.setDescricao(dto.getDescricao());
        }
        if (dto.getPreco() != null) {
            itemExistente.setPreco(dto.getPreco());
        }
        if (dto.getCategoria() != null) {
            itemExistente.setCategoria(dto.getCategoria());
        }
        if (dto.getStatus() != null) {
            itemExistente.setStatus(dto.getStatus());
        }
        if (dto.getImagemUrl() != null) {
            itemExistente.setImagemUrl(dto.getImagemUrl());
        }
        itemExistente.setDisponivel(dto.isDisponivel());
        
        repository.save(itemExistente);
        
        return modelMapper.map(itemExistente, ItemCardapioDTO.class);
    }

    public void excluirItem(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Item não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<ItemCardapioDTO> obterPorCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemCardapioDTO> obterPorStatus(Status status) {
        return repository.findByStatus(status).stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemCardapioDTO> obterDisponiveis() {
        return repository.findByDisponivelTrue().stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemCardapioDTO> obterPorCategoriaEStatus(Categoria categoria, Status status) {
        return repository.findByCategoriaAndStatus(categoria, status).stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemCardapioDTO> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome).stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemCardapioDTO> obterPorFaixaPreco(BigDecimal precoMin, BigDecimal precoMax) {
        return repository.findByPrecoBetween(precoMin, precoMax).stream()
                .map(item -> modelMapper.map(item, ItemCardapioDTO.class))
                .collect(Collectors.toList());
    }

    public ItemCardapioDTO alterarDisponibilidade(Long id, boolean disponivel) {
        ItemCardapio item = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com ID: " + id));
        
        item.setDisponivel(disponivel);
        repository.save(item);
        
        return modelMapper.map(item, ItemCardapioDTO.class);
    }

    public ItemCardapioDTO alterarStatus(Long id, Status status) {
        ItemCardapio item = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com ID: " + id));
        
        item.setStatus(status);
        repository.save(item);
        
        return modelMapper.map(item, ItemCardapioDTO.class);
    }
}
