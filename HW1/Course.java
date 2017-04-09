/**
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class Course{
    public Course(String CourseName){
        this.Name = CourseName;
    }
    public String Name;


    public static void main(String[] args) {
        Student student;
        if(args.length < 0){
            return;
        }
        if(args.length == 1){
            student = new Student(args[0]);
        }
        else
        {
            String[] CourseList = new String[args.length - 1];
            for(int i = 0; i < CourseList.length; i++){
                CourseList[i] = args[i + 1];
            }
            student = new Student(args[0], CourseList);
        }

        System.out.println(student.toString());        
    }
}