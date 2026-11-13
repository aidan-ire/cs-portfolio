/**
 * A student is a person with a student number and a set of module marks.
 */
public class Student extends Person{
    private String studentId;
    private int[] marks;
    private int count;

    /**
     * @param name       the student's name
     * @param age        their age, must be 0 or more
     * @param studentId  their student number
     * @param maxModules the most marks that can be stored
     */
    public Student(String name, int age, String studentId, int maxModules){
        super(name, age);
        this.studentId = studentId;
        this.marks = new int[maxModules];
        this.count = 0;
    }

    public String getStudentId(){
        return studentId;
    }

    /**
     * Records a module mark.
     * @param mark the mark, from 0 to 100
     * @return true if it was added, false if the mark is out of range or there is no room left
     */
    public boolean addMark(int mark){
        if(mark < 0 || mark > 100 || count == marks.length) return false;
        marks[count] = mark;
        count++;
        return true;
    }

    /**
     * @return the average of the marks so far, or 0 if there are none
     */
    public double getAverage(){
        if(count == 0) return 0;

        int total = 0;
        for(int i = 0; i < count; i++){
            total = total + marks[i];
        }
        return (double) total / count;
    }

    // 40 is the pass mark
    public boolean isPassing(){
        return count > 0 && getAverage() >= 40;
    }

    @Override
    public String introduce(){
        return super.introduce() + ", student number " + studentId;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %d %s, average %.1f", super.toString(), studentId, count, count == 1 ? "mark" : "marks", getAverage());
    }
}
