
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    public void init() throws ServletException {
        super.init();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://172.16.20.89:3306/hanahr?useUnicode=true&characterEncoding=utf8", "hanaro", "hanaro6666!");
            System.out.println("Login DB success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        // 세션 보유 여부 확인

        HttpSession session=request.getSession();

        if(session.getAttribute("admin")==null){
            response.sendRedirect("/");
        }
        else{
            response.sendRedirect("candidateStatus");
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 입력 아이디, 비밀번호 값 받기

        String adminId = request.getParameter("adminId");
        String adminPw = request.getParameter("adminPw");


        // DB에서 관리자 ID,PW 가져와서 비교하기

        String query = "select * from admin where adminId=? and adminPassword=?";

        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pstmt.setString(1,adminId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pstmt.setString(2,adminPw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ResultSet resultSet = pstmt.executeQuery();


            // 로그인 실패 시

            if(resultSet.next() == false){
                System.out.println("login fail");
                response.sendRedirect("/");
            }

            // 로그인 성공 시

            else{
                System.out.println("login success");

                // 세션 추가

                HttpSession session = request.getSession(true);
                session.setAttribute("admin", true);
                response.sendRedirect("candidateStatus");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}


