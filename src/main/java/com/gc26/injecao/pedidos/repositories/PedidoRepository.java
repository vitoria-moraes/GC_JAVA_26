package com.gc26.injecao.pedidos.repositories;

import java.util.ArrayList;
import java.util.List;

import com.gc26.injecao.pedidos.entities.Pedido;

public class PedidoRepository {

    private final List<Pedido> pedidos = new ArrayList<>();

    public void salvar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return List.copyOf(pedidos);
    }
}
