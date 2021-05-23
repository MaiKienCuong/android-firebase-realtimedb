package android.maikiencuong;

public class User {

    private String id;
    private String name;
    private String email;
    private int angry;
    private int bored;
    private int smile;

    public User() {
        angry = 0;
        bored = 0;
        smile = 0;
    }

    public User(String name, String email) {
        this();
        this.name = name;
        this.email = email;
    }

    public User(String id, String name, String email) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAngry() {
        return angry;
    }

    public void setAngry(int angry) {
        this.angry = angry;
    }

    public int getBored() {
        return bored;
    }

    public void setBored(int bored) {
        this.bored = bored;
    }

    public int getSmile() {
        return smile;
    }

    public void setSmile(int smile) {
        this.smile = smile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", angry=" + angry +
                ", bored=" + bored +
                ", smile=" + smile +
                '}';
    }
}
