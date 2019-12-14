class Product{
	String brand;
	double value;
	boolean inStock;

	public Product(double valueValue, boolean inStockValue){
		setValue(valueValue);
		setInStock(inStock);
	}

	public Product(double valueValue){
		setValue(valueValue);
	}

	public Product(){
	}

	public void setBrand(String brandValue){
		brand = brandValue;
	}
	public String getBrand(){
		return brand;
	}

	public void setValue(double valueValue){
		if (valueValue >=0) {
			value = valueValue;
		}
	}
	public double getValue(){
		return value;
	}

	public void setInStock(boolean inStockValue){
		inStock = inStockValue;
	}
	public boolean getInStock(){
		return inStock;
	}
}