
public class Employee extends Person {
	private double salary;
	private double hoursWorked;
	private boolean hired;

	public Employee(String name, String surname){
		setFirstName(name);
		setSecondName(surname);
	}

	public Employee(double salaryValue, double hoursWorkedValue, boolean hiredValue){
		setSalary(salaryValue);
		setHoursWorked(hoursWorkedValue);
		setHired(hiredValue);
	}

	public Employee(String name, String surname, double salaryValue, double hoursWorkedValue, boolean hiredValue){
		setFirstName(name);
		setSecondName(surname);
		setSalary(salaryValue);
		setHoursWorked(hoursWorkedValue);
		setHired(hiredValue);
	}

	public Employee(){}

	public void setSalary(double salaryValue){
		salary = salaryValue;
	}
	public double getSalary(){
		return salary;
	}

	public void setHoursWorked(double hoursWorkedValue){
		hoursWorked = hoursWorkedValue;
	}
	public double getHoursWorked(){
		return hoursWorked;
	}

	public void setHired(boolean hiredValue){
		hired = hiredValue;
	}
	public boolean getHired(){
		return hired;
	}

	@Override
	public void setPhoneNumber(String phoneNumberValue){
		if (phoneNumberValue.substring(0,2).equals("020")) {
			super.setPhoneNumber(phoneNumberValue);
		}
		
	}
}