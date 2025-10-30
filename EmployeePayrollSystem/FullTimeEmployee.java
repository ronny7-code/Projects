public class FullTimeEmployee extends Employee{

    private double monthlySalary;

    // Constructor
    FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }
    
    // Getters and setters
    public double getMonthlySalary(){
        return monthlySalary;
    }
    public void setMonthlySalary(double monthlySalary){
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calcSalary(){
        return monthlySalary;
    }

}