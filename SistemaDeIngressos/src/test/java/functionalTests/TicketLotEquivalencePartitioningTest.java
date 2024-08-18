package functionalTests;

import model.Ticket;
import model.TicketLot;
import model.TicketType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketLotEquivalencePartitioningTest {

    @Test
    public void testSellVipTicketWithoutDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.VIP, 20.0);
        assertNotNull(ticket);
        assertEquals(20.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testSellNormalTicketWithoutDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.NORMAL, 10.0);
        assertNotNull(ticket);
        assertEquals(10.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testSellMeiaEntradaTicket() {
        TicketLot lot = new TicketLot(10.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.MEIA, 5.0);
        assertNotNull(ticket);
        assertEquals(5.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testSellVipTicketWithDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.10, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.VIP, 18.0);
        assertNotNull(ticket);
        assertEquals(18.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testSellNormalTicketWithDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.NORMAL, 8.50);
        assertNotNull(ticket);
        assertEquals(8.50, ticket.getPrice(), 0.01);
    }
}

