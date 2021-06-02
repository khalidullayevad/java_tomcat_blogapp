package servlets;

import db.DBManager;
import db.Student;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/detail")
public class DetailPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student currentUser = (Student) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){
            Long id = Long.parseLong(request.getParameter("post_id"));
            request.setAttribute("post", DBManager.getPost(id));
            request.getRequestDispatcher("views/detail.jsp").forward(request,response);
        }
        else{
            response.sendRedirect("/");
        }
    }
}
