import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import ProcessadorDeContas.Conta;
import ProcessadorDeContas.Fatura;
import ProcessadorDeContas.ProcessadorDeContas;
import org.junit.jupiter.api.Test;

class ProcessadorDeContasTest {

    @Test
    void testFaturaPagaComBoletosEmDia() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "20/02/2023", 500.00, "BOLETO");
        Conta conta2 = new Conta("2", "20/02/2023", 400.00, "BOLETO");
        Conta conta3 = new Conta("3", "20/02/2023", 600.00, "BOLETO");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2, conta3));

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testFaturaPagaComBoletosComAtraso() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "21/02/2023", 500.00, "BOLETO");
        Conta conta2 = new Conta("2", "21/02/2023", 400.00, "BOLETO");
        Conta conta3 = new Conta("3", "21/02/2023", 600.00, "BOLETO");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2, conta3));

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testFaturaPagaComCartaoCredito() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "05/02/2023", 700.00, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", "17/02/2023", 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    void testFaturaPendenteComCartaoCredito() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "06/02/2023", 700.00, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", "17/02/2023", 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoInvalidoBoletoValorBaixo() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "20/02/2023", 0.005, "BOLETO");
        Conta conta2 = new Conta("2", "20/02/2023", 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoInvalidoBoletoValorAlto() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "20/02/2023", 5001.00, "BOLETO");
        Conta conta2 = new Conta("2", "20/02/2023", 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    void testPagamentoCartaoCreditoForaDoPrazo() {
        Fatura fatura = new Fatura("20/02/2023", 1500.00, "Cliente 1");
        Conta conta1 = new Conta("1", "10/02/2023", 700.00, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", "17/02/2023", 800.00, "TRANSFERENCIA_BANCARIA");

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.processarFatura(fatura, Arrays.asList(conta1, conta2));

        assertEquals("PENDENTE", fatura.getStatus());
    }
}