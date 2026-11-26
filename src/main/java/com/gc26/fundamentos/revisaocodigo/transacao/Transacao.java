package com.gc26.fundamentos.revisaocodigo.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transacao(BigDecimal valor, LocalDateTime data) {
}
