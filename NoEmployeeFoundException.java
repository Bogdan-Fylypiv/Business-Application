class NoEmployeeFoundException extends Exception{
	public NoEmployeeFoundException(String message){
		super(message);
	}

	public NoEmployeeFoundException(){
		super("No employee found");
	}
}