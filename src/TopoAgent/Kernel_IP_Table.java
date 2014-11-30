package TopoAgent;

import org.hyperic.sigar.NetRoute;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Kernel IP table
 * Класс считывает таблицу маршрутизации
 * Возвращает ее значение type Route[]
 */
public class Kernel_IP_Table {

    private NetRoute[] NetRoutes;
    private Sigar sigar;
    private Route[] Routes;


    public Kernel_IP_Table() {

        sigar = new Sigar();
        GetRoutes();
        UpdateRoutes();
    }



    /**
     * Считывание таблицы маршрутизации
     */
    private void GetRoutes(){
        try {
            //считывание таблицы маршрутизации
            NetRoutes = sigar.getNetRouteList();
        }
        catch (SigarException e) {
            // write a description of the problem to the output
            e.printStackTrace();

            // exit the constructor
            return;
        }
    }

    /**
     * Обновление данных таблицы маршрутизации
     */
    private void UpdateRoutes() {

        sigar = new Sigar();
        GetRoutes();

        if(sigar != null) {

            //создание таблицы маршрутизации
            Routes = new Route[NetRoutes.length];
            for(int i = 0; i < NetRoutes.length; i++) {
                Routes[i] = new Route();
            }

            //заполнение таблицы
            for(int i = 0; i < NetRoutes.length; i++) {
                Routes[i].Destination= NetRoutes[i].getDestination();
                Routes[i].Gateway = NetRoutes[i].getGateway();
                Routes[i].GenMask = NetRoutes[i].getMask();
                Routes[i].Flag = NetRoutes[i].getFlags();
                Routes[i].Metric = NetRoutes[i].getMetric();
                Routes[i].Reference = NetRoutes[i].getRefcnt();
                Routes[i].Interface = NetRoutes[i].getIfname();
            }
        }
    }

    /**
     * Представление таблицы в виде строки
     * @return
     */
    public String ToString() {
        UpdateRoutes();
        String output = "";
        output +="Destination \tGateway \tGenmask \tFlags \t Metric\t Ref\t Interface\t\n";
        for(int i = 0; i < NetRoutes.length; i++) {
            output +=
            Routes[i].Destination + " \t" +
            Routes[i].Gateway + " \t" +
            Routes[i].GenMask + " \t" +
            Routes[i].Flag + " \t" +
            Routes[i].Metric + " \t" +
            Routes[i].Reference + " \t" +
            Routes[i].Interface + " \n";

        }
        return output;
    }

    /**
     * Получение таблицы маршрутизации
     * @return Routes
     */
    public Route[] GetTable() {
        UpdateRoutes();
        return Routes;
    }

}
