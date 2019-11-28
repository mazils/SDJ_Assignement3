

import Logger.Logger;
import ValuableFactory.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class TaxCollector implements Runnable {

    private final String[] values= new String[]{"cow","diamond","ruby"};
    private TreasureRoom room;
    private ArrayList<Valuable> valuables;
    Random random1= new Random();
    Random random2= new Random();
    Logger log= Logger.getInstance();
    private ValuableFactory factory;

    public TaxCollector(TreasureRoom treasureRoom, ValuableFactory factory)
    {
        this.factory=factory;
        this.room= treasureRoom;
        valuables= new ArrayList<>();
    }
    int count=0;
    public void collectTax()
    {
        log.log("Tax collector wants to collect tax");

        int total=random1.nextInt(200-50+1) + 1;
        log.log("This tax Collector must generate" + total);
        while (!(count >= total )) {
            int value = random2.nextInt(2 ) + 1;
            String valueName = values[value];
            Valuable valuable = factory.getValuable(valueName);
            count += valuable.getValue();
            log.log("Tax Collector added " + valuable.getName() + "to inventory");
            valuables.add(valuable);
        }
    }

    @Override
    public void run() {
        while (true) {
            collectTax();
            room.acquireWrite();
            log.log("Tax Collector is storing all the valuables into the treasury");
            Iterator<Valuable> iterator = valuables.iterator();
            while (iterator.hasNext()) {
                room.addValuable(iterator.next());
            }

            room.releaseWrite();
            log.log("Tax collector released access to treasury");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
