import java.util.ArrayList;
import java.util.List;

public class Lote {
    private int id;
    private List<Ingresso> ingressos;
    private double desconto;

    public Lote(int id, double desconto) {
        this.id = id;
        this.desconto = desconto;
        this.ingressos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public double getDesconto() {
        return desconto;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }
}

