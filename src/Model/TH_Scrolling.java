package Model;

/**
 * Thread : scroll the road
 */
public class TH_Scrolling extends Thread {
    /**
     * The road
     */
    private final Road road;

    /**
     * The player
     */
    private final Moto moto;

    /**
     * The thread itself
     */
    @Override
    public void run(){
        while(true) {
            try {
                // Updating time based on the player's speed
                sleep(moto.calculateSleep());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update the player's speed
            if (moto.getSpeed() > 0) road.update();
            //System.out.println(moto.getSpeed());
        }
    }

    /**
     * Constructor
     * @param road the road
     * @param moto the player
     */
    public TH_Scrolling(Road road, Moto moto){
        this.road = road;
        this.moto = moto;
    }
}
