package servlets;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/detailStudent")
public class StudentDetailPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacher = (String) request.getSession().getAttribute("TEACHER");
        if(teacher != null){
            Long id =Long.parseLong(request.getParameter("student_id"));
            request.setAttribute("posts", DBManager.getAlThisStudentPosts(id));
            request.getRequestDispatcher("views/admin.jsp").forward(request,response);
        }
        else{
            response.sendRedirect("/");
        }
    }
}
