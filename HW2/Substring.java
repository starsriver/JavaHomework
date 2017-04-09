/**
 *  Substring
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
import java.lang.*;
public class  Substring {
    public static void main(String[] args) {
        try{
            String str = args[0].substring(Integer.parseInt(args[1]), Integer.parseInt(args[1]) + Integer.parseInt(args[2]));
            System.out.println(str);
        }
        catch(ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch(NumberFormatException e){
            throw e;
        }
        catch(IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch(Exception e){
            throw e;
        }
    }
}