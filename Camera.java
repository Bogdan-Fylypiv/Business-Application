class Camera extends Product {
	private double numOfMegaPixels;
	private int shutterSpeed;
	private double aperture;

	public Camera(String brandValue, double value){
		super(value);
		setBrand(brandValue);
	}

	@Override
	public void setValue(double valueValue){
		if (valueValue>=0 && valueValue<=1000) {
			value = valueValue;
		}
		else{
			//throw new PriceException("Wrong price");
		}
	}

	public void setNumOfMegaPixels(double value){
		numOfMegaPixels = value;
	}
	public double getNumOfMegaPixels(){
		return numOfMegaPixels;
	}

	public void setAperture(double value){
		aperture = value;
	}
	public double getAperture(){
		return aperture;
	}

	public void setShutterSpeed(int value){
		shutterSpeed = value;
	}
	public int getShutterSpeed(){
		return shutterSpeed;
	}
}