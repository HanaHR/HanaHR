package javaBeans;

public class User {
    private String memberName;
    private String email;

    public User( String userName, String email) {

        this.memberName = userName;
        this.email = email;
    }




    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}