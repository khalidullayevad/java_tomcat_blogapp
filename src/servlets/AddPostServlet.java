package servlets;

import db.DBManager;
import db.Post;
import db.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value = "/addPost")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student currentUser = (Student) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String category = request.getParameter("category");

            DBManager.addPost(new Post(null, title, content,null, false,null,0,currentUser,category));
            response.sendRedirect("/studentPage");
        }
        else{
            response.sendRedirect("/");
        }
    }

}
