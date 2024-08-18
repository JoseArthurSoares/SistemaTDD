package functionalTests;

import model.Ticket;
import model.TicketLot;
import model.TicketType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketLotDecisionTableTest {

    @Test
    public void testVipTicketWithDiscount() {
        TicketLot lot = new TicketLot(20.0, 0.15, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.VIP, 34.0);
        assertNotNull(ticket);
        assertEquals(34.0, ticket.getPrice(), 0.01);
        assertEquals(19, lot.getTotalVipAvailable());
    }

    @Test
    public void testVipTicketWithoutDiscount() {
        TicketLot lot = new TicketLot(20.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.VIP, 40.0);
        assertNotNull(ticket);
        assertEquals(40.0, ticket.getPrice(), 0.01);
        assertEquals(19, lot.getTotalVipAvailable());
    }

    @Test
    public void testNormalTicketWithDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.10, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.NORMAL, 9.0);
        assertNotNull(ticket);
        assertEquals(9.0, ticket.getPrice(), 0.01);
        assertEquals(69, lot.getTotalNormalAvailable());
    }

    @Test
    public void testNormalTicketWithoutDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.NORMAL, 10.0);  // preço
        assertNotNull(ticket);
        assertEquals(10.0, ticket.getPrice(), 0.01);
        assertEquals(69, lot.getTotalNormalAvailable());
    }

    @Test
    public void testMeiaEntradaTicketWithDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.MEIA_ENTRADA, 5.0);
        assertNotNull(ticket);
        assertEquals(5.0, ticket.getPrice(), 0.01);
        assertEquals(29, lot.getTotalMeiaAvailable());
    }

    @Test
    public void testMeiaEntradaTicketWithoutDiscount() {
        TicketLot lot = new TicketLot(10.0, 0.0, 100, 20);
        Ticket ticket = lot.sellTicket(TicketType.MEIA_ENTRADA, 5.0);
        assertNotNull(ticket);
        assertEquals(5.0, ticket.getPrice(), 0.01);
        assertEquals(29, lot.getTotalMeiaAvailable());
    }
}
