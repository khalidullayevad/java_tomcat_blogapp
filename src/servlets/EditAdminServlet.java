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

@WebServlet(value = "/editAdminPost")
public class EditAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacher = (String) request.getSession().getAttribute("TEACHER");
        if(teacher != null) {
            Long id = Long.parseLong( request.getParameter("post_id"));
          int point = Integer.parseInt(request.getParameter("point"));
            String comment = request.getParameter("comment");


            Post post =new Post();
            post.setId(id);
           post.setPoint(point);
           post.setTeachersComment(comment);
            DBManager.editAdminPost(post);
            response.sendRedirect("/admin?verifiedPosts");
        }
        else{
            response.sendRedirect("/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
