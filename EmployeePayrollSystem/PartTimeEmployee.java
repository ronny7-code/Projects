public class PartTimeEmployee extends Employee{

    private int hoursWorked;
    private double hourlyPay;

    // Constructor 
    PartTimeEmployee(String name, int id, int hoursWorked, double hourlyPay) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyPay = hourlyPay;
    }

    // Getters and setters
    public int getHoursWorked(){
        return hoursWorked;
    }
    public double hourlyPay(){
        return hourlyPay;
    }
    public void setHoursWorked(int hoursWorked){
        this.hoursWorked = hoursWorked;
    }
    public void setHourlyPay(double hourlyPay){
        this.hourlyPay = hourlyPay;
    }

    @Override
    public double calcSalary(){
        return hoursWorked * hourlyPay;
    }

}
