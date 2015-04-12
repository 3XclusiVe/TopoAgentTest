package TopoAgent;

public class Host {
    private String Name;
    private InternetInterface[] InterFace;

    public Host(String Name) {
        this.Name = Name;
    }

    public void AddInterface(String Name, String IPAddress, String MacAddress, String SubNetMask) {

        if(InterFace != null) {
        InternetInterface[] PrevInterface = InterFace;
        int PrevLength = InterFace.length;
        InterFace = new InternetInterface[PrevLength + 1];
        for(int i = 0; i < InterFace.length -1; i++) {
            InterFace[i] = PrevInterface[i];
        }

        InterFace[PrevLength] = new InternetInterface(Name, IPAddress, MacAddress, SubNetMask);
        } else {
            InterFace = new InternetInterface[1];
            InterFace[0] = new InternetInterface(Name, IPAddress, MacAddress, SubNetMask);
        }


    }

    public void print() {
        for(int i = 0; i < InterFace.length; i++) {
            InterFace[i].print();
        }
    }

    public InternetInterface  InterfaceWithName(String Name) {

        for(int i = 0; i < InterFace.length; i++) {
            if(InterFace[i].Name.equals(Name)) {
                return InterFace[i];
            }
        }
        return null;
    }
}
