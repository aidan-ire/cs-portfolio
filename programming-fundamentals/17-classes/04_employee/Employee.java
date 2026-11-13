/**
 * An employee with a name, an ID and a yearly salary.
 */
public class Employee{
    private String name;
    private int id;
    private double salary;

    /**
     * @param name   the employee's name
     * @param id     their employee number
     * @param salary their yearly salary, must be 0 or more
     * @throws IllegalArgumentException if salary is negative
     */
    public Employee(String name, int id, double salary){
        if(salary < 0) throw new IllegalArgumentException("salary can't be negative");
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public double getSalary(){
        return salary;
    }

    /**
     * Raises the salary by a percentage, eg. 5 for a 5% raise.
     * @param percent the size of the raise, must be 0 or more
     * @throws IllegalArgumentException if percent is negative, the salary is unchanged
     */
    public void giveRaise(double percent){
        if(percent < 0) throw new IllegalArgumentException("a raise can't be negative");
        salary = salary * (1 + percent / 100);
    }

    public double getMonthlySalary(){
        return salary / 12;
    }

    public String toString(){
        return String.format("#%d %s, salary %.2f", id, name, salary);
    }
}
