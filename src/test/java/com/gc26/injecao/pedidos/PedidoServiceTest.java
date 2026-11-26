package com.gc26.injecao.pedidos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.gc26.injecao.pedidos.entities.Pedido;
import com.gc26.injecao.pedidos.entities.Produto;
import com.gc26.injecao.pedidos.repositories.PedidoRepository;
import com.gc26.injecao.pedidos.services.NotificacaoService;
import com.gc26.injecao.pedidos.services.PedidoService;

class PedidoServiceTest {

    @Test
    void criarPedidoSalvaENotifica() {
        List<Pedido> notificados = new ArrayList<>();
        NotificacaoService notificacao = notificados::add;
        PedidoService service = new PedidoService(new PedidoRepository(), notificacao);
        Pedido pedido = new Pedido(123, new Produto("Notebook", "Informática"), 500);

        service.criarPedido(pedido);

        assertEquals(1, service.listarPedidos().size());
        assertEquals(pedido, service.listarPedidos().get(0));
        assertEquals(1, notificados.size());
    }

    @Test
    void naoAceitaPedidoNuloNemRepetido() {
        PedidoService service = new PedidoService(new PedidoRepository(), pedido -> { });
        service.criarPedido(new Pedido(1, new Produto("Mouse", "Informática"), 50));

        assertThrows(IllegalArgumentException.class, () -> service.criarPedido(null));
        assertThrows(IllegalStateException.class,
                () -> service.criarPedido(new Pedido(1, new Produto("Teclado", "Informática"), 80)));
        assertEquals(1, service.listarPedidos().size());
    }

    @Test
    void pedidoEProdutoInvalidosSaoRecusados() {
        Produto produto = new Produto("Mouse", "Informática");

        assertThrows(IllegalArgumentException.class, () -> new Pedido(0, produto, 10));
        assertThrows(IllegalArgumentException.class, () -> new Pedido(1, null, 10));
        assertThrows(IllegalArgumentException.class, () -> new Pedido(1, produto, 0));
        assertThrows(IllegalArgumentException.class, () -> new Produto(" ", "Informática"));
    }
}
