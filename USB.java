class USB extends Product {
	String brand;
	String type;
	double writeSpeed;
	double readSpeed;

	public USB(String brandValue, String typeValue){
		setBrand(brandValue);
		setType(typeValue);
	}

	public USB(double valueValue, boolean inStockValue, String brandValue, String typeValue){
		super(valueValue, inStockValue);
		setInStock(inStock);
	}

	@Override
	public void setValue(double valueValue){
		if (valueValue>=0 && valueValue<=20) {
			value = valueValue;
		}
		else{
			//throw new PriceException("Wrong price");
		}
	}

	public void setType(String typeValue){
		type = typeValue;
	}
	public String getType(){
		return type;
	}

	public void setWriteSpeed(double value){
		writeSpeed = value;
	}
	public double getWriteSpeed(){
		return writeSpeed;
	}

	public void setReadSpeed(double value){
		readSpeed = value;
	}
	public double getReadSpeed(){
		return readSpeed;
	}
}