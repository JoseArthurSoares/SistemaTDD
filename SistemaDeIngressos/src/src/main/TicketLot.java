package main;

import java.util.HashMap;
import java.util.Map;

public class TicketLot {
    private long id;
    private double price;
    private double discount;
    private int totalTickets;
    private int totalVipAvailable;
    private int totalNormalAvailable;
    private int totalMeiaAvailable;
    private Map<Long, Ticket> ticketsSold = new HashMap<>();

    // Construtor
    public TicketLot(double price, double discount, int totalTickets, int totalVip) {
        this.id = IdGenerator.getNextId();
        this.price = price;
        this.discount = discount;
        this.totalTickets = totalTickets;
        this.totalVipAvailable = totalVip;
        this.totalMeiaAvailable = (int) (totalTickets * TicketConstants.QTD_MEIA);
        this.totalNormalAvailable = (int) (totalTickets - (totalVip + totalTickets * TicketConstants.QTD_MEIA));
        ;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int getTotalVipAvailable() {
        return totalVipAvailable;
    }

    public int getTotalNormalAvailable() {
        return totalNormalAvailable;
    }

    public int getTotalMeiaAvailable() {
        return totalMeiaAvailable;
    }

    public Map<Long, Ticket> getTicketsSold() {
        return ticketsSold;
    }

    public void addTicket(Ticket ticket) {
        this.ticketsSold.put(ticket.getId(), ticket);
    }

    public Ticket criarTicket(TicketType type) {
        return new Ticket(type, calculateCost(type));
    }

    public Ticket sellTicket(TicketType ticketType, double money) {

        if (!validateMoney(calculateCost(ticketType), money) || !validarVenda(ticketType)) {
            return null;
        }

        Ticket ticket = criarTicket(ticketType);

        addTicket(ticket);

        decrementarTicket(ticketType);

        return ticket;
    }

    private boolean validarVenda(TicketType type) {
        if (type == TicketType.MEIA) {
            return this.totalMeiaAvailable > 0;
        } else if (type == TicketType.VIP) {
            return this.totalVipAvailable > 0;
        } else {
            return this.totalNormalAvailable > 0;
        }
    }

    private boolean validateMoney(double realPrice, double money) {
        return realPrice <= money;
    }

    private double calculateCost(TicketType type) {
        if (type == TicketType.MEIA) {
            return this.price / 2;
        } else if (type == TicketType.VIP) {
            return this.price * 2 - (this.price * this.discount);
        } else {
            return this.price - (this.price * this.discount);
        }
    }

    private void decrementarTicket(TicketType type) {
        if (type == TicketType.MEIA) {
            this.totalMeiaAvailable -= 1;
        } else if (type == TicketType.VIP) {
            this.totalVipAvailable -= 1;
        } else {
            this.totalNormalAvailable -= 1;
        }
    }

    public double calculateIncome(double vips) {
        double vipMoney = (((int) ((this.totalTickets * vips) - this.totalVipAvailable)) * 2 * this.price) - (this.price * this.discount);
        double meiaMoney = ( ((((int) (this.totalTickets * TicketConstants.QTD_MEIA)) - this.totalMeiaAvailable) * this.price) / 2);
        double normalMoney = ((this.totalTickets - ((int)((this.totalTickets * vips) - this.totalVipAvailable) + (this.totalTickets * TicketConstants.QTD_MEIA))) * this.price) - (this.price * this.discount) ;

        return vipMoney + meiaMoney + normalMoney;
    }
}
