/**
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class SumOfArgs{
    public static void main(String[] args) {
        int sum = 0;
        for (String str : args) {
            int a = 0;
            try{
                a = Integer.parseInt(str);
            }
            catch(NumberFormatException e){
                a = 0;
            }
            finally{
                sum += a;
            }
        }
        System.out.println(sum);
    }
}