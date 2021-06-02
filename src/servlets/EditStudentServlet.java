package servlets;

import db.DBManager;
import db.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editStudent")
public class EditStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student currentUser = (Student) request.getSession().getAttribute("CURRENT_USER");
        String redirect ="/";

        if(currentUser != null) {
            Long id = currentUser.getId();
            redirect = "/editStudent?emailerror";
            if (request.getParameter("editProfile") != null) {
                String email = request.getParameter("email");
                String birthdate = request.getParameter("birthdate");
                String full_name = request.getParameter("full_name");
                String group =  request.getParameter("group");
                Student student = DBManager.getStudentByLogin(email);
                if(student == null || email.equals(currentUser.getEmail())) {
                    if (DBManager.editProfile(id,email, full_name, birthdate,group)) {

                        redirect = "/editStudent?emailsuccess";
                    }
                }
            }
            else if(request.getParameter("updatePhoto") != null){
                redirect = "/editStudent?photoerror";
                String url = request.getParameter("picture_url");
                if(DBManager.editUserPhoto(id,url)){

                    redirect = "/editStudent?photosuccess";
                }
            }
            else if(request.getParameter("updatePassword") != null){

                redirect = "/editStudent?passworderror";
                String old_password = request.getParameter("old_password");
                String new_password = request.getParameter("new_password");
                String re_password = request.getParameter("re_password");
                if(currentUser.getPassword().equals(old_password)){

                    if(new_password.equals(re_password)){
                        if(DBManager.editPassword(id,new_password)){

                            redirect ="/editStudent?passwordsuccess";
                        }
                    }
                }

            }

        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student currentUser = (Student) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser != null){
            currentUser = DBManager.getStudent(currentUser.getId());
            request.getSession().setAttribute("CURRENT_USER", currentUser);
            request.getRequestDispatcher("views/editStudentPage.jsp").forward(request,response);

        }
        else{
            response.sendRedirect("/");
        }

    }
}
