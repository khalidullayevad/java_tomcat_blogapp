package db;

import java.sql.Date;

public class Post {

    private Long id;
    private String title;
    private String content;
    private Date post_date;
    private boolean checked;
    private String teachersComment;
    private int point;
    private Student author;
    private String category;

    public Post() {
    }

    public Post(Long id, String title, String content, Date post_date, boolean checked, String teachersComment, int point, Student author, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.post_date = post_date;
        this.checked = checked;
        this.teachersComment = teachersComment;
        this.point = point;
        this.author = author;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTeachersComment() {
        return teachersComment;
    }

    public void setTeachersComment(String teachersComment) {
        this.teachersComment = teachersComment;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Student getAuthor() {
        return author;
    }

    public void setAuthor(Student author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", post_date=" + post_date +
                ", checked=" + checked +
                ", teachersComment='" + teachersComment + '\'' +
                ", point=" + point +
                ", author=" + author +
                ", category='" + category + '\'' +
                '}';
    }
}
