package TopoAgent;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Класс считывает информацию о сетевых интерфейсах
 * возвращает их значение NetInterface[]
 */
public class NetInterfaces {

    private Sigar sigar;
    private String[] NetInterfaceName;
    private NetInterface[] netInterface;

    public NetInterfaces() {

        sigar = new Sigar();
        GetNetInterfacesNames();
        UpdateNetInterfacesInformation();
    }



    /**
     * Считывание существующих сетевых интерфейсов
     */
    private void GetNetInterfacesNames(){
        try {
            //считывание имен существуюх сетевых интерфейсов
            NetInterfaceName = sigar.getNetInterfaceList();
        }
        catch (SigarException e) {
            // write a description of the problem to the output
            e.printStackTrace();

            // exit the constructor
            return;
        }
    }

    /**
     * Обновление данных сетевых интерфейсов
     */
    private void UpdateNetInterfacesInformation() {

        sigar = new Sigar();
        GetNetInterfacesNames();

        //Создание списка сетевых интерфейсов
        netInterface = new NetInterface[NetInterfaceName.length];
        for( int i = 0; i < NetInterfaceName.length; i++) {
            netInterface[i] = new NetInterface();
        }

        //заполнение информации о сетевых интерфейсах
        for( int i = 0; i < NetInterfaceName.length; i++) {

            try {
                NetInterfaceConfig NetInterface = sigar.getNetInterfaceConfig(NetInterfaceName[i]);
                netInterface[i].Name = NetInterface.getName();
                netInterface[i].IPAdress = NetInterface.getAddress();
                netInterface[i].MacAdress = NetInterface.getHwaddr();
                netInterface[i].Broadcast = NetInterface.getBroadcast();
                netInterface[i].Description = NetInterface.getDescription();
                netInterface[i].Destination = NetInterface.getDestination();
                netInterface[i].Flag = NetInterface.getFlags();
                netInterface[i].Metrics = NetInterface.getMetric();
                netInterface[i].NetMask = NetInterface.getNetmask();
                netInterface[i].MTU = NetInterface.getMtu();
            }
            catch (SigarException e) {
                // write a description of the problem to the output
                e.printStackTrace();

                // exit the constructor
                return;
            }

        }

    }

    /**
     * Представлнеие данных о сетевых интрефейсах  в виде строки
     * @return Interfaces
     */
    public String ToString() {
        UpdateNetInterfacesInformation();
        String output = "";
        output +="Name \tIPAdress \tMacAdress \tBroadcast \t Description\t Destination\t Flag\t Metric\t NetMask\t MTU\t\n";
        for(int i = 0; i < netInterface.length; i++) {
            output +=
                    netInterface[i].Name + " \t" +
                    netInterface[i].IPAdress + " \t" +
                    netInterface[i].MacAdress + " \t" +
                    netInterface[i].Broadcast + " \t" +
                    netInterface[i].Description + " \t" +
                    netInterface[i].Destination + " \t" +
                    netInterface[i].Flag + " \t" +
                    netInterface[i].Metrics + " \t" +
                    netInterface[i].NetMask + " \t" +
                    netInterface[i].MTU + " \n";

        }
        return output;
    }

    /**
     * Возвращает информацию о сетевых интерфейсах
     * @return
     */
    public NetInterface[] GetInterfaces() {
        UpdateNetInterfacesInformation();
        return netInterface;
    }
}
