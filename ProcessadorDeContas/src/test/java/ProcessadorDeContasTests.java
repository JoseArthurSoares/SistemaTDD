import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;


import org.junit.jupiter.api.Test;

class ProcessadorDeContasTest {

    @Test
    void testFaturaPagaComBoletosEmDia() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 500.00, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 400.00, "BOLETO");
        Conta conta3 = new Conta("3", LocalDate.of(2023, 2, 20), 600.00, "BOLETO");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2, conta3));

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testFaturaPagaComBoletosComAtraso() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 21), 500.00, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 21), 400.00, "BOLETO");
        Conta conta3 = new Conta("3", LocalDate.of(2023, 2, 21), 600.00, "BOLETO");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2, conta3));

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testFaturaPagaComCartaoCredito() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 5), 700.00, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 17), 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testFaturaPendenteComCartaoCredito() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 6), 700.00, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 17), 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoInvalidoBoletoValorBaixo() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 0.005, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoInvalidoBoletoValorAlto() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 5001.00, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoCartaoCreditoForaDoPrazo() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 10), 700.00, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 17), 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }
}
