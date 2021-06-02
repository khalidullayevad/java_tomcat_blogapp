package servlets;

import db.DBManager;
import db.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password =request.getParameter("re-password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String full_name= name+" "+surname;
        String group = request.getParameter("group");
        String birthdate = request.getParameter("birthdate");
        String url = "https://img.icons8.com/material/452/portrait.png";
        String redirect = "/registration?passworderror";
        if (re_password.equals(password)){


                redirect = "/registration?data";
                if (!name.equals("") && !password.equals("") && !surname.equals("") && !group.equals("") && !birthdate.equals("")) {
                    redirect = "/registration?passworderrorLength";
                    if(password.length()>=8) {
                    redirect = "/registration?full_name";
                    if (name.length() <=50) {
                        redirect = "/registration?emailerror";
                        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(email);
                        Student student = DBManager.getStudentByLogin(email);
                        if (student == null && matcher.matches()) {
                            Student newStudent = new Student(null, full_name, birthdate, url, password, email, group);
                            if (DBManager.addStudent(newStudent)) {
                                redirect = "/signIn";
                            }
                        }
                    }
                }
            }
        }
        response.sendRedirect(redirect);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/registration.jsp").forward(request, response);
    }
}
