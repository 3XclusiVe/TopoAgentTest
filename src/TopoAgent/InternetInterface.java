package TopoAgent;

public class InternetInterface {

    public String Name;
    private String IPAddress;
    private String MacAddress;
    private String SubNetMask;
    private NetNode[] ConnectedNode;

    public InternetInterface(String Name, String IPAddress, String MacAddress, String SubNetMask) {
        this.Name = Name;
        this.IPAddress = IPAddress;
        this.MacAddress = MacAddress;
        this.SubNetMask = SubNetMask;
    }

    public void AddNode(String IPAddress,String SubNetMask) {
        if(ConnectedNode != null) {
            NetNode[] PrevNode = ConnectedNode;
            int PrevLength = ConnectedNode.length;
            ConnectedNode = new NetNode[PrevLength + 1];
            for(int i = 0; i < ConnectedNode.length -1; i++) {
                ConnectedNode[i] = PrevNode[i];
            }

            ConnectedNode[PrevLength] = new NetNode(IPAddress,SubNetMask);
        } else {
            ConnectedNode = new NetNode[1];
            ConnectedNode[0] = new NetNode(IPAddress, SubNetMask);
        }
    }

    public void print() {

        System.out.println("--------NetInterface: " + Name + "--------");
        System.out.println("IP:" + IPAddress);
        System.out.println("Mac:" + MacAddress);
        System.out.println("Mask:" + SubNetMask);
            if(ConnectedNode != null)  {
                for(int i = 0; i < ConnectedNode.length; i++) {
                       ConnectedNode[i].print();
                }
            }
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public NetNode FindWithNodeWithIP(String IP) {
        if(ConnectedNode != null) {
            for(int i = 0; i < ConnectedNode.length; i++) {

                if(ConnectedNode[i].IPAddress.equals(IP)){
                    return ConnectedNode[i];
                }

            }

        }
        return null;
    }

}
