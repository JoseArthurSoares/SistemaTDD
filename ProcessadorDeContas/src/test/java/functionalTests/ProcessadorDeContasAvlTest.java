package functionalTests;

import model.Conta;
import model.Fatura;
import model.ProcessadorDeContas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class ProcessadorDeContasAvlTest {
    Fatura fatura1;
    Fatura fatura2;
    Fatura fatura3;
    ProcessadorDeContas processadorDeContas;

    @BeforeEach
    public void setUp() {
        fatura1 = new Fatura(LocalDate.of(2023, 2, 20), 1000, "Cliente 1");
        fatura2 = new Fatura(LocalDate.of(2023, 2, 20), 4000, "Cliente 2");
        fatura3 = new Fatura(LocalDate.of(2023, 2, 20), 5000, "Cliente 3");
        processadorDeContas = new ProcessadorDeContas();
    }

    @Test
    public void testPagamentoPorBoletoNoLimiteInferior() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 0.01, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 999.99, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1, conta2));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorBoletoAbaixoDoLimiteInferior() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 0.00, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 1000, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1, conta2));
        assert fatura1.getStatus().equals("PENDENTE");
    }

    @Test
    public void testPagamentoPorBoletoNoLimiteSuperior() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 5000, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorBoletoAcimaDoLimiteSuperior() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 5000.01, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PENDENTE");
    }

    @Test
    public void testValorQualquerEntreLimiteInferiorESuperior() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 2500, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorCartaoDeCreditoDezesseisDiasAntes() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 4), 1000, "CARTAO_CREDITO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorCartaoDeCreditoQuinzeDiasAntes() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 5), 1000, "CARTAO_CREDITO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorCartaoDeCreditoQuatorzeDiasAntes() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 6), 1000, "CARTAO_CREDITO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PENDENTE");
    }

    @Test
    public void testPagamentoPorTransferenciaBancariaUmDiaAntes() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 19), 1000, "TRANSFERENCIA_BANCARIA");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorTransferenciaBancariaNoDia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 1000, "TRANSFERENCIA_BANCARIA");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testPagamentoPorTransferenciaBancariaUmDiaDepois() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 21), 1000, "TRANSFERENCIA_BANCARIA");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PENDENTE");
    }
}
