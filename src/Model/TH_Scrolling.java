package Model;

public class TH_Scrolling extends Thread {
    private final Road road;
    private final Moto moto;

    @Override
    public void run(){
        while(true) {
            try {
                sleep(moto.calculateSleep());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //if (moto.getSpeed() > 0) road.update();
            //System.out.println(moto.getSpeed());
        }
    }

    public TH_Scrolling(Road road, Moto moto){
        this.road = road;
        this.moto = moto;
    }
}
