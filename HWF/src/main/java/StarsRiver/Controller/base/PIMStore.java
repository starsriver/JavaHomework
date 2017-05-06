package StarsRiver.Controller.base;
/**
 * PIMStore
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public interface PIMStore {
    public boolean Open(String[] str);
    public boolean Write(String[] str);
    public String[] Read();
    public void Close();
}