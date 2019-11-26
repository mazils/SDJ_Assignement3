import Logger.Logger;
import ValuableFactory.ValuableFactory;

public class Main
{
    public static void main(String[] args)
    {
        TreasureRoom treasureRoom = new TreasureRoom();
        Accountant accountant = new Accountant(treasureRoom);
        King king = new King(Logger.getInstance(),treasureRoom);
        TaxCollector taxCollector = new TaxCollector(treasureRoom);

        Thread AccountantThread  = new Thread(accountant);
        Thread TaxThread  = new Thread(taxCollector);
        Thread KingThread = new Thread(king);
        AccountantThread.start();
        TaxThread.start();
        KingThread.start();
    }
}
