package servlets;

import db.DBManager;
import db.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/checkSignIn")
public class CheckSignInServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String redirect = "/signIn?emailerror";
        if(email.equals("teacher@gmail.com") && password.equals("123teacher")){
            request.getSession().setAttribute("TEACHER", email);
            redirect = "/admin?allPosts=true";
        }
        else {
            Student student = DBManager.getStudentByLogin(email);
            if (student != null) {
                redirect = "/signIn?passworderror";
                if (student.getPassword().equals(password)) {
                    request.getSession().setAttribute("CURRENT_USER", student);
                    redirect = "/studentPage?allPosts=true";
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
