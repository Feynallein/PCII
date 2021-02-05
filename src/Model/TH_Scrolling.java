package Model;

public class TH_Scrolling extends Thread {
    private Road road;
    private Moto moto;

    @Override
    public void run(){
        while(true){
            System.out.println(moto.getSpeed());
            try {
                sleep((long) ((700/(1 + (moto.getSpeed()* 2L))) + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(moto.getSpeed() > 0) road.update();
        }
    }

    public TH_Scrolling(Road road, Moto moto){
        this.road = road;
        this.moto = moto;
    }
}
