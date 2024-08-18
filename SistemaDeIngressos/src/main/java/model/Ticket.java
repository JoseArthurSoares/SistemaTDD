package model;

public class Ticket {
    private long id;
    private TicketType type;
    private double price;

    public Ticket(TicketType type, double price) {
        this.id = IdGenerator.getNextId();
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public TicketType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "model.Ticket{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
