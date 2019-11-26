

import Logger.Logger;
import ValuableFactory.Valuable;

import java.util.ArrayList;
import java.util.Iterator;

public class TreasureRoom {
    private int reader;
    private int waitingWriter;
    private boolean isWriting;
    Logger log= Logger.getInstance();

    private ArrayList<Valuable> valuables;

    public TreasureRoom()
    {
        reader=0;
        waitingWriter=0;
        isWriting=false;
        valuables= new ArrayList<>();
    }

    public synchronized void acquireRead()
    {
        while(isWriting || waitingWriter>0)
        {
            try {

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        reader++;
    }

    public synchronized void releaseRead()
    {
        reader--;
        if(reader==0)
        {
            notifyAll();
        }
    }

    public synchronized void acquireWrite()
    {
        waitingWriter++;
        while(reader>0 || isWriting)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriter--;
        isWriting=true;
    }

    public synchronized void releaseWrite()
    {
        isWriting=false;
        notifyAll();

    }

    public int doRead()
    {
        int count= 0;
        Iterator<Valuable> iterator= valuables.iterator();
        while (iterator.hasNext()) {
            count += iterator.next().getValue();
        }
        return count;
    }

    public void addValuable(Valuable valuable)
    {
        valuables.add(valuable);
    }

    public Valuable removeValuable()
    {
        if(valuables.size()>0) {

            return valuables.remove(0);
        }
        else
            return null;
    }





}
