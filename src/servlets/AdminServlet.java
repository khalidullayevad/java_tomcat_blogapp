package servlets;

import db.DBManager;
import db.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String teacher = (String) request.getSession().getAttribute("TEACHER");
       if(teacher != null){
           ArrayList<Post> posts =new ArrayList<>();
           if (request.getParameter("allPosts") != null) {
               posts = DBManager.getAllTeacherPost();

           }
           else if(request.getParameter("verifiedPosts")!=null){
               posts = DBManager.getAllVerified( true);
           }
           else if(request.getParameter("unverifiedPosts")!=null){
               posts = DBManager.getAllVerified( false);
           }
           else if (request.getParameter("romance") != null) {
               posts =DBManager.getFilterForTeacherPost( "Romance");

           }
           else if(request.getParameter("humor") != null){
               posts =DBManager.getFilterForTeacherPost( "Humor");
           }
           else if(request.getParameter("mix") != null){
               posts =DBManager.getFilterForTeacherPost( "Mix");
           }
           else if(request.getParameter("drama") != null){
               posts =DBManager.getFilterForTeacherPost( "Drama");
           }
           else if(request.getParameter("essay") != null){
               posts =DBManager.getFilterForTeacherPost( "Essay");
           }
           else if(request.getParameter("article") != null){
               posts =DBManager.getFilterForTeacherPost("Article");
           }
           else if(request.getParameter("fantasy") != null){
               posts =DBManager.getFilterForTeacherPost( "Fantasy");
           }
           request.setAttribute("posts", posts);
           request.getRequestDispatcher("views/admin.jsp").forward(request, response);
       }
       else{
           response.sendRedirect("/");
       }

    }
}
