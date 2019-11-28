import Logger.Logger;
import ValuableFactory.ValuableFactory;

public class Main
{
    public static void main(String[] args)

    {
        TreasureRoom treasureRoom = new TreasureRoom();
        ValuableFactory valuableFactory = new ValuableFactory();
        for (int i = 0; i <5 ; i++) {
            Accountant accountant = new Accountant(treasureRoom);
            Thread AccountantThread  = new Thread(accountant);
            AccountantThread.start();
        }
        King king = new King(Logger.getInstance(),treasureRoom);

        for (int i = 0; i <3 ; i++) {
            TaxCollector taxCollector = new TaxCollector(treasureRoom, valuableFactory);
            Thread TaxThread  = new Thread(taxCollector);
            TaxThread.start();
        }


        Thread KingThread = new Thread(king);

        KingThread.start();
    }
}
