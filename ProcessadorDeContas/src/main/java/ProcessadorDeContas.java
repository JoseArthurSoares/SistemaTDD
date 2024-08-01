import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProcessadorDeContas {
    public void processarFatura(Fatura fatura, List<Conta> contas) {
        double valorTotal = 0;

        for (Conta conta : contas) {
            if (conta.getTipo().equals("BOLETO")) {
                if (conta.getData().isAfter(fatura.getData())) {
                    valorTotal += conta.getValor() * 1.10;
                } else if (conta.getValor() < 0.01 || conta.getValor() > 5000.00) {
                    break;
                } else {
                    valorTotal += conta.getValor();
                }
            } else if (conta.getTipo().equals("CARTAO_CREDITO")) {
                if (ChronoUnit.DAYS.between(conta.getData(), fatura.getData()) >= 15) {
                    valorTotal += conta.getValor();
                }
            } else {
                if (conta.getData().isBefore(fatura.getData()) || conta.getData().isEqual(fatura.getData())) {
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

