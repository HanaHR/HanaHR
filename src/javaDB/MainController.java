package javaDB;

import javaBeans.User;
import javaResources.RequestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MainController", urlPatterns = {"/main", "/candidateSearch", "/pick", "/deleteCandidate","/updateCandidate","/emailServlet","/apply","/candidateStatus","/login", "/updatePasser"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            requestPro(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            requestPro(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        /* URL check */
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String command = uri.substring(context.length());
        String site = null;

        System.out.println("command : " + command);

        UserDAO user = new UserDAO();

        switch (command) {
            // 합격자 선정
            case "/pick":
                String process = request.getParameter("process");
                String headCount = request.getParameter("headCount");
                site="candidatePick.jsp";
                if (headCount != "") {
                    List<Map<String, String>> findResult = user.pickPasser(process, headCount);
                    request.setAttribute("passer", findResult);
                }
                break;
            // score 테이블 전형별 Pass값 변경 & pass 테이블에 최종 합격자 추가
            case "/updatePasser":
                String processUpdate = request.getParameter("process");
                String headCountUpdate = request.getParameter("headCount");
                if (headCountUpdate != "") {
                    List<Map<String, String>> findResult = user.pickPasser(processUpdate, headCountUpdate);
                    user.updatePasser(findResult, processUpdate, headCountUpdate);
                }
                site="candidatePick.jsp";
                break;

            case "/candidateSearch":

                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("utf-8");
                site = "candidateEdit.jsp";

                String searchName = request.getParameter("searchName");
                request.setCharacterEncoding("UTF-8");
                List<User> searchResults = user.searchUsersByName(searchName);
                request.setAttribute("searchResults", searchResults);
                break;

            case "/deleteCandidate":
                String memberNumber = request.getParameter("memberNumber");
                user.CandidateDelete(memberNumber);
                response.sendRedirect("candidateEdit.jsp");

                break;

            case "/updateCandidate":
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("utf-8");
                memberNumber = request.getParameter("memberName");
                String memberName = request.getParameter("memberName");
                boolean memberMajor = Boolean.parseBoolean(request.getParameter("memberMajor"));
                String memberPhone = request.getParameter("memberPhone");
                String memberEmail = request.getParameter("memberEmail");
                String memberPaperScoreStr = request.getParameter("memberPaperScore");
                int memberPaperScore = Integer.parseInt(memberPaperScoreStr);
                boolean memberPaperPass = Boolean.parseBoolean(request.getParameter("memberPaperPass"));
                String memberWrittenScoreStr = request.getParameter("memberWrittenScore");
                int memberWrittenScore =Integer.parseInt(memberWrittenScoreStr);
                boolean memberWrittenPass = Boolean.parseBoolean(request.getParameter("memberWrittenPass"));
                String memberInterview1ScoreStr = request.getParameter("memberInterview1Score");
                int memberInterview1Score = Integer.parseInt(memberInterview1ScoreStr);
                boolean memberInterview1Pass = Boolean.parseBoolean(request.getParameter("memberInterview1Pass"));
                String memberInterview2ScoreStr = request.getParameter("memberInterview2Score");
                int memberInterview2Score =Integer.parseInt(memberInterview2ScoreStr) ;
                boolean memberInterview2Pass = Boolean.parseBoolean(request.getParameter("memberInterview2Pass"));
                user.CandidateUpdate(memberNumber, memberName,memberMajor, memberPhone,memberEmail,memberPaperScore,memberPaperPass,memberWrittenScore,memberWrittenPass, memberInterview1Score,memberInterview1Pass,memberInterview2Score,memberInterview2Pass) ;
                break;

            case "/emailServlet":
                RequestUtils requestUtils = new RequestUtils(request, response);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain; charset=UTF-8");
                String selection1 = requestUtils.getParam("selection1"); // 전형 분류
                String selection2 = requestUtils.getParam("selection2"); // 지원자 분류
                String emailSubject = requestUtils.getParam("emailSubject"); // 메일 제목
                String emailContent = requestUtils.getParam("emailContent"); // 메일 내용
                user.CandidateSendmail(selection1, selection2, emailSubject,emailContent);
                request.setAttribute("resultMessage", "이메일 전송이 완료되었습니다.");


                break;

            case "/apply":
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("utf-8");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String gender = request.getParameter("gender");
                String temp_career = request.getParameter("career");
                String temp_major = request.getParameter("major");
                String birth = request.getParameter("birth");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                user.CandidateApply(name,email,gender,temp_career,temp_major,birth,address,phone);
                response.sendRedirect("apply.jsp");

                break;

            case "/candidateStatus":
                request.setCharacterEncoding("utf-8");

                int viewResult1 = user.getCandidatesCount();
                request.setAttribute("rst1", viewResult1);

                List<Map<String, String>> viewResult2 = user.viewCandidate();
                request.setAttribute("rst2", viewResult2);

                List<Map<String, String>> viewResult3 = user.AvgScore();
                request.setAttribute("rst3", viewResult3);

                List<Integer> viewResult4 = user.getCutLine();
                request.setAttribute("result4-1", viewResult4.get(0));
                request.setAttribute("result4-2", viewResult4.get(1));
                request.setAttribute("result4-3", viewResult4.get(2));
                request.setAttribute("result4-4", viewResult4.get(3));

                List<Float> viewResult5 = user.getPassRate();
                request.setAttribute("result5-1", viewResult5.get(0));
                request.setAttribute("result5-2", viewResult5.get(1));
                request.setAttribute("result5-3", viewResult5.get(2));
                request.setAttribute("result5-4", viewResult5.get(3));

                site = "candidateStatus.jsp";


                break;

            case "/login":
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();

                // 입력 아이디, 비밀번호 값 받기

                String adminId = request.getParameter("adminId");
                String adminPw = request.getParameter("adminPw");

                user.AdminLogin(adminId,adminPw);
                response.sendRedirect("candidateStatus");
                site = "index.jsp";
                return;




        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}
