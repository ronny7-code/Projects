import java.util.*;

public class PayrollSystem{

    private ArrayList<Employee> employeeList = null;

    // Initializing arraylist in the constructor 
    public PayrollSystem(){
        employeeList = new ArrayList<>();
    }

    // Method to add employee
    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    // Method to remove employee by employee id
    public void removeEmployee(int id){
       
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.isEmpty()){
                System.out.println("EMPLOYEE LIST IS EMPTY!");
                System.out.println("ADD EMPLOYEE FIRST");
            }
            else{
                if(employeeList.get(i).getId() == id){
                employeeList.remove(i);
                 }
                else{
                    System.out.println("No employee found with employee id: " + id);
                 }
            }
        }
    }

    // Method to display employee
    public void displayEmployee(){
        for(Employee employee : employeeList){
            System.out.println(employee);
        }
    }
}