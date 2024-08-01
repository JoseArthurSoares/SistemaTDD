import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaDeIngressosTests {

    @Test
    public void testShowWithAllTicketsSold() {
        Show show = new Show("2024-12-31", "Artista Famoso", 1000, 2000, true);

        Lote lote = new Lote(1, 0.15);
        for (int i = 1; i <= 500; i++) {
            Ingresso.Tipo tipo = (i <= 100) ? Ingresso.Tipo.VIP : (i <= 150) ? Ingresso.Tipo.MEIA_ENTRADA : Ingresso.Tipo.NORMAL;
            double preco = (tipo == Ingresso.Tipo.VIP) ? 20 : (tipo == Ingresso.Tipo.MEIA_ENTRADA) ? 5 : 10;
            Ingresso ingresso = new Ingresso(i, tipo, preco);
            ingresso.setStatus(Ingresso.Status.VENDIDO);
            lote.adicionarIngresso(ingresso);
        }

        show.adicionarLote(lote);

        assertEquals("VIP vendidos: 100, NORMAL vendidos: 350, MEIA_ENTRADA vendidos: 50, Receita líquida: 1625.00, Status financeiro: LUCRO",
                show.gerarRelatorio());
    }

    @Test
    public void testShowWithNoTicketsSold() {
        Show show = new Show("2024-12-31", "Artista Famoso", 1000, 2000, true);

        Lote lote = new Lote(1, 0.15);
        for (int i = 1; i <= 500; i++) {
            Ingresso.Tipo tipo = (i <= 100) ? Ingresso.Tipo.VIP : (i <= 150) ? Ingresso.Tipo.MEIA_ENTRADA : Ingresso.Tipo.NORMAL;
            double preco = (tipo == Ingresso.Tipo.VIP) ? 20 : (tipo == Ingresso.Tipo.MEIA_ENTRADA) ? 5 : 10;
            Ingresso ingresso = new Ingresso(i, tipo, preco);
            lote.adicionarIngresso(ingresso);
        }

        show.adicionarLote(lote);

        assertEquals("VIP vendidos: 0, NORMAL vendidos: 0, MEIA_ENTRADA vendidos: 0, Receita líquida: -3450.00, Status financeiro: PREJUÍZO",
                show.gerarRelatorio());
    }

    @Test
    public void testShowWithMixedTicketsSold() {
        Show show = new Show("2024-12-31", "Artista Famoso", 1000, 2000, true);

        Lote lote = new Lote(1, 0.15);
        for (int i = 1; i <= 500; i++) {
            Ingresso.Tipo tipo = (i <= 100) ? Ingresso.Tipo.VIP : (i <= 150) ? Ingresso.Tipo.MEIA_ENTRADA : Ingresso.Tipo.NORMAL;
            double preco = (tipo == Ingresso.Tipo.VIP) ? 20 : (tipo == Ingresso.Tipo.MEIA_ENTRADA) ? 5 : 10;
            Ingresso ingresso = new Ingresso(i, tipo, preco);
            if (i % 2 == 0) {
                ingresso.setStatus(Ingresso.Status.VENDIDO);
            }
            lote.adicionarIngresso(ingresso);
        }

        show.adicionarLote(lote);

        assertEquals("VIP vendidos: 50, NORMAL vendidos: 175, MEIA_ENTRADA vendidos: 25, Receita líquida: -915.00, Status financeiro: PREJUÍZO",
                show.gerarRelatorio());
    }

    @Test
    public void testShowWithoutSpecialDate() {
        Show show = new Show("2024-11-30", "Artista Famoso", 1000, 2000, false);

        Lote lote = new Lote(1, 0.10);
        for (int i = 1; i <= 500; i++) {
            Ingresso.Tipo tipo = (i <= 100) ? Ingresso.Tipo.VIP : (i <= 150) ? Ingresso.Tipo.MEIA_ENTRADA : Ingresso.Tipo.NORMAL;
            double preco = (tipo == Ingresso.Tipo.VIP) ? 20 : (tipo == Ingresso.Tipo.MEIA_ENTRADA) ? 5 : 10;
            Ingresso ingresso = new Ingresso(i, tipo, preco);
            ingresso.setStatus(Ingresso.Status.VENDIDO);
            lote.adicionarIngresso(ingresso);
        }

        show.adicionarLote(lote);

        assertEquals("VIP vendidos: 100, NORMAL vendidos: 350, MEIA_ENTRADA vendidos: 50, Receita líquida: 1500.00, Status financeiro: LUCRO",
                show.gerarRelatorio());
    }
}

