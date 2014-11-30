package TopoAgent;

public class Ticker implements Runnable {

    private TopoAgent Agent;
    public boolean Alive;

    public Ticker(TopoAgent Agent) {

        this.Agent = Agent;


    }

    @Override
    public void run() {

        Alive = true;

        while(Alive) {
            Agent.action();
            try {
                Thread.sleep(Agent.getPeriod());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!Alive) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        Alive = false;
    }

}
