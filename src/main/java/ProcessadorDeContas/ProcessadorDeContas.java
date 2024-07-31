package ProcessadorDeContas;

import java.util.List;

public class ProcessadorDeContas {
    public void processarFatura(Fatura fatura, List<Conta> contas) {
        double valorTotal = contas.stream().mapToDouble(Conta::getValor).sum();
        if (valorTotal >= fatura.getValor()) {
            fatura.setStatus("PAGA");
        } else {
            fatura.setStatus("PENDENTE");
        }
    }
}
