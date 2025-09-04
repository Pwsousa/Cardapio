package br.com.saboresconectados.cardapio.controller;

import br.com.saboresconectados.cardapio.dto.ItemCardapioDTO;
import br.com.saboresconectados.cardapio.model.Categoria;
import br.com.saboresconectados.cardapio.model.Status;
import br.com.saboresconectados.cardapio.service.ItemCardapioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemCardapioController.class)
class ItemCardapioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemCardapioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarListaDeItens() throws Exception {
        ItemCardapioDTO item1 = new ItemCardapioDTO();
        item1.setId(1L);
        item1.setNome("Hambúrguer Clássico");
        item1.setPreco(new BigDecimal("25.90"));
        item1.setCategoria(Categoria.PRATO_PRINCIPAL);
        item1.setStatus(Status.ATIVO);

        ItemCardapioDTO item2 = new ItemCardapioDTO();
        item2.setId(2L);
        item2.setNome("Batata Frita");
        item2.setPreco(new BigDecimal("12.50"));
        item2.setCategoria(Categoria.ENTRADA);
        item2.setStatus(Status.ATIVO);

        List<ItemCardapioDTO> itens = Arrays.asList(item1, item2);
        when(service.obterTodos()).thenReturn(itens);

        mockMvc.perform(get("/cardapio/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("Hambúrguer Clássico"))
                .andExpect(jsonPath("$[1].nome").value("Batata Frita"));
    }

    @Test
    void deveCriarNovoItem() throws Exception {
        ItemCardapioDTO itemDTO = new ItemCardapioDTO();
        itemDTO.setNome("Pizza Margherita");
        itemDTO.setDescricao("Pizza com molho de tomate, mussarela e manjericão");
        itemDTO.setPreco(new BigDecimal("35.90"));
        itemDTO.setCategoria(Categoria.PRATO_PRINCIPAL);

        ItemCardapioDTO itemCriado = new ItemCardapioDTO();
        itemCriado.setId(1L);
        itemCriado.setNome("Pizza Margherita");
        itemCriado.setDescricao("Pizza com molho de tomate, mussarela e manjericão");
        itemCriado.setPreco(new BigDecimal("35.90"));
        itemCriado.setCategoria(Categoria.PRATO_PRINCIPAL);
        itemCriado.setStatus(Status.ATIVO);
        itemCriado.setDisponivel(true);

        when(service.criarItem(any(ItemCardapioDTO.class))).thenReturn(itemCriado);

        mockMvc.perform(post("/cardapio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$.status").value("ATIVO"));
    }

    @Test
    void deveRetornarItemPorId() throws Exception {
        ItemCardapioDTO item = new ItemCardapioDTO();
        item.setId(1L);
        item.setNome("Hambúrguer Clássico");
        item.setPreco(new BigDecimal("25.90"));
        item.setCategoria(Categoria.PRATO_PRINCIPAL);
        item.setStatus(Status.ATIVO);

        when(service.obterPorId(1L)).thenReturn(item);

        mockMvc.perform(get("/cardapio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Hambúrguer Clássico"));
    }

    @Test
    void deveRetornarItensPorCategoria() throws Exception {
        ItemCardapioDTO item = new ItemCardapioDTO();
        item.setId(1L);
        item.setNome("Hambúrguer Clássico");
        item.setCategoria(Categoria.PRATO_PRINCIPAL);

        List<ItemCardapioDTO> itens = Arrays.asList(item);
        when(service.obterPorCategoria(Categoria.PRATO_PRINCIPAL)).thenReturn(itens);

        mockMvc.perform(get("/cardapio/categoria/PRATO_PRINCIPAL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].categoria").value("PRATO_PRINCIPAL"));
    }
}