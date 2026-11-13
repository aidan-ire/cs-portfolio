/**
 * A teacher is a person who also teaches a subject.
 */
public class Teacher extends Person{
    private String subject;

    /**
     * @param name    the teacher's name
     * @param age     their age, must be 0 or more
     * @param subject the subject they teach
     */
    public Teacher(String name, int age, String subject){
        super(name, age);
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }

    // adds to Person's introduction rather than repeating it
    @Override
    public String introduce(){
        return super.introduce() + ", and I teach " + subject;
    }

    @Override
    public String toString(){
        return super.toString() + ", teaches " + subject;
    }
}
