package borka.com.dbexample;
public class Shop {
    private int id;
    private String name;
    private String address;
    public Shop() //ריק
    {
    }
    public Shop(int id,String name,String address) // השדות כל
    {
        this.id=id;
        this.name=name;
        this.address=address;
    }
    public Shop(String name,String address) // והכתובת השם רק
    {
        this.name=name;
        this.address=address;
    }
    //get and set
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
}