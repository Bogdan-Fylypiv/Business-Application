class Laptop extends Product{
	private int yearOfManufacture;
	private double inches;
	private double processorSpeed;

	public Laptop(String brandValue, int yearOfManufactureValue, double value, double inchesValue, double processorSpeedValue){
		super(value);
		setBrand(brandValue);
		setYearOfManufacture(yearOfManufactureValue);
		setInches(inchesValue);
		setProcessorSpeed(processorSpeedValue);
	}

	public Laptop(String brandValue, int yearOfManufactureValue, double value){
		super(value);
		setBrand(brandValue);
		setYearOfManufacture(yearOfManufactureValue);
	}

	public Laptop(String brandValue, int yearOfManufactureValue){
		super();
		setBrand(brandValue);
		setYearOfManufacture(yearOfManufactureValue);
	}

	public void setYearOfManufacture(int value){
		yearOfManufacture = value;
	}
	public int getYearOfManufacture(){
		return yearOfManufacture;
	}

	public void setInches(double value){
		inches = value;
	}
	public double getInches(){
		return inches;
	}

	public void setProcessorSpeed(double value){
		processorSpeed = value;
	}
	public double getProcessorSpeed(){
		return processorSpeed;
	}

}