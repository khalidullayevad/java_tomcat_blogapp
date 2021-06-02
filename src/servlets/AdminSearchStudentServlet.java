package servlets;

import db.DBManager;
import db.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/adminSearchStudent")
public class AdminSearchStudentServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacher = (String) request.getSession().getAttribute("TEACHER");
        if(teacher != null) {

            ArrayList<Student> students = new ArrayList<>();
            String name = request.getParameter("name");
            students = DBManager.getStudentByName(name);
            request.setAttribute("students", students);
            request.getRequestDispatcher("views/adminStudent.jsp").forward(request, response);

        }
        else{
            response.sendRedirect("/");
        }
    }
}
