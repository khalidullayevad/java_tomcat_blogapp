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
import java.util.ArrayList;

@WebServlet(value = "/studentPage")
public class StudentPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Student currentStudent = (Student) request.getSession().getAttribute("CURRENT_USER");
        if(currentStudent != null){
            ArrayList<Post> posts =new ArrayList<>();
            if (request.getParameter("allPosts") != null) {
               posts = DBManager.getAllPosts(true);

            }
            else if(request.getParameter("myAllPosts")!=null){
              posts =DBManager.getMyAllPosts(currentStudent.getId());

            }
            else if(request.getParameter("verifiedPosts")!=null){
                posts = DBManager.getMyVerified(currentStudent.getId(), true);
            }
            else if(request.getParameter("unverifiedPosts")!=null){
                posts = DBManager.getMyVerified(currentStudent.getId(), false);
            }

            request.setAttribute("posts", posts);
            request.getRequestDispatcher("views/studentPage.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/");
        }
    }
}
