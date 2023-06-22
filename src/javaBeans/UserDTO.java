package javaBeans;

import java.util.Date;

public class UserDTO {
    private String memberName;
    private int memberNumber;
    private String memberEmail;
    private String memberGender;
    private Date memberBirth;
    private String memberAddress;
    private boolean memberCareer;
    private String memberPhone;
    private boolean memberMajor;
    private int memberPaperScore;
    private int memberWrittenScore;
    private int memberInterview1Score;
    private int memberInterview2Score;
    private boolean memberPaperPass;
    private boolean memberInterview1Pass;
    private boolean memberInterview2Pass;
    private boolean memberWrittenPass;

    public UserDTO(String memberName, int memberNumber, String memberEmail, String memberGender, Date memberBirth,
                   String memberAddress, boolean memberCareer, String memberPhone, boolean memberMajor,
                   int memberPaperScore, int memberWrittenScore, int memberInterview1Score, int memberInterview2Score,
                   boolean memberPaperPass, boolean memberInterview1Pass, boolean memberInterview2Pass,
                   boolean memberWrittenPass) {
        this.memberName = memberName;
        this.memberNumber = memberNumber;
        this.memberEmail = memberEmail;
        this.memberGender = memberGender;
        this.memberBirth = memberBirth;
        this.memberAddress = memberAddress;
        this.memberCareer = memberCareer;
        this.memberPhone = memberPhone;
        this.memberMajor = memberMajor;
        this.memberPaperScore = memberPaperScore;
        this.memberWrittenScore = memberWrittenScore;
        this.memberInterview1Score = memberInterview1Score;
        this.memberInterview2Score = memberInterview2Score;
        this.memberPaperPass = memberPaperPass;
        this.memberInterview1Pass = memberInterview1Pass;
        this.memberInterview2Pass = memberInterview2Pass;
        this.memberWrittenPass = memberWrittenPass;
    }

    public int getMemberNumber() {
        return memberNumber;
    }


    public int getMemberPaperScore() {
        return memberPaperScore;
    }


    public int getMemberWrittenScore() {
        return memberWrittenScore;
    }



    public int getMemberInterview1Score() {
        return memberInterview1Score;
    }


    public int getMemberInterview2Score() {
        return memberInterview2Score;
    }


    public boolean isMemberPaperPass() {
        return memberPaperPass;
    }



    public boolean isMemberInterview1Pass() {
        return memberInterview1Pass;
    }


    public boolean isMemberInterview2Pass() {
        return memberInterview2Pass;
    }



    public boolean isMemberWrittenPass() {
        return memberWrittenPass;
    }







    public String getMemberGender() {
        return memberGender;
    }


    public Date getMemberBirth() {
        return memberBirth;
    }



    public String getMemberEAddress() {
        return memberAddress;
    }


    public boolean isMemberCareer() {
        return memberCareer;
    }


    public String getMemberPhone() {
        return memberPhone;
    }


    public boolean isMemberMajor() {
        return memberMajor;
    }








    public String getMemberName() {
        return memberName;
    }


    public String getMemberEmail() {
        return memberEmail;
    }


    @Override
    public String toString() {
        return "User [memberName=" + memberName +
                ", email=" + memberEmail +
                ", memberNumber=" + memberNumber +
                ", memberPaperScore=" + memberPaperScore +
                ", memberWrittenScore=" + memberWrittenScore +
                ", memberInterview1Score=" + memberInterview1Score +
                ", memberInterview2Score=" + memberInterview2Score +
                ", memberPaperPass=" + (memberPaperPass ? "합격" : "불합격") +
                ", memberInterview1Pass=" + (memberInterview1Pass ? "합격" : "불합격") +
                ", memberInterview2Pass=" + (memberInterview2Pass ? "합격" : "불합격") +
                ", memberWrittenPass=" + (memberWrittenPass ? "합격" : "불합격") +
                "]";
    }





}