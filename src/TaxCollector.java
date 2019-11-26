

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class TaxCollector implements Runnable {

    private final String[] values= new String[]{"Cow","Diamond","GoldCoin","Jewel","Ruby"};
    private TreasureRoom room;
    private ArrayList<Valuable> valuables;
    Random random1= new Random();
    Random random2= new Random();
    Logger log= Logger.getInstance();

    public TaxCollector(TreasureRoom treasureRoom)
    {
        this.room= treasureRoom;hh
        valuables= new ArrayList<>();
    }
    int count=0;
    public void collectTax()
    {
        log.log("Tax collector wants to collect tax");

        int total=random1.nextInt(200-50+1) + 1;
        log.log("This tax Collector must generate" + total);
        while (!(count >= total )) {
            int value = random2.nextInt(4 - 0 ) + 1;
            log.log(Integer.toString(value));
            String valueName = values[value];
            log.log(valueName);
            Valuable valuable = ValuableFactory.getValuable(valueName);
            log.log(valuable.getName());
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
