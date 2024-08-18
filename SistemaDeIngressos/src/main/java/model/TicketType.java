package model;

public enum TicketType {
    VIP,
    NORMAL,
    MEIA, MEIA_ENTRADA;

    public String getType() {
        return name();
    }
}
