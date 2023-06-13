//import javax.servlet.http.HttpSession;
//
//public class MainServlet {
//
//    // GET 방식으로 접근 시
//
//    HttpSession session = request.getSession();
//
//    String session_check=(String) session.getAttribute("관리자");
//
//    // 세션이 있으면 main.jsp
//
//        if(session_check.isEmpty()==false){
//        response.sendRedirect("main.jsp");
//    }
//
//    // index.jsp 로 redirect 하기
//
//        else{
//        response.sendRedirect("/");
//    }
//}
