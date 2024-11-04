1Q.
//Base class:Employee
class Employee {
    private int employeeId;
    private String employeeName;
    private String designation;
    //constructor
    public Employee(int employeeId, String employeeName, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    //getters for Employee attributes
    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getDesignation() { return designation; }

    //default bonus calculation to be overridden
    public double calculateBonus() { return 0; }
    //display basic employee details
    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }


//derived class:HourlyEmployee
}
public class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) throw new IllegalArgumentException("Hourly rate must be non-negative.");
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0) throw new IllegalArgumentException("Hours worked must be non-negative.");
        this.hoursWorked = hoursWorked;
    }

    public double calculateWeeklySalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateBonus() {
        return 0; // Hourly employees typically do not get bonuses
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}
//derived class:SalariedEmployee
public class SalariedEmployee extends Employee {
    private double monthlySalary;

    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        setMonthlySalary(monthlySalary);
    }

    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary < 0) throw new IllegalArgumentException("Monthly salary must be non-negative.");
        this.monthlySalary = monthlySalary;
    }

    public double calculateWeeklySalary() {
        return monthlySalary / 4;
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.05; // Example: 5% bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
    }
}
//further Derived class:ExecutiveEmployee
class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;
    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        this.bonusPercentage = (bonusPercentage >= 0 && bonusPercentage <= 100) ? bonusPercentage : 0;
    }

    @Override
    public double calculateBonus() {
        return super.calculateWeeklySalary() * 4 * 12 * (bonusPercentage / 100);
    }

    @Override
    public void displayInfo() {
        System.out.println("Executive Employee Details:");
        System.out.printf("Employee ID: %d%n", getEmployeeId());
        System.out.printf("Employee Name: %s%n", getEmployeeName());
        System.out.printf("Designation: %s%n", getDesignation());
        System.out.printf("Monthly Salary: %.2f%n", getMonthlySalary());
        System.out.printf("Weekly Salary: %.2f%n", calculateWeeklySalary());
        System.out.printf("Bonus Percentage: %.2f%n", bonusPercentage);
        System.out.printf("Annual Bonus: %.2f%n", calculateBonus());
    }
    
    //getter for monthly salary to use in display info
    public double getMonthlySalary() {
        return super.calculateWeeklySalary() * 4;
    }
}
//main class to test the payroll system
public class PayrollSystem {
    public static void main(String[] args) {
        //create and display each employee's information
        new HourlyEmployee(1, "manu", "Teaching Assistant", 20.5, 40).displayInfo();
        new SalariedEmployee(2, "shiva", "Lecturer", 4000).displayInfo();
        new ExecutiveEmployee(3, "loki", "Head of Department", 8000, 15).displayInfo();
}
}
