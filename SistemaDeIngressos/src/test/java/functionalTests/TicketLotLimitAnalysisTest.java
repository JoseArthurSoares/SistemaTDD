package functionalTests;

import model.TicketLot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketLotLimitAnalysisTest {

    @Test
    public void testVipPercentageLowerBoundary() {
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 20);
        assertEquals(20, lot.getTotalVipAvailable());
    }

    @Test
    public void testVipPercentageUpperBoundary() {
        TicketLot lot = new TicketLot(10.0, 0.15, 100, 30);
        assertEquals(30, lot.getTotalVipAvailable());
    }

    @Test
    public void testDiscountUpperBoundary() {
        TicketLot lot = new TicketLot(10.0, 0.25, 100, 20);
        assertEquals(0.25, lot.getDiscount(), 0.01);
    }

    @Test
    public void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TicketLot(10.0, 0.26, 100, 20);
        });
    }

    @Test
    public void testTotalTicketsUpperBoundary() {
        TicketLot lot = new TicketLot(10.0, 0.15, 1000, 200);
        assertEquals(1000, lot.getTotalTickets());
    }
}

