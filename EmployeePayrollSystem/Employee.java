    public abstract class Employee {
        
        // Declaring the variables
        private String name;
        private int id;

        // Creating a constructor to initialize the variables
        Employee(String name, int id){
            this.name = name;
            this.id = id;
        }

        // Creating a getters and setters to access and set those variables 
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }

        // Creating an abstract method to calculate salary
        public abstract double calcSalary();

        @Override
        public String toString(){
            return "Employee[name ="+ name + ", id ="+ id + ", salary = Rs."+ calcSalary() + "]";
        }

    }