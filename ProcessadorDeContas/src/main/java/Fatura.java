import java.time.LocalDate;

public class Fatura {
    private String status;
    private double valor;
    private String cliente;
    private LocalDate data;

    public Fatura(LocalDate data, double valor, String cliente) {
        this.data = data;
        this.valor = valor;
        this.cliente = cliente;
        this.status = "PENDENTE";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
