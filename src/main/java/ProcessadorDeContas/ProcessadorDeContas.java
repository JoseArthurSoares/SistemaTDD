package ProcessadorDeContas;

import java.util.List;

public class ProcessadorDeContas {
    public void processarFatura(Fatura fatura, List<Conta> contas) {
        double valorTotal = 0;
        for (Conta conta : contas) {
            if (conta.getTipo().equals("BOLETO")) {
                if (conta.getValor() < 0.01 || conta.getValor() > 5000.00) {
                    fatura.setStatus("PENDENTE");
                    return;
                } else {
                    valorTotal += conta.getValor();
                }
            }
        }
        if (valorTotal >= fatura.getValor()) {
            fatura.setStatus("PAGA");
        } else {
            fatura.setStatus("PENDENTE");
        }
    }
}
