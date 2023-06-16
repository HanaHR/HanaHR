package javaBeans;

public class UserDTO {
    private int memberNumber;
    private String memberName;
    private String memberAddress;
    private String memberPhone;
    private String memberEmail;
    private Boolean memberMajor;

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public Boolean getMemberMajor() {
        return memberMajor;
    }

    public void setMemberMajor(Boolean memberMajor) {
        this.memberMajor = memberMajor;
    }

}
