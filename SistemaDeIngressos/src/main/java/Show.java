import java.util.ArrayList;
import java.util.List;

public class Show {
    private String data;
    private String artista;
    private double cache;
    private double despesasInfraestrutura;
    private boolean dataEspecial;
    private List<Lote> lotes;

    public Show(String data, String artista, double cache, double despesasInfraestrutura, boolean dataEspecial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.dataEspecial = dataEspecial;
        this.lotes = new ArrayList<>();
    }

    public void adicionarLote(Lote lote) {
        lotes.add(lote);
    }

    public double calcularReceitaLiquida() {
        double receita = 0;

        for (Lote lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getStatus() == Ingresso.Status.VENDIDO) {
                    receita += ingresso.getPreco() * (1 - lote.getDesconto());
                }
            }
        }

        double despesas = cache + despesasInfraestrutura;
        if (dataEspecial) {
            despesas *= 1.15;
        }

        return receita - despesas;
    }

    public String gerarRelatorio() {
        int vipVendidos = 0, normalVendidos = 0, meiaVendidos = 0;
        double receita = 0;

        for (Lote lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getStatus() == Ingresso.Status.VENDIDO) {
                    receita += ingresso.getPreco() * (1 - lote.getDesconto());
                    if (ingresso.getTipo() == Ingresso.Tipo.VIP) {
                        vipVendidos++;
                    } else if (ingresso.getTipo() == Ingresso.Tipo.NORMAL) {
                        normalVendidos++;
                    } else if (ingresso.getTipo() == Ingresso.Tipo.MEIA_ENTRADA) {
                        meiaVendidos++;
                    }
                }
            }
        }

        double despesas = cache + despesasInfraestrutura;
        if (dataEspecial) {
            despesas *= 1.15;
        }

        double receitaLiquida = receita - despesas;
        String statusFinanceiro = receitaLiquida > 0 ? "LUCRO" : receitaLiquida < 0 ? "PREJUÍZO" : "ESTÁVEL";

        return String.format("VIP vendidos: %d, NORMAL vendidos: %d, MEIA_ENTRADA vendidos: %d, Receita líquida: %.2f, Status financeiro: %s",
                vipVendidos, normalVendidos, meiaVendidos, receitaLiquida, statusFinanceiro);
    }
}
