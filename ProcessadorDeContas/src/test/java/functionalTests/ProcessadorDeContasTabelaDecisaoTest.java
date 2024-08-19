package functionalTests;

import model.Fatura;
import model.ProcessadorDeContas;
import model.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class ProcessadorDeContasTabelaDecisaoTest {
    Fatura fatura1;
    Fatura fatura2;
    ProcessadorDeContas processadorDeContas;

    @BeforeEach
    public void setUp() {
        fatura1 = new Fatura(LocalDate.of(2023, 2, 20), 1000, "Cliente 1");
        fatura2 = new Fatura(LocalDate.of(2023, 2, 20), 1500, "Cliente 2");
        processadorDeContas = new ProcessadorDeContas();
    }

    @Test
    public void testBoletoEmDiaDentroDoLimites() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 1000, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testBoletoAtrasadoDentroDoLimites() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 25), 950, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PAGA");
    }

    @Test
    public void testBoletoForaDosLimites() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 0, "BOLETO");
        processadorDeContas.processarFatura(fatura1, List.of(conta1));
        assert fatura1.getStatus().equals("PENDENTE");
    }

    @Test
    public void testCartaoQuinzeDiasOuMaisAntecedencia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 5), 1500, "CARTAO_CREDITO");
        processadorDeContas.processarFatura(fatura2, List.of(conta1));
        assert fatura2.getStatus().equals("PAGA");
    }

    @Test
    public void testCartaoMenosDeQuinzeDiasAntecedencia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 10), 1500, "CARTAO_CREDITO");
        processadorDeContas.processarFatura(fatura2, List.of(conta1));
        assert fatura2.getStatus().equals("PENDENTE");
    }

    @Test
    public void testTransferenciaEmDia() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 20), 1500, "TRANSFERENCIA");
        processadorDeContas.processarFatura(fatura2, List.of(conta1));
        assert fatura2.getStatus().equals("PAGA");
    }

    @Test
    public void testTransferenciaAtrasada() {
        Conta conta1 = new Conta("1", LocalDate.of(2023, 2, 25), 1500, "TRANSFERENCIA");
        processadorDeContas.processarFatura(fatura2, List.of(conta1));
        assert fatura2.getStatus().equals("PENDENTE");
    }

}
