/**
 * PIMEntity
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public abstract class PIMEntity {
    String Priority;
    // default constructor sets priority to "high" "above" "normal" "below_normal" "idle"
    PIMEntity() {
        Priority = "normal";
    }
    PIMEntity(String priority) {
        Priority =  priority;
    }
    public String getPriority() {
        return Priority;
    }
    public void setPriority(String p) {
        Priority = p;
    }
    abstract public void fromString(String s);
    abstract public String toString();
}