package tests;

import main.Ticket;
import main.TicketType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    @Test
    public void testCreateNormalTicket() {
        TicketType type = TicketType.NORMAL;
        double price = 10.0;

        Ticket ticket = new Ticket(type, price);

        assertEquals(type, ticket.getType());
        assertEquals(price, ticket.getPrice(), 0.01);
    }

    @Test
    public void testCreateMeiaTicket() {
        TicketType type = TicketType.MEIA;
        double price = 10.0;

        Ticket ticket = new Ticket(type, price);

        assertEquals(type, ticket.getType());
        assertEquals(price, ticket.getPrice(), 0.01);
    }

    @Test
    public void testCreateVipTicket() {
        TicketType type = TicketType.VIP;
        double price = 10.0;

        Ticket ticket = new Ticket(type, price);

        assertEquals(type, ticket.getType());
        assertEquals(price, ticket.getPrice(), 0.01);
    }
}
