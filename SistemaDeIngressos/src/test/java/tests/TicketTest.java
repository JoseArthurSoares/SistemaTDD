package tests;

import model.Ticket;
import model.TicketType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    @Test
    public void testCreateNormalTicket() {
        TicketType type = TicketType.NORMAL;
        double price = 10.0;

        Ticket ticket = new Ticket(type, price);

        Assert.assertEquals(type, ticket.getType());
        Assert.assertEquals(price, ticket.getPrice(), 0.01);
    }

    @Test
    public void testCreateMeiaTicket() {
        TicketType type = TicketType.MEIA;
        double price = 10.0;

        Ticket ticket = new Ticket(type, price);

        Assert.assertEquals(type, ticket.getType());
        Assert.assertEquals(price, ticket.getPrice(), 0.01);
    }

    @Test
    public void testCreateVipTicket() {
        TicketType type = TicketType.VIP;
        double price = 10.0;

        Ticket ticket = new Ticket(type, price);

        Assert.assertEquals(type, ticket.getType());
        Assert.assertEquals(price, ticket.getPrice(), 0.01);
    }
}
