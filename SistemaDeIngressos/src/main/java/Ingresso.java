public class Ingresso {
    public enum Tipo { VIP, NORMAL, MEIA_ENTRADA }
    public enum Status { VENDIDO, DISPONIVEL }

    private int id;
    private Tipo tipo;
    private Status status;
    private double preco;

    public Ingresso(int id, Tipo tipo, double preco) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.status = Status.DISPONIVEL;
    }

    public int getId() {
        return id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Status getStatus() {
        return status;
    }

    public double getPreco() {
        return preco;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

