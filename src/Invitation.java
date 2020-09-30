public class Invitation {
    private String hostName;
    private String address;

    public Invitation(String n, String a)
    {
        hostName = n;
        address = a;
    }

    public Invitation(String address){
        this.address = address;
        this.hostName = "Host";
    }

    public String getHostName() {
        return hostName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String generateInvitation(String invitee){
        return "Dear " + invitee + ", please attend my event at " + this.address + ". See you then, " + this.hostName + ".";
    }



}
