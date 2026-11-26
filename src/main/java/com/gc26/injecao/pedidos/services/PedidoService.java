package com.gc26.injecao.pedidos.services;

import java.util.List;

import com.gc26.injecao.pedidos.entities.Pedido;
import com.gc26.injecao.pedidos.repositories.PedidoRepository;

public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final NotificacaoService notificacaoService;

    public PedidoService(PedidoRepository pedidoRepository, NotificacaoService notificacaoService) {
        this.pedidoRepository = pedidoRepository;
        this.notificacaoService = notificacaoService;
    }

    public void criarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        boolean repetido = pedidoRepository.getPedidos().stream()
                .anyMatch(p -> p.numeroPedido() == pedido.numeroPedido());
        if (repetido) {
            throw new IllegalStateException("Já existe o pedido " + pedido.numeroPedido());
        }
        pedidoRepository.salvar(pedido);
        notificacaoService.notificar(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.getPedidos();
    }
}
