package TopoAgent;
import org.hyperic.sigar.SigarException;

public class Main {
    public static void main(String[] args) throws SigarException, InterruptedException {

        TopoAgent MonitoringAgent = new TopoAgent(10000);

        Thread.currentThread().sleep(10000);

        MonitoringAgent.setFixedPeriod(1000);

        Thread.currentThread().sleep(10000);

        MonitoringAgent.setFixedPeriod(100);

        Thread.currentThread().sleep(10000);

        MonitoringAgent.reset();

    }
}
