package tests;

import main.Show;
import main.Ticket;
import main.TicketLot;
import main.TicketType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class ShowTest {

    @Test
    public void testCreateShow() {
        LocalDateTime date = LocalDateTime.now();
        String artist = "Artist";
        Double cache = 1000.0;
        Double expenses = 2000.0;
        Double vipPercentage = 0.2;

        Show show = new Show(date, false, artist, cache, expenses, vipPercentage);

        assertEquals(date, show.getDate());
        assertFalse(show.isSpecialDate());
        assertEquals(artist, show.getArtist());
        assertEquals(cache, show.getCache());
        assertEquals(expenses, show.getExpenses());
        assertEquals(vipPercentage, show.getVipPercentage());
    }

    @Test
    public void testAddLot() {
        LocalDateTime date = LocalDateTime.now();
        String artist = "Artist";
        double cache = 1000.0;
        double expenses = 2000.0;
        double vipPercentage = 0.2;

        Show show = new Show(date, false, artist, cache, expenses, vipPercentage);
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 50);

        assertEquals(0, show.getLots().size());

        show.addLots(lot);

        assertEquals(1, show.getLots().size());
        assertTrue(show.getLots().containsValue(lot));
    }

    @Test
    public void testCalculateIncome() {
        Show show = new Show(LocalDateTime.now(), false, "Artist", 1000.0, 2000.0, 0.2);
        TicketLot lot1 = new TicketLot(10.0, 0.15, 100, 20);
        TicketLot lot2 = new TicketLot(12.0, 0.10, 100, 20);

        show.addLots(lot1);
        show.addLots(lot2);

        lot1.sellTicket(TicketType.NORMAL, 10.0);
        lot1.sellTicket(TicketType.VIP, 10.0);
        lot1.sellTicket(TicketType.MEIA, 10.0);
        lot2.sellTicket(TicketType.NORMAL, 12.0);
        lot2.sellTicket(TicketType.VIP, 12.0);
        lot2.sellTicket(TicketType.MEIA, 12.0);

        double income = show.calculateIncome();
        assertEquals(-816.4, income,0.01);
    }

    @Test
    public void testReportFinancialStatus() {
        Show show = new Show(LocalDateTime.now(), false, "Artist", 1000.0, 2000.0, 0.2);
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 100);
        show.addLots(lot);

        lot.sellTicket(TicketType.NORMAL, 10.0);

        String status = show.reportFinancialStatus();
        assertEquals("PREJUIZO", status);
    }

}
