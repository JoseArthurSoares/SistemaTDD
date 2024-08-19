package functionalTests;

import model.Conta;
import model.Fatura;
import model.ProcessadorDeContas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class ProcessadorDeContasParticoesEquivalenciaTest {
    Fatura fatura1;
    Fatura fatura2;
    Fatura fatura3;
    Fatura fatura4;
    ProcessadorDeContas processadorDeContas;

    @BeforeEach
    public void setUp() {
        fatura1 = new Fatura(LocalDate.of(2023, 2, 20), 1000, "Cliente 1");
        fatura2 = new Fatura(LocalDate.of(2023, 2, 20), 1500, "Cliente 2");
        fatura3 = new Fatura(LocalDate.of(2023, 2, 20), 5000, "Cliente 3");
        fatura4 = new Fatura(LocalDate.of(2023, 2, 20), 2000, "Cliente 4");
        processadorDeContas = new ProcessadorDeContas();
    }

    @Test
    public void faturaTotalmentePagaBoletosEmDia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 600, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 400, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1, conta2));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void faturaParcialmentePagaBoletosAtrasados() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 25), 1000, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 25), 200, "BOLETO");
        processadorDeContas.processarFatura(fatura2, List.of(conta1, conta2));
        assert fatura2.getStatus().equals("PENDENTE");
    }

    @Test
    public void faturaNaoPagaDevidoPagamentoInvalidos() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 0, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 5000, "BOLETO");
        processadorDeContas.processarFatura(fatura4, List.of(conta1, conta2));
        assert fatura4.getStatus().equals("PENDENTE");
    }

    @Test
    public void faturaComCartaoDeCreditoQuinzeDiasAntesETransferenciaEmDia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 5), 700, "CARTAO_CREDITO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 800, "TRANSFERENCIA");
        processadorDeContas.processarFatura(fatura2, List.of(conta1, conta2));
        assert fatura2.getStatus().equals("PAGA");
    }

    @Test
    public void faturaNaoPagaComCartaoDeCreditoComMenosQuinzeDias() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 10), 1500, "CARTAO_CREDITO");
        processadorDeContas.processarFatura(fatura2, List.of(conta1));
        assert fatura2.getStatus().equals("PENDENTE");
    }

    @Test
    public void faturaPagaTransferenciaAposDataFatura() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 21), 1000, "TRANSFERENCIA");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PENDENTE");
    }

    @Test
    public void faturaPagaComUmUnicoBoletoNoValorMaximo() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 5000, "BOLETO");
        processadorDeContas.processarFatura(fatura3, List.of(conta1));
        assert fatura3.getStatus().equals("PAGA");
    }

    @Test
    public void faturaParcialmentePagaComBoletoEmAtrasoComAcrescimoEOutroEmDia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 25), 1000, "BOLETO");
        Conta conta2 = new Conta("2", LocalDate.of(2023, 2, 20), 200, "BOLETO");
        processadorDeContas.processarFatura(fatura2, List.of(conta1, conta2));
        assert fatura2.getStatus().equals("PENDENTE");
    }
}
