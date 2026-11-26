package com.gc26.injecao.pedidos.controller;

import com.gc26.injecao.pedidos.entities.Pedido;
import com.gc26.injecao.pedidos.entities.Produto;
import com.gc26.injecao.pedidos.services.PedidoService;

public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void criarPedido(int numeroPedido, String nome, String categoria, int preco) {
        pedidoService.criarPedido(new Pedido(numeroPedido, new Produto(nome, categoria), preco));
    }

    public void listarPedidos() {
        System.out.println(pedidoService.listarPedidos());
    }
}
