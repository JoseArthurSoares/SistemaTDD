package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Show {
    private long id;
    private LocalDateTime date;
    private boolean specialDate;
    private String artist;
    private double cache;
    private double expenses;
    private double vipPercentage;
    private Map<Long, TicketLot> lots;

    public Show(LocalDateTime date, boolean specialDate, String artist, double cache, double expenses, double vipPercentage) {
        this.id = IdGenerator.getNextId();  // Atribui um ID autoincrementável
        this.date = date;
        this.specialDate = specialDate;
        this.artist = artist;
        this.cache = cache;
        this.expenses = expenses;
        this.vipPercentage = vipPercentage;
        this.lots = new HashMap<Long, TicketLot>();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isSpecialDate() {
        return specialDate;
    }

    public String getArtist() {
        return artist;
    }

    public double getCache() {
        return cache;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getVipPercentage() {
        return vipPercentage;
    }

    public Map<Long, TicketLot> getLots() {
        return this.lots;
    }

    public void addLots(TicketLot lot) {
        this.lots.put(lot.getId(), lot);
    }

    public Ticket sellTicket(TicketType ticketType, double money, long idLot) {
        TicketLot ticekt  = lots.get(id);

        Ticket ticket = ticekt.sellTicket(ticketType, money);

        if (ticket == null) {
            System.out.println("Falha na compra");
            return null;
        }

        return ticket;
    }

    public String reportFinancialStatus() {
        double receita = calculateIncome();
        if (receita > 0) {
            return "LUCRO";
        } else if (receita < 0) {
            return "PREJUIZO";
        } else {return "ESTAVEL";}
    }

    public double calculateIncome() {
        double receita = 0.0;

        for (TicketLot lot : this.lots.values()) {
            receita += lot.calculateIncome(vipPercentage);
        }

        return (receita - this.cache - this.expenses);
    }
}

