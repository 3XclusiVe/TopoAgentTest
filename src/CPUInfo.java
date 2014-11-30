

    //import the classes that we will use

    import TopoAgent.NetInterfaces;
    import org.hyperic.sigar.*;

    //declare the CPUInfo class
    public class CPUInfo {



        /**
         * declare a constructor for CPUINFO
         *
         * Remember that a constructor normally is meant to set the class up...in
         * this case we are bending the rules just to make the example clearer.
         * Really the constructor should be empty and we should be doing this in a
         * method.
         */
        public CPUInfo() {
// declare the Sigar class (we need this because it gathers the
// statistics)
            Sigar sigar = new Sigar();

// the output string is just going to hold our output string
            String output = "";

// an array of CPU Info classes. The CPUInfo class is a blank data
// holder class. Just like string it can hold any data.
            CpuInfo[] cpuInfoList = null;
            org.hyperic.sigar.NetInfo netInfoList = null;
            NetStat netstat =null;
            NetRoute[] Route = null;
            NetInterfaceConfig NeTInterfaceConfig  = null;
            NetConnection[] NetCon = null;
            //NetInfo Netinf = null


            Tcp TCP = null;

// the try catch block means that if we get an error we are notified
            try {
// get the CPU information from the sigar library
                cpuInfoList = sigar.getCpuInfoList();
                netInfoList = sigar.getNetInfo();
                netstat = sigar.getNetStat();
                Route = sigar.getNetRouteList();
                TCP = sigar.getTcp();
                String[] Stringies = sigar.getNetInterfaceList();
                NetCon = sigar.getNetConnectionList(1);
               // Netinf = sigar.getNetInfo();

                System.out.print(Stringies[2]);
// if something foes wrong
            } catch (SigarException e) {
// write a description of the problem to the output
                e.printStackTrace();

// exit the constructor
                return;
            }

// for each item in the cpu info array
            for (CpuInfo info : cpuInfoList) {
// add the data to the output ( output += "something" means add
// "something" to the end of output)

                output += "\nCPU\n";
                output += "Vendor: " + info.getVendor() + "\n";
                output += "Core " + info.getCoresPerSocket() + "\n";
                output += "Total Cores " + info.getTotalCores() + "\n";
                output += "Total Sockets " + info.getTotalSockets() + "\n";
                output += "Cash " + info.getCacheSize() + "\n";
                output += "Model " + info.getModel() + "\n";
                output += "Clock: " + info.getMhz() + "Mhz\n";
            }
            output += "--------Net Info-------\n\n";

            output += "Default GateWay " + netInfoList.getDefaultGateway() + "\n";
            output += "Domain Name " + netInfoList.getDomainName() + "\n";
            output += "Host Name " + netInfoList.getHostName() + "\n";
            output += "DNS " + netInfoList.getPrimaryDns() + "\n";
            output += "S Dns " + netInfoList.getSecondaryDns() + "\n";

            output += "TCP last ack " + netstat.getTcpLastAck() + "\n\n";

            output += "-------Kernel Ip Table-------\n\n";

            for (NetRoute Rout  : Route) {
// add the data to the output ( output += "something" means add
// "something" to the end of output)

                output += " " + Rout.getDestination();
                output += " " + Rout.getGateway();
                output += " " + Rout.getFlags();
                output += " " + Rout.getIfname();
                output += " " + Rout.getIrtt();
                output += " " + Rout.getMask();
                output += " " + Rout.getMetric()+ "\n";
            }

            output += "--------NetStat-------\n\n";

            output += "getAllInboundTotal " + netstat.getAllInboundTotal()+ "\n";
            output += "AllOutboundTotal " + netstat.getAllOutboundTotal()+ "\n";
            output += "getTcpBound " + netstat.getTcpBound()+ "\n";

            output += "--------TCP-------\n\n";

            output += "ActiveOpens " + TCP.getActiveOpens()+ "\n";
            output += "EstabResets " + TCP.getEstabResets()+ "\n";

            output += "--------NeT Interface Config-------\n\n";

            //output += "Description " + str[0] "\n";
           // output += "Destination " + NeTInterfaceConfig.getDestination()+ "\n";
            //output += "Broadcast " + NeTInterfaceConfig.getBroadcast() + "\n";



            output += "--------Net connections-------\n\n";

          //  output += "LocalAddress " + NetCon[0].getLocalAddress()+ "\n";
           // output += "LocalPort " + NetCon[0].getLocalPort()+ "\n";
            //output += "RemoteAddress " + NetCon[0].getRemoteAddress()+ "\n";
            //output += "State " + NetCon[0].getStateString()+ "\n";




// finally, print the data to the output
            //System.out.println(output);
        }

        public static void main(String[] args) throws SigarException, InterruptedException {

            NetInterfaces netInterfaces = new NetInterfaces();
           while(true) {
               System.out.print(netInterfaces.ToString());
              Thread.sleep(10000);
           }
            //NetInterfaces netInterfaces = new NetInterfaces();
            //System.out.print(netInterfaces.ToString());




        }
    }


