import java.util.ArrayList;
import java.util.Random;

public class King implements Runnable{
    private Logger logger;
    private TreasureRoom treasureRoom;

    public King(Logger logger, TreasureRoom treasureRoom){
        this.logger=logger;
        this.treasureRoom=treasureRoom;
    }

    Random random = new Random();
    @Override
    public void run() {
        while(true){
            ArrayList<Valuable> kingsPocket = new ArrayList<Valuable>();

            int costOfParty = random.nextInt(150-50)+50;
            System.out.println("Listen everyone. I'm making party");

            int numOfThingsFromRoom = random.nextInt(15-5)+5;

            int pocketInMoney = 0;

            for (int i = 0; i < numOfThingsFromRoom; i++) {
                treasureRoom.acquireWrite();
                kingsPocket.add(treasureRoom.removeValuable());
                treasureRoom.releaseWrite();
            }

            for (int i = 0; i <kingsPocket.size() ; i++) {
                pocketInMoney =+ kingsPocket.get(i).getValue();
            }

            if (pocketInMoney<costOfParty){
                System.out.println("Party has been canceled");
                treasureRoom.acquireWrite();
                for (int i = 0; i < kingsPocket.size() ; i++) {
                    treasureRoom.addValuable(kingsPocket.get(i));
                }
                treasureRoom.releaseWrite();
            } else{
                System.out.println("Party on");
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
