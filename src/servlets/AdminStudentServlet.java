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

@WebServlet(value = "/adminStudent")
public class AdminStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacher = (String) request.getSession().getAttribute("TEACHER");
        if(teacher != null){
            ArrayList<Student> students = new ArrayList<>();
            if(request.getParameter("12A") != null) {
                students = DBManager.getStudentByClass("12A");
            }
            else if (request.getParameter("All") != null){
                students = DBManager.getAllStudents();
            }
            else if(request.getParameter("12B") != null) {
                students = DBManager.getStudentByClass("12B");
            }
            else if(request.getParameter("12C") != null) {
                students = DBManager.getStudentByClass("12C");
            }
            else if(request.getParameter("12D") != null) {
                students = DBManager.getStudentByClass("12D");
            }
            else  if(request.getParameter("11A") != null) {
                students = DBManager.getStudentByClass("11A");
            }
            else if(request.getParameter("11B") != null) {
                students = DBManager.getStudentByClass("11B");
            }
            else if(request.getParameter("11C") != null) {
                students = DBManager.getStudentByClass("11C");
            }
            else if(request.getParameter("11D") != null) {
                students = DBManager.getStudentByClass("11D");
            }
            else  if(request.getParameter("10A") != null) {
                students = DBManager.getStudentByClass("10A");
            }
            else if(request.getParameter("10B") != null) {
                students = DBManager.getStudentByClass("10B");
            }
            else if(request.getParameter("10C") != null) {
                students = DBManager.getStudentByClass("10C");
            }
            else if(request.getParameter("10D") != null) {
                students = DBManager.getStudentByClass("10D");
            }
            request.setAttribute("students", students);
            request.getRequestDispatcher("views/adminStudent.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/");
        }
    }
}
