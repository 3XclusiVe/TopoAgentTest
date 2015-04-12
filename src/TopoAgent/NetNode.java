package TopoAgent;

public class NetNode {
    public String IPAddress;
    private String SubNetMask;
    private NetNode[] ConnectedNode;

    public NetNode(String IPAddress, String SubNetMask) {
        this.IPAddress = IPAddress;
        this.SubNetMask = SubNetMask;
    }

    public void print() {
        System.out.print("\t\t\t\t" + IPAddress);
        System.out.println(" " + SubNetMask);
        if(ConnectedNode != null){
        for(int i = 0; i < ConnectedNode.length; i++) {
            System.out.print("\t\t\t\t");
            ConnectedNode[i].print();
        }
        }
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
