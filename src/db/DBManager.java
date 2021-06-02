package db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dinaraProject?useUnicode=true&serverTimezone=UTC",
                                                     "root",
                                                     "");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Student getStudentByLogin(String login){

        Student student = null;
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  *  FROM student  WHERE email =?  ");

            statement.setString(1, login);

            ResultSet resultSet =statement.executeQuery();

            if (resultSet.next()){
                student = (new Student (resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("class_group")));
            }

            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
    public static ArrayList<Student> getStudentByClass(String group){

        ArrayList<Student> student = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  *  FROM student  WHERE class_group =?  ");

            statement.setString(1, group);

            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                student .add (new Student (resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("class_group")));
            }

            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
    public static ArrayList<Student> getAllStudents(){

        ArrayList<Student> student = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  *  FROM student   ");


            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                student .add (new Student (resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("class_group")));
            }

            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
    public static ArrayList<Student> getStudentByName(String name){
       name ="%"+name+"%";
        ArrayList<Student> student = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  *  FROM student  WHERE full_name LIKE ?  ");

            statement.setString(1, name);

            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                student .add (new Student (resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("class_group")));
            }

            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
    public static boolean addStudent(Student student) {
        //This is code for input registration data to the database
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO student " +
                    "( email, password, full_name, birthdate ,picture_url,class_group)" +
                    " VALUES (?,?,?,?,?,?)");

            statement.setString(1, student.getEmail());
            statement.setString(2, student.getPassword());
            statement.setString(3, student.getFull_name());
            statement.setDate(4, Date.valueOf(student.getBirthdate()));
            statement.setString(5, student.getPicture_url());
            statement.setString(6, student.getGroup());
            res = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res > 0;

    }

//    This method for adding post into database. I use query for adding.
    public static boolean addPost(Post post) {
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO posts ( title, content, " +
                    "student_id ,checked,teachercomment," +
                    "point,category)" + " VALUES (?,?,?,?,?,?,?)");

            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setLong(3, post.getAuthor().getId());
            statement.setBoolean(4,post.isChecked());
            statement.setString(5, post.getTeachersComment());
            statement.setInt(6, post.getPoint());
            statement.setString(7, post.getCategory());
            res = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res > 0;

    }


//    This method for getting one Post by post id
    public static Post getPost(Long id){
        Post post = new Post();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id  " +"WHERE p.id = ? " +
                            " ORDER BY p.post_date DESC ");

            statement.setLong(1, id);

            ResultSet resultSet =statement.executeQuery();

            if (resultSet.next()){
                post= (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));


            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return post;
    }
// This method for getting all Posts where boolean operator equal to TRUE/FALSE. Which means post is checked or unchecked.
    public static ArrayList<Post> getAllPosts(boolean checked){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id where p.checked = ? " +
                            " ORDER BY p.post_date DESC ");
            statement.setBoolean(1,checked);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }
// This method for getting all posts
    public static ArrayList<Post> getAllTeacherPost(){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id " +
                            " ORDER BY p.post_date DESC ");


            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }
    // This method for getting posts depends on posts category and checked
    public static ArrayList<Post> getFilterPost(boolean checked, String type){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id where p.checked = ? AND p.category =? " +
                            " ORDER BY p.post_date DESC ");
            statement.setBoolean(1,checked);
            statement.setString(2,type);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getFilterForTeacherPost( String type){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id where  p.category =? " +
                            " ORDER BY p.post_date DESC ");

            statement.setString(1,type);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }


    public static Student getStudent(Long id){
        Student user =new Student();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  *  FROM student  WHERE id =? ");

            statement.setLong(1, id);

            ResultSet resultSet =statement.executeQuery();

            if (resultSet.next()){
                user = (new Student(resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("class_group")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean editPost(Post lg){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  posts SET title =?, content =?, category = ? " +
                    "WHERE id =?");
            statement.setString(1,lg.getTitle());
            statement.setString(2, lg.getContent());
            statement.setString(3,lg.getCategory());
            statement.setLong(4, lg.getId());
            res= statement.executeUpdate();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res>0;
    }
    public static boolean editAdminPost(Post lg){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  posts SET point =?, teachercomment =?, checked = ? " +
                    "WHERE id =?");
            statement.setInt(1,lg.getPoint());
            statement.setString(2, lg.getTeachersComment());
            statement.setBoolean(3,true);
            statement.setLong(4, lg.getId());
            res= statement.executeUpdate();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res>0;
    }
    public static boolean editProfile(Long id,String email, String full_name, String birthdate, String group){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  student SET email =?, full_name=?, birthdate=?, class_group=? " +
                    "WHERE id =?");
            statement.setString(1,email);
            statement.setString(2, full_name);
            statement.setDate(3, Date.valueOf(birthdate));
            statement.setString(4, group);
            statement.setLong(5, id);
            res= statement.executeUpdate();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res>0;
    }


    public static boolean editUserPhoto(Long id,String url){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  student SET picture_url=? " +
                    "WHERE id =?");
            statement.setString(1,url);
            statement.setLong(2, id);
            res= statement.executeUpdate();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res>0;
    }




    public static boolean editPassword(Long id,String pass){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  student SET password=? " +
                    "WHERE id =?");
            statement.setString(1,pass);
            statement.setLong(2, id);
            res= statement.executeUpdate();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res>0;
    }

    public static ArrayList<Post> getMyAllPosts(Long user_id){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE u.id = ? " +
                            " ORDER BY p.post_date DESC ");

            statement.setLong(1, user_id);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getMyVerified(Long user_id, boolean checked){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE u.id = ? and p.checked = ? " +
                            " ORDER BY p.post_date DESC ");

            statement.setLong(1, user_id);
            statement.setBoolean(2,checked);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getAllVerified( boolean checked){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE  p.checked = ? " +
                            " ORDER BY p.post_date DESC ");


            statement.setBoolean(1,checked);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getSearch(String search_text, boolean checked){
        ArrayList<Post> posts = new ArrayList<>();
        search_text = "%"+search_text+"%";
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE p.title LIKE ? AND p.checked =?  " +
                            " ORDER BY p.post_date DESC ");

            statement.setString(1, search_text);
            statement.setBoolean(2,checked);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }
public static ArrayList<Post> adminPostFilter(String title,String category){

        ArrayList<Post> posts = new ArrayList<>();
        if(title.equals("") && category.equals("All")){
            posts = getAllTeacherPost();
        }
        else if(!title.equals("")  && !category.equals("All")){
            posts = getPostByTitileAndByCategory(title,category);
        }
        else if(!title.equals("")  && category.equals("All")){
            posts = getPostByTitile(title);
        }
        else if(title.equals("")  && !category.equals("All")){
            posts = getPostByCategory(category);
        }
        return posts;
}

    public static ArrayList<Post> getPostByTitileAndByCategory(String title, String category){
        ArrayList<Post> posts = new ArrayList<>();
        title = "%"+title+"%";
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE p.title LIKE ?  and p.category = ? " +
                            " ORDER BY p.post_date DESC ");

            statement.setString(1, title);
            statement.setString(2,category);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }
    public static ArrayList<Post> getPostByTitile(String title){
        ArrayList<Post> posts = new ArrayList<>();
        title = "%"+title+"%";
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE p.title LIKE ?  " +
                            " ORDER BY p.post_date DESC ");

            statement.setString(1, title);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getPostByCategory(String category){
        ArrayList<Post> posts = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE p.category =?  " +
                            " ORDER BY p.post_date DESC ");

            statement.setString(1, category);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getAlThisStudentPosts(Long id){
        ArrayList<Post> posts = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT  p.id,p.post_date, u.id as student_id, p.title, p.content,p.checked, p.teachercomment,p.point,p.category, " +
                            "u.email, u.password,u.full_name, u.birthdate, u.picture_url,u.class_group " +
                            " FROM posts p " +
                            "INNER JOIN student u ON u.id=p.student_id   WHERE u.id =?  " +
                            " ORDER BY p.post_date DESC ");

            statement.setLong(1, id);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                posts.add (new Post(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        resultSet.getBoolean("checked"),
                        resultSet.getString("teachercomment"),
                        resultSet.getInt("point"),
                        new Student(resultSet.getLong("student_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("birthdate"),
                                resultSet.getString("picture_url"),
                                resultSet.getString("password"),
                                resultSet.getString("email"),
                                resultSet.getString("class_group")),
                        resultSet.getString("category")));
            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }
}
