/**
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class Student{
    public Student(String ID){
        this.ID = ID;
    }
    public Student(String ID, String[] CourseList){
        this.ID = ID;
        this.CourseList = new java.util.ArrayList<Course>();
        for (String CourseName : CourseList) {
            this.CourseList.add(new Course(CourseName));
        }
    }
    public Student(String ID, String[] CourseList, String[] BookList){
        this.ID = ID;
        this.BookList = new java.util.ArrayList<Book>();
        for (String BookName : BookList) {
            this.BookList.add(new Book(BookName));
        }
        this.CourseList = new java.util.ArrayList<Course>();
        for (String CourseName : CourseList) {
            this.CourseList.add(new Course(CourseName));
        }
    }
    public String Name;
    public String ID;
    public java.util.ArrayList<Book> BookList;
    public java.util.ArrayList<Course> CourseList;
    public String toString(){
        String StudentInfo = this.ID;

        if(CourseList == null){
            StudentInfo += " have nothing Course";
        }
        else{
            StudentInfo += " choose ";
            for (Course course : CourseList) {
                StudentInfo += course.Name + " ";
            }
        }
        StudentInfo += ".";
        if(BookList == null){
            StudentInfo += " have nothing Book.";
        }
        else{
            StudentInfo += "have ";
            for (Book book : BookList) {
                StudentInfo += book.Name + " ";
            }
        }
        return StudentInfo;
        //return ID + " choose " + CourseListInfo + ".";
    }
}