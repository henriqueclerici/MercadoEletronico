package com.luiz.me.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.me.dto.PedidoRequestDTO;
import com.luiz.me.dto.PedidoResponseDTO;
import com.luiz.me.enums.StatusRequest;
import com.luiz.me.enums.StatusResponse;
import com.luiz.me.exception.PedidoException;
import com.luiz.me.model.Pedido;
import com.luiz.me.repository.PedidoRepositry;
import com.luiz.me.service.ItemService;
import com.luiz.me.service.PedidoService;
import com.luiz.me.util.Error;

import lombok.Getter;

@Service
@Getter
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepositry repository;
    private final ItemService itemService;

    @Autowired
    public PedidoServiceImpl(final PedidoRepositry repository, final ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;

    }

    @Override
    public Pedido salvar(Pedido pedido) {
        validarPedido(pedido);
        popularItemPedido(pedido);
        return getRepository().save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        if (null == pedido.getPedido()) {
            throw new PedidoException(Error.NUMERO_PEDIDO_INVALIDO);
        }

    }

    void popularItemPedido(Pedido pedido) {
        pedido.getItens().forEach(itens -> {
            getItemService().validarItens(itens);
            itens.setPedidoItem(pedido);
        });
    }

    @Override
    public PedidoResponseDTO mudarStatus(PedidoRequestDTO pedidoDto) {
        if (null == pedidoDto.getPedido()) {
            throw new PedidoException(Error.NUMERO_PEDIDO_INVALIDO);
        }
        Optional<Pedido> pedido = getRepository().findByPedido(pedidoDto.getPedido());
        if (!pedido.isPresent()) {
            PedidoResponseDTO pedidoResponse = new PedidoResponseDTO(pedidoDto.getPedido(), StatusResponse.CODIGO_PEDIDO_INVALIDO);
            return pedidoResponse;
        }
        return validarStatus(pedido.get(), pedidoDto);

    }

    private PedidoResponseDTO validarStatus(Pedido pedido, PedidoRequestDTO pedidoDto) {
        PedidoResponseDTO pedidoResponse = new PedidoResponseDTO();
        pedidoResponse.setStatus(StatusResponse.APROVADO);
        Pedido pedidoValoresTotais = getItemService().obterValorDoPedidoeQuantidade(pedido.getId());

        if (StatusRequest.REPROVADO.equals(pedidoDto.getStatus())) {
            pedidoResponse.setStatus(StatusResponse.REPROVADO);
            pedidoResponse.setPedido(pedidoDto.getPedido());
            return pedidoResponse;
        } else if (pedidoDto.getItensAprovados() == pedidoValoresTotais.getTotalQtd()
                && pedidoDto.getValorAprovado().compareTo(pedidoValoresTotais.getTotalPreco()) == 0
                && StatusRequest.APROVADO.equals(pedidoDto.getStatus())) {
            pedidoResponse.setStatus(StatusResponse.APROVADO);
            return pedidoResponse;
        } else if (StatusRequest.APROVADO.equals(pedidoDto.getStatus())
                && pedidoDto.getValorAprovado().compareTo(pedidoValoresTotais.getTotalPreco()) == -1) {
            pedidoResponse.setStatus(StatusResponse.APROVADO_VALOR_A_MENOR);
            return pedidoResponse;
        } else if (StatusRequest.APROVADO.equals(pedidoDto.getStatus())
                && pedidoDto.getItensAprovados() < pedidoValoresTotais.getTotalQtd()) {
            pedidoResponse.setStatus(StatusResponse.APROVADO_QTD_A_MENOR);
            return pedidoResponse;
        } else if (StatusRequest.APROVADO.equals(pedidoDto.getStatus())
                && pedidoDto.getValorAprovado().compareTo(pedidoValoresTotais.getTotalPreco()) == 1) {
            pedidoResponse.setStatus(StatusResponse.APROVADO_VALOR_A_MAIOR);
            return pedidoResponse;
        } else if (StatusRequest.APROVADO.equals(pedidoDto.getStatus())
                && pedidoDto.getItensAprovados() > pedidoValoresTotais.getTotalQtd()) {
            pedidoResponse.setStatus(StatusResponse.APROVADO_QTD_A_MAIOR);
            return pedidoResponse;
        }

        return pedidoResponse;

    }

}
