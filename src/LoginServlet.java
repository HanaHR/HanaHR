import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

       response.sendRedirect("/");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 입력 아이디, 비밀번호 값 받기

        String adminId = request.getParameter("adminId");
        String adminPw = request.getParameter("adminPw");

        // DB에서 관리자 ID,PW 가져와서 비교하기



        // 로그인 성공 시 main.jsp

        if(adminId.equals("admin") && adminPw.equals("1234")){

            // 세션 추가

            HttpSession session = request.getSession();
            session.setAttribute("관리자",adminId);

            response.sendRedirect("main.jsp");
        }

        // 로그인 실패 시 index.jsp

        else{
            response.sendRedirect("/");
        }




    }
}

