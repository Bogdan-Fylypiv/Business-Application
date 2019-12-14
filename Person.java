
public abstract class Person{
	private String firstName;
	private String secondName;
	private String phoneNumber;

	public void setFirstName(String name){
		firstName = name;
	}
	public String getFirstName(){
		return firstName;
	}
	
	public void setSecondName(String name){
		secondName = name;
	}
	public String getSecondName(){
		return secondName;
	}

	public void setPhoneNumber(String phoneNumberValue){
		phoneNumber = phoneNumberValue;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
}