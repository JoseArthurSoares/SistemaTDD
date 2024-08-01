package main;

public enum TicketType {
    VIP,
    NORMAL,
    MEIA;

    public String getType() {
        return name();
    }
}
