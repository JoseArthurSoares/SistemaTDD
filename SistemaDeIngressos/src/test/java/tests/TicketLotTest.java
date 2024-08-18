package tests;

import model.Ticket;
import model.TicketLot;
import model.TicketType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketLotTest {

    @Test
    public void testCreateTicketLot() {
        Long id = 3L;
        Double price = 10.0;
        Double desconto = 0.15;
        int totalTickets = 100;
        int totalVIPAvailable = 50;

        TicketLot lot = new TicketLot(price, desconto, totalTickets, totalVIPAvailable);

        Assertions.assertEquals(id, lot.getId());
        Assertions.assertEquals(price, lot.getPrice());
        Assertions.assertEquals(desconto, lot.getDiscount());
        Assertions.assertEquals(totalVIPAvailable, lot.getTotalVipAvailable());
        Assertions.assertEquals((int) (totalTickets - (totalTickets * 0.01 + totalVIPAvailable)), lot.getTotalNormalAvailable());
        Assertions.assertEquals((int) (totalTickets * 0.01), lot.getTotalMeiaAvailable());
        Assertions.assertTrue(lot.getTicketsSold().isEmpty());
    }

    @Test
    public void testCreateNormalTicket() {
        Long id = 1L;
        double price = 10.0;
        double desconto = 0.15;
        int totalTickets = 100;
        int totalVIPAvailable = 50;

        TicketLot lot = new TicketLot(price, desconto, totalTickets, totalVIPAvailable);
        Ticket ticket = lot.criarTicket(TicketType.NORMAL);

        assertNotNull(ticket);
        Assertions.assertEquals(TicketType.NORMAL, ticket.getType());
        Assertions.assertEquals(price - (price*desconto), ticket.getPrice(), 0.01);
    }

    @Test
    public void testCreateVipTicket() {
        Long id = 1L;
        double price = 10.0;
        double desconto = 0.15;
        int totalTickets = 100;
        int totalVIPAvailable = 50;

        TicketLot lot = new TicketLot(price, desconto, totalTickets, totalVIPAvailable);
        Ticket ticket = lot.criarTicket(TicketType.VIP);

        assertNotNull(ticket);
        Assertions.assertEquals(TicketType.VIP, ticket.getType());
        Assertions.assertEquals(2*price - (price*desconto), ticket.getPrice(), 0.01);
    }

    @Test
    public void testCreateMeiaTicket() {
        Long id = 1L;
        double price = 10.0;
        double desconto = 0.15;
        int totalTickets = 100;
        int totalVIPAvailable = 50;

        TicketLot lot = new TicketLot(price, desconto, totalTickets, totalVIPAvailable);
        Ticket ticket = lot.criarTicket(TicketType.MEIA);

        assertNotNull(ticket);
        Assertions.assertEquals(TicketType.MEIA, ticket.getType());
        Assertions.assertEquals(price/2, ticket.getPrice(), 0.01);
    }

    @Test
    public void testAddTicket() {
        Long id = 1L;
        double price = 10.0;
        double desconto = 0.15;
        int totalTickets = 100;
        int totalVIPAvailable = 50;

        TicketLot lot = new TicketLot(price, desconto, totalTickets, totalVIPAvailable);
        Ticket ticket = lot.criarTicket(TicketType.NORMAL);

        lot.addTicket(ticket);

        Assertions.assertTrue(lot.getTicketsSold().containsKey(ticket.getId()));
        Assertions.assertEquals(ticket.getId(), lot.getTicketsSold().get(ticket.getId()).getId());
    }

    @Test
    public void testVenderTicket() {
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 20);

        Ticket ticket = lot.sellTicket(TicketType.NORMAL, 10.0);

        assertNotNull(ticket);
        Assertions.assertTrue(lot.getTicketsSold().containsKey(ticket.getId()));
    }

    @Test
    public void testFalhaVenderTicket() {
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 20);

        Ticket ticket = lot.sellTicket(TicketType.NORMAL, 9.0);
    }
}
