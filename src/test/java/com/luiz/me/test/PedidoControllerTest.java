package com.luiz.me.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luiz.me.dto.PedidoRequestDTO;
import com.luiz.me.enums.StatusRequest;
import com.luiz.me.model.Item;
import com.luiz.me.model.Pedido;
import com.luiz.me.repository.ItemRepository;
import com.luiz.me.repository.PedidoRepositry;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerTest {
    
    private static final String PEDIDO_CONTROLLER = "/api/pedido";
    private static final String PEDIDO_CONTROLLER_STATUS = "/api/pedido/status";

    @Autowired
    private MockMvc mockMvc;
    
    private ObjectMapper mapper = new ObjectMapper();
        
    @MockBean
    private PedidoRepositry repository;
    
    @MockBean
    private ItemRepository itemRepository;
    
    

    @Test
    public void testSalvarNumeroPedidoInvalido() throws Exception {
        Pedido pedido =  gerarPedido();
        pedido.setPedido(null);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedido)))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testSalvarItemDescricaoInvalida() throws Exception {
        Pedido pedido =  gerarPedido();
        pedido.getItens().forEach(item -> {
            item.setDescricao(null);
        });
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedido)))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testSalvarPrecoUnitarioInvalido() throws Exception {
        Pedido pedido =  gerarPedido();
        pedido.getItens().forEach(item -> {
            item.setPrecoUnitario(BigDecimal.ZERO);
        });
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedido)))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testSalvarQuantidadeInvalida() throws Exception {
        Pedido pedido =  gerarPedido();
        pedido.getItens().forEach(item -> {
            item.setQtd(null);
        });
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedido)))
        .andExpect(status().isBadRequest());
    }
    
    
    @Test
    public void testMudarStatusReprovado() throws Exception {
        Pedido pedido =  gerarPedido();
        
        PedidoRequestDTO pedidoRequestDto = gerarPedidoRequestDto();
        pedidoRequestDto.setStatus(StatusRequest.REPROVADO);
        
        Pedido pedidoTotalValores = gerarPedidoTotalValores();
        
        
        Optional<Pedido> optional = Optional.ofNullable(pedido);
        
        Mockito.when(repository.findByPedido(123)).thenReturn(optional);
        Mockito.when(itemRepository.obterValorDoPedidoeQuantidade(123L)).thenReturn(pedidoTotalValores);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER_STATUS)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedidoRequestDto)))
        .andExpect(status().isCreated());
    }
    
    @Test
    public void testMudarStatusAprovado() throws Exception {
        Pedido pedido =  gerarPedido();
        
        PedidoRequestDTO pedidoRequestDto = gerarPedidoRequestDto();
        
        Pedido pedidoTotalValores = gerarPedidoTotalValores();
        
        
        Optional<Pedido> optional = Optional.ofNullable(pedido);
        
        Mockito.when(repository.findByPedido(123)).thenReturn(optional);
        Mockito.when(itemRepository.obterValorDoPedidoeQuantidade(123L)).thenReturn(pedidoTotalValores);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER_STATUS)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedidoRequestDto)))
        .andExpect(status().isCreated());
    }
    
    @Test
    public void testMudarStatusAprovadoValorMenor() throws Exception {
        Pedido pedido =  gerarPedido();
        
        PedidoRequestDTO pedidoRequestDto = gerarPedidoRequestDto();
        pedidoRequestDto.setValorAprovado(BigDecimal.valueOf(9L));
        
        Pedido pedidoTotalValores = gerarPedidoTotalValores();
        
        
        Optional<Pedido> optional = Optional.ofNullable(pedido);
        
        Mockito.when(repository.findByPedido(123)).thenReturn(optional);
        Mockito.when(itemRepository.obterValorDoPedidoeQuantidade(123L)).thenReturn(pedidoTotalValores);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER_STATUS)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedidoRequestDto)))
        .andExpect(status().isCreated());
    }
    
    @Test
    public void testMudarStatusAprovadoQuantidadeMenor() throws Exception {
        Pedido pedido =  gerarPedido();
        
        PedidoRequestDTO pedidoRequestDto = gerarPedidoRequestDto();
        pedidoRequestDto.setItensAprovados(3L);
        
        Pedido pedidoTotalValores = gerarPedidoTotalValores();
        
        
        Optional<Pedido> optional = Optional.ofNullable(pedido);
        
        Mockito.when(repository.findByPedido(123)).thenReturn(optional);
        Mockito.when(itemRepository.obterValorDoPedidoeQuantidade(123L)).thenReturn(pedidoTotalValores);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER_STATUS)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedidoRequestDto)))
        .andExpect(status().isCreated());
    }
    
    
    @Test
    public void testMudarStatusAprovadoValorMaior() throws Exception {
        Pedido pedido =  gerarPedido();
        
        PedidoRequestDTO pedidoRequestDto = gerarPedidoRequestDto();
        pedidoRequestDto.setValorAprovado(BigDecimal.valueOf(11L));
        
        Pedido pedidoTotalValores = gerarPedidoTotalValores();
        
        
        Optional<Pedido> optional = Optional.ofNullable(pedido);
        
        Mockito.when(repository.findByPedido(123)).thenReturn(optional);
        Mockito.when(itemRepository.obterValorDoPedidoeQuantidade(123L)).thenReturn(pedidoTotalValores);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER_STATUS)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedidoRequestDto)))
        .andExpect(status().isCreated());
    }
    
    @Test
    public void testMudarStatusAprovadoQuantidadeMaior() throws Exception {
        Pedido pedido =  gerarPedido();
        
        PedidoRequestDTO pedidoRequestDto = gerarPedidoRequestDto();
        pedidoRequestDto.setItensAprovados(5L);
        
        Pedido pedidoTotalValores = gerarPedidoTotalValores();
        
        
        Optional<Pedido> optional = Optional.ofNullable(pedido);
        
        Mockito.when(repository.findByPedido(123)).thenReturn(optional);
        Mockito.when(itemRepository.obterValorDoPedidoeQuantidade(123L)).thenReturn(pedidoTotalValores);
        this.mockMvc
        .perform(post(PEDIDO_CONTROLLER_STATUS)                       
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper
                                        .writeValueAsString(pedidoRequestDto)))
        .andExpect(status().isCreated());
    }
    
    
    

    private Pedido gerarPedidoTotalValores() {
        Pedido pedido = new Pedido();
        pedido.setPedido(123);
        pedido.setTotalPreco(BigDecimal.valueOf(10L));
        pedido.setTotalQtd(4L);
        return pedido;
    }

    private PedidoRequestDTO gerarPedidoRequestDto() {
        PedidoRequestDTO pedidoRequestDto = new PedidoRequestDTO();
        pedidoRequestDto.setStatus(StatusRequest.APROVADO);
        pedidoRequestDto.setPedido(123);
        pedidoRequestDto.setItensAprovados(4L);
        pedidoRequestDto.setValorAprovado(BigDecimal.valueOf(10L));
        return pedidoRequestDto;
    }

    private Pedido gerarPedido() {
        Pedido pedido = new Pedido();
        Set<Item> itemList = new HashSet<Item>();
        pedido.setPedido(123);
        pedido.setTotalPreco(BigDecimal.valueOf(10L));
        pedido.setTotalQtd(4L);
        pedido.setId(123L);
        
        Item item = new Item();
        item.setDescricao("Teste");
        item.setQtd(2L);
        item.setPrecoUnitario(BigDecimal.valueOf(5L));
       
        
        Item item2 = new Item();
        item2.setDescricao("Teste2");
        item2.setQtd(2L);
        item2.setPrecoUnitario(BigDecimal.valueOf(5L));
     
        
        itemList.add(item);
        itemList.add(item2);
        pedido.setItens(itemList);
        
        
        return pedido;
    }

}
