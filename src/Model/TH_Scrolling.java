package Model;

public class TH_Scrolling extends Thread {
    private Road road;

    @Override
    public void run(){
        while(true){
            try {
                sleep(TH_Game.GAME_SPEED); //devra dépendre de la vitesse de joueur
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            road.update();
        }
    }

    public TH_Scrolling(Road road){
        this.road = road;
    }
}
