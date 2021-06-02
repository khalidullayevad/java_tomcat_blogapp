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

@WebServlet(value = "/editPost")
public class EditPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student currentUser = (Student) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){
            Long id = Long.parseLong( request.getParameter("post_id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String short_content = request.getParameter("category");

            Post post =new Post();
            post.setId(id);
            post.setContent(content);
            post.setCategory(short_content);
            post.setTitle(title);
            DBManager.editPost(post);
            response.sendRedirect("/studentPage?unverifiedPosts");
        }
        else{
            response.sendRedirect("/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
