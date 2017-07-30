
public class FooCorporation {
	
	private int number; 
	private double pay;
	private int hours;
	
	public FooCorporation(int i, double d, int j) {
		number = i;
		pay = d;
		hours = j;
	}
	
	public static double Salary(FooCorporation name){
		double Salary;
		if (name.hours < 40){
			Salary = name.hours * name.pay;
		}
		else {
			Salary = (name.hours - 40) * 0.5 * name.pay 
					+ name.hours * name.pay;
		};
		return Salary;
	}	
	
	public static void printSalary(FooCorporation name){
		if (name.pay < 8.00){
			System.out.print("Employee " + name.number + 
					" does not earn minimum wage of $8.00!");
		}	
		else if (name.hours > 60){
			System.out.print("Employee " + name.number + 
					" is not allowed to work over 60 hours!");
		}
		else {
			System.out.print("Employee " + name.number + 
				" is paid $" + Salary(name));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		FooCorporation employee1, employee2, employee3;
		employee1 = new FooCorporation(1, 7.50, 35);
		employee2 = new FooCorporation(2, 8.20, 47);
		employee3 = new FooCorporation(3, 10.00, 73);
		
		printSalary(employee1);
		printSalary(employee2);
		printSalary(employee3);
	}
}
