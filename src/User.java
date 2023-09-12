public class User {
    private final String Name;
    private final String MemberNumber;

    public User(String Name , String MemberNumber ){
        this.Name = Name;
        this.MemberNumber = MemberNumber;
    }

    public String getName(){
        return this.Name;
    }
    public String getMemberNumber(){
        return this.MemberNumber;
    }
}
