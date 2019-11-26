import Logger.Logger;

public class Accountant implements Runnable {
    private TreasureRoom treasureRoom;
    private Logger logger;

    public Accountant(TreasureRoom treasureRoom){
        this.treasureRoom=treasureRoom;
        logger = Logger.getInstance();
    }

    @Override
    public void run() {
        while(true){
            int sum=0;
            treasureRoom.acquireRead();

            sum = treasureRoom.doRead();
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            treasureRoom.releaseRead();

            logger.log("The balance of treasure room is: "+sum);
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
