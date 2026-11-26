package com.gc26.fundamentos.revisaocodigo.transacao;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransacaoApp {

    public static void main(String[] args) {
        TransacaoService service = new TransacaoService(
                mensagem -> System.out.println(mensagem),
                (email, corpo) -> System.out.println("Email enviado para " + email + ": " + corpo),
                Clock.systemDefaultZone());

        Conta conta = new Conta("ACC-998", "cliente@email.com", "BR", CategoriaVip.PADRAO,
                new BigDecimal("1000.00"), true);

        List<Transacao> historico = new ArrayList<>();
        historico.add(new Transacao(new BigDecimal("50.00"), LocalDateTime.now().minusHours(5)));
        historico.add(new Transacao(new BigDecimal("30.00"), LocalDateTime.now().minusHours(3)));

        System.out.println("Saldo antes: " + conta.getSaldo());
        ResultadoTransacao resultado = service.processar(
                conta, new BigDecimal("100.00"), MeioPagamento.PIX, "BR", historico, true);

        System.out.println("Resultado: " + resultado);
        System.out.println("Saldo depois: " + conta.getSaldo());
    }
}
