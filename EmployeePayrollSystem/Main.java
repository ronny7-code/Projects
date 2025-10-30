public class Main {
    public static void main(String[] args) {
        
        // Creating an instance of PayrollSystem
        PayrollSystem payrollSystem = new PayrollSystem();

        // Creating an instance of FullTimeEmployee
        FullTimeEmployee employee1 = new FullTimeEmployee("Vikas", 1, 70000.0);

        // Creating an instance of PartTimeEmployee
        PartTimeEmployee employee2 = new PartTimeEmployee("Alexander", 2, 40, 100);

        // Adding the employees into the payrollSystem
        payrollSystem.addEmployee(employee1);
        payrollSystem.addEmployee(employee2);

        // Gettting initial employee details
        System.out.println("Initial Employee Details: ");
        payrollSystem.displayEmployee();
        System.out.println();
    
        // Removing employee
        payrollSystem.removeEmployee(2);
    
        // Gettting remaining employee details
        System.out.println("Remaining Employee Details: ");
        payrollSystem.displayEmployee();
    }
}
