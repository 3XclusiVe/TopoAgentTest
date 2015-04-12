package TopoAgent;


import jade.core.Agent;

public class NetMonitoringAgent extends Agent {

    private Route[] NetRoute;
    private NetInterface[] NetInterface;

    private Kernel_IP_Table IPTable;
    private NetInterfaces Interfaces;

    private int Period;

    private Host host;

    private String Default = "0.0.0.0";
    private String LocalHost = "127.0.0.1";
    private String DNS;
    private String PrimaryInterface;


    // Put agent clean-up operations here
    @Override
    protected void takeDown() {
        // Printout a dismissal message
        System.out.println("Net-topology monitoring agent "+getAID().getName()+" terminating.");
    }
    public void action() {
        System.out.println("Action");
    }

    @Override
    protected void setup() {

        System.out.println("Hello! Net-topology monitoring agent " +getAID().getName()+" is ready.");

        IPTable = new Kernel_IP_Table();
        Interfaces = new NetInterfaces();
        Period = 1000;
/**
        // Get the monitore period
        Object[] args = getArguments();

        if (args != null && args.length > 0) {

//            Period = (int)args[0];

        }
        else {
            // Make the agent terminate immediately
            System.out.println("No monitore period specified");
            System.out.println("Monitore period has been changed to default value: 10000");
            Period = 1000;
        }

**/


        addBehaviour(new jade.core.behaviours.TickerBehaviour (this, Period) {
            @Override
            public void onTick() {

                NetRoute = IPTable.GetTable();
                NetInterface = Interfaces.GetInterfaces();

                System.out.println("Tick");

                InformationProcessing();

                WriteToOWLDataBase();



            }


            @Override
            public void onStart() {
                System.out.println("Start to monitore");
            }


            /**
             * Построение топологии сети по полученным данным
             */
            private void InformationProcessing() {

                System.out.println("Creating Topology");

                host = new Host("MyHost");

                for(int i = 0; i < NetInterface.length; i++) {

                    host.AddInterface(NetInterface[i].Name, NetInterface[i].IPAdress, NetInterface[i].MacAdress, NetInterface[i].NetMask);
                }




                for(int j = 0; j < NetRoute.length; j++) {

                    if(NetRoute[j].Destination.equals(Default)){
                        DNS = NetRoute[j].Gateway;
                        PrimaryInterface = NetRoute[j].Interface;
                    }

                    if(NetRoute[j].Gateway.equals(Default)) {
                        host.InterfaceWithName(NetRoute[j].Interface).AddNode(NetRoute[j].Destination, NetRoute[j].GenMask);
                    }

                }

                for(int j = 0; j < NetRoute.length; j++) {

                    if(NetRoute[j].Gateway.equals(DNS)) {
                        host.InterfaceWithName(PrimaryInterface).FindWithNodeWithIP(DNS).AddNode(NetRoute[j].Destination, NetRoute[j].GenMask);
                    }

                    if(NetRoute[j].Gateway.equals(LocalHost)) {
                        host.InterfaceWithName(NetRoute[j].Interface).AddNode(NetRoute[j].Destination, NetRoute[j].GenMask);
                    }

                }

                host.print();
            }

            /**
             * Запись данных в OWL базу данных
             */
            private void WriteToOWLDataBase() {
                System.out.println("OWL Database updating");
                //System.out.println(IPTable.ToString());
            }

            /**
             * Очистка базы данных
             */
            private void RewindOWLDataBase() {
                System.out.println("OWL Database rewinding");
            }

        });
    }
}

