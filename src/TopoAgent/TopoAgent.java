package TopoAgent;

/**
 *
 */
public class TopoAgent implements TickerBehaviour{

    private Route[] NetRoute;
    private NetInterface[] NetInterface;

    private Kernel_IP_Table IPTable;
    private NetInterfaces Interfaces;

    private boolean ReadyToRead;
    private long Period;
    private int TickCount;

    private Ticker Ticker;
    private Thread TickerThread;

    public TopoAgent() {

        IPTable = new Kernel_IP_Table();
        Interfaces = new NetInterfaces();
        ReadyToRead = true;
        Period = 10000;
        TickCount = 0;
        onStart();

    }

    public TopoAgent( int period) {

        IPTable = new Kernel_IP_Table();
        Interfaces = new NetInterfaces();
        this.Period = period;
        onStart();

    }

    @Override
    public void action() {

        if(ReadyToRead) {

                ReadyToRead = false;
                onTick();
                NetRoute = IPTable.GetTable();
                NetInterface = Interfaces.GetInterfaces();
                InformationProcessing();
                WriteToOWLDataBase();
                TickCount ++;
                ReadyToRead = true;

        }

    }

    @Override
    public boolean done() {

        if (ReadyToRead) {

            return true;

        } else {

            return false;

        }
    }

    @Override
    public long getPeriod() {
        return Period;
    }

    @Override
    public int getTickCount() {
        return TickCount;
    }

    @Override
    public void onStart() {
        System.out.print("****Starting Monitoring****");
        ReadyToRead = true;
        TickCount = 0;
        Ticker = new Ticker(this);
        TickerThread = new Thread(Ticker);
        TickerThread.start();
    }

    @Override
    public void onTick() {
        System.out.print("***Tick***");
    }

    @Override
    public void reset() {
        stop();
        onStart();
    }

    @Override
    public void reset(long period) {

        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reset();
    }

    @Override
    public void setFixedPeriod(long fixedPeriod) {
            this.Period = fixedPeriod;
    }

    @Override
    public void stop() {
        Ticker.stop();
        TickerThread.interrupt();
    }

    /**
     * Построение топологии сети по полученным данным
     */
     private void InformationProcessing() {
            System.out.print(Interfaces.ToString());
    }

    private void WriteToOWLDataBase() {
            System.out.print(IPTable.ToString());
    }
}
