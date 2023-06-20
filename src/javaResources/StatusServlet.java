package javaResources;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
//g
@WebServlet("/candidateStatus")
public class StatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf8", "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        UserDAO2 dao = new UserDAO2();

        int viewResult1 = dao.getCandidatesCount();
        request.setAttribute("rst1", viewResult1);

        List<Map<String, String>> viewResult2 = dao.viewCandidate();
        request.setAttribute("rst2", viewResult2);

        List<Map<String, String>> viewResult3 = dao.AvgScore();
        request.setAttribute("rst3", viewResult3);

        List<Integer> viewResult4 = dao.getCutLine();
        request.setAttribute("result4-1", viewResult4.get(0));
        request.setAttribute("result4-2", viewResult4.get(1));
        request.setAttribute("result4-3", viewResult4.get(2));
        request.setAttribute("result4-4", viewResult4.get(3));

        List<Float> viewResult5 = dao.getPassRate();
        request.setAttribute("result5-1", viewResult5.get(0));
        request.setAttribute("result5-2", viewResult5.get(1));
        request.setAttribute("result5-3", viewResult5.get(2));
        request.setAttribute("result5-4", viewResult5.get(3));


        RequestDispatcher dispatcher = request.getRequestDispatcher("candidateStatus.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
