package TopoAgent;

/**
 * основное поведение агента
 */
public interface TickerBehaviour {
    void action();
    boolean done();
    long getPeriod();
    int getTickCount();
    void onStart();
    abstract void onTick();
    void reset();
    void reset(long period);
    void setFixedPeriod(long fixedPeriod);
    void stop();
}
