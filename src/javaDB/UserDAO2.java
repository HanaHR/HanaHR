package javaDB;
import javaDB.DB1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UserDAO2 {

    public int getCandidatesCount() {
        int totalCount = 0;
        try {
            Connection connection = DB1.getConnection();
            String query = "SELECT COUNT(memberNumber) AS totalCandidate FROM memberInfo;";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                totalCount = resultSet.getInt("totalCandidate");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return totalCount;
    }
    public List<Map<String, String>> viewCandidate() {
        List<Map<String, String>> viewResult = new ArrayList<>();
        try {
            Connection connection = DB1.getConnection();
            String query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass " +
                    "from memberInfo as m join score as s on m.memberNumber = s.memberNumber";

            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Map<String, String> hm = new HashMap<>();
                hm.put("지원자번호", Integer.toString(resultSet.getInt("memberNumber")));
                hm.put("이름", resultSet.getString("memberName"));
                hm.put("전공유무", Integer.toString(resultSet.getInt("memberMajor")));
                hm.put("전화번호", resultSet.getString("memberPhone"));
                hm.put("이메일", resultSet.getString("memberEmail"));
                hm.put("서류점수", Integer.toString(resultSet.getInt("memberPaperScore")));
                hm.put("서류합격여부", Integer.toString(resultSet.getInt("memberPaperPass")));
                hm.put("필기점수", Integer.toString(resultSet.getInt("memberWrittenScore")));
                hm.put("필기합격여부", Integer.toString(resultSet.getInt("memberWrittenPass")));
                hm.put("1차면접점수", Integer.toString(resultSet.getInt("memberInterview1Score")));
                hm.put("1차면접합격여부", Integer.toString(resultSet.getInt("memberInterview1Pass")));
                hm.put("2차면접점수", Integer.toString(resultSet.getInt("memberInterview2Score")));
                hm.put("2차면접합격여부", Integer.toString(resultSet.getInt("memberInterview2Pass")));

                viewResult.add(hm);
            }
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return viewResult;
    }

    public List<Map<String, String>> AvgScore() {
        List<Map<String, String>> avgResult = new ArrayList<>();
        try {
            Connection connection = DB1.getConnection();
            String query = "SELECT AVG(memberPaperScore) as avgPaperScore, AVG(memberWrittenScore) as avgrWrittenScore,  " +
                    "AVG(memberInterview1Score) as avgInterview1Score, AVG(memberInterview2Score) as avgInterview2Score FROM score;";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Map<String, String> hm = new HashMap<>();
                hm.put("서류전형", Float.toString(resultSet.getFloat("avgPaperScore")));
                hm.put("필기전형", Float.toString(resultSet.getFloat("avgrWrittenScore")));
                hm.put("1차면접점수", Float.toString(resultSet.getFloat("avgInterview1Score")));
                hm.put("2차면접점수", Float.toString(resultSet.getFloat("avgInterview2Score")));
                avgResult.add(hm);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return avgResult;
    }

    public List<Integer> getCutLine() {
        int totalCut = 0;
        List<Integer> cutResult = new ArrayList<Integer>();
        String[] columnName1 = {"memberPaperScore", "memberWrittenScore", "memberInterview1Score", "memberInterview2Score"};
        String[] columnName2 = {"memberPaperPass", "memberWrittenPass", "memberInterview1Pass", "memberInterview2Pass"};
        String[] columnName3 = {"minPaperPass", "minWrittenPass", "minInterview1Pass", "minInterview2Pass"};
        try {
            Connection connection = DB1.getConnection();
            for(int i=0;i<4;i++) {
                String query = "select min(" + columnName1[i] + ") as "+columnName3[i]+" from memberInfo as m join score " +
                        "as s on m.memberNumber = s.memberNumber where s."+columnName2[i]+"=1;";
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();
                while (resultSet.next()) {
                    totalCut = resultSet.getInt(columnName3[i]);
                }
                cutResult.add(totalCut);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return cutResult;
    }

    public List<Float> getPassRate() {
        List<Float> passResult = new ArrayList<Float>();
        String[] columnVarName = {"memberPaperPass", "memberWrittenPass", "memberInterview1Pass", "memberInterview2Pass"};
        int totalCandidate = 0;
        int passCandidate = 0;
        try {
            Connection connection = DB1.getConnection();
            String query = "SELECT COUNT(memberNumber) AS totalCandidate FROM memberInfo;";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                totalCandidate = resultSet.getInt("totalCandidate");
            }

            for(int i=0;i<4;i++) {
                String query2 = "SELECT count(memberNumber) AS countPass from score where "+ columnVarName[i] + "=1;";
                PreparedStatement pstmt2 = connection.prepareStatement(query2);
                ResultSet resultSet2 = pstmt2.executeQuery();

                while (resultSet2.next()) {
                    passCandidate = resultSet2.getInt("countPass");
                }
                passResult.add(new Float(passCandidate*100 / totalCandidate));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return passResult;
    }
}