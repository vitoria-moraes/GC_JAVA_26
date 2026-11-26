package com.gc26.injecao.pedidos;

import com.gc26.injecao.pedidos.controller.PedidoController;
import com.gc26.injecao.pedidos.repositories.PedidoRepository;
import com.gc26.injecao.pedidos.services.EmailNotificacaoService;
import com.gc26.injecao.pedidos.services.NotificacaoService;
import com.gc26.injecao.pedidos.services.PedidoService;

public class PedidosApp {

    public static void main(String[] args) {
        PedidoRepository repository = new PedidoRepository();
        NotificacaoService notificacao = new EmailNotificacaoService();
        PedidoService pedidoService = new PedidoService(repository, notificacao);
        PedidoController pedidoController = new PedidoController(pedidoService);

        pedidoController.criarPedido(123, "Notebook", "Informática", 500);
        pedidoController.listarPedidos();
    }
}
