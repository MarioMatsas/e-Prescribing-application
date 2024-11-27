public class Person {
    
    private String firstName;
    private String lastName;

    public Person(String fn, String ln){
        firstName = fn;
        lastName = ln;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setFirstName(String str)
    {
        firstName = str;
    }

    public void setLastName(String str)
    {
        lastName = str;
    }
}
