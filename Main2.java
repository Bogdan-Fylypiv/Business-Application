import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

class Main2 extends JFrame{
	File file;
	PrintStream filePrint;
	FileReader fileRead;
	BufferedReader fileBufferedReader;
	JButton addEmployee;
	JButton addProduct;
	JButton exitButton;
	JButton fireAnEmployee;
	JPanel mainPanel;
	ArrayList<Person> people = new ArrayList<>();
	ArrayList<Product> product = new ArrayList<>();
	TextArea orderList;
	int numOfUSB = 0, numOfLaptops = 0, numOfCameras = 0, peopleCounter = 0, productCounter = 0;
	int fileCounter = 1;

	public Main2() throws FileNotFoundException{
		super("Buttons");
		setSize(520,200);
		setLayout(new FlowLayout());
		buildPanel0();
		add(mainPanel);
		setVisible(true);
		file = new File("database0.txt");
		filePrint = new PrintStream(file);
		fileRead = new FileReader(file);
		fileBufferedReader = new BufferedReader(fileRead);
	}

	public void buildPanel0(){
		addEmployee = new JButton("Add new employee");
		addProduct = new JButton("Add new product");
		exitButton = new JButton("Exit");
		fireAnEmployee = new JButton("Fire an employee");
		orderList = new TextArea(4, 30);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(addProduct, BorderLayout.NORTH);
		mainPanel.add(exitButton, BorderLayout.SOUTH);
		mainPanel.add(addEmployee, BorderLayout.WEST);
		mainPanel.add(fireAnEmployee, BorderLayout.EAST);
		mainPanel.add(orderList, BorderLayout.CENTER);
		addEmployee.addActionListener(new EventHandler());
		addProduct.addActionListener(new EventHandler());
		exitButton.addActionListener(new EventHandler());
		fireAnEmployee.addActionListener(new EventHandler());
		mainPanel.setVisible(true);
	}

	private class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equalsIgnoreCase("Add new employee")) {
			addNewEmployee();
		}
		else if (e.getActionCommand().equalsIgnoreCase("Add new product")) {
			addNewProduct();
		}
		else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				System.exit(0);
			}
		}
		else if (e.getActionCommand().equalsIgnoreCase("Fire an employee")) {
			try{
				if (people.size()==0) {
					throw new StringIndexOutOfBoundsException();
				}
				String initials = JOptionPane.showInputDialog("Please give employee's full name");
				fireAnEmployee(initials);
			}
			catch (NoEmployeeFoundException exc) {
				JOptionPane.showMessageDialog(null, exc.getMessage());
			}
			catch (StringIndexOutOfBoundsException exc) {
				JOptionPane.showMessageDialog(null, "Employee list is empty");
			}
		}
	}
	}

	public static void main(String[] args) throws FileNotFoundException{
		new Main2();
	}

	public void updateDatabase(String command, String firstName){
		if (command.equalsIgnoreCase("Add employee")) {
			filePrint.println("Employee " + people.get(peopleCounter).getFirstName() + " " + people.get(peopleCounter).getSecondName());
		}
		else if (command.equalsIgnoreCase("Add product")){
			String productName = "Product";
			if (product.get(productCounter) instanceof Laptop) {
				productName = "Laptop";
			}
			else if (product.get(productCounter) instanceof USB) {
				productName = "USB";
			}
			else if (product.get(productCounter) instanceof Camera) {
				productName = "Camera";
			}
			filePrint.println(productName + " " + product.get(productCounter).getBrand());
		}
		else if (command.equalsIgnoreCase("Fire an employee")) {
			File file1 = file;
			PrintStream filePrint1 = filePrint;
			FileReader fileRead1 = fileRead;
			BufferedReader fileBufferedReader1 = fileBufferedReader;
			String fileName = "database" + Integer.toString(fileCounter) + ".txt";
			try{
				file1 = new File(fileName);
				file1.createNewFile();
				filePrint1 = new PrintStream(file1);
				fileRead1 = new FileReader(file1);
				fileBufferedReader1 = new BufferedReader(fileRead1);
				String line;
				String[] lineArray;
	 			while(true) {
	 				//printLine("reading line");
	 				line = fileBufferedReader.readLine();
	 				//printLine(line);
	 				try{
	 					lineArray = line.split(" ");
	 				}
	 				catch(NullPointerException e){
	 					//printLine("NullPointerException encountered");
	 					break;
	 				}
					if (!lineArray[1].equalsIgnoreCase(firstName)) {
						//printLine("adding " + line);
						filePrint1.println(line);
					}
				}
			}
			catch(FileNotFoundException e){}
			catch(IOException e1){}
			finally{
				File tempFile = file;
				file = file1;
				filePrint = filePrint1;
				fileRead = fileRead1;
				fileBufferedReader = fileBufferedReader1;
				fileCounter++;
				tempFile.delete();
			}
			//printLine("finished");
		}
	}

	public void addNewEmployee(){
		String firstName;
		String secondName;
		do{
			firstName = JOptionPane.showInputDialog("Enter first name: ");
		}
		while (firstName.equals(""));
		do{
			secondName = JOptionPane.showInputDialog("Enter second name: ");
		}
		while (secondName.equals(""));

		boolean employeeAlreadyAdded = false;
		for (int i=0; i<people.size(); i++) {
			if (firstName.equalsIgnoreCase(people.get(i).getFirstName()) && secondName.equalsIgnoreCase(people.get(i).getSecondName())) {
				employeeAlreadyAdded = true;
				break;
			}
		}
		if (!employeeAlreadyAdded || (employeeAlreadyAdded && JOptionPane.showConfirmDialog(null, "Employee is already on the list, do you still want to add them?")==0)) {
			people.add(new Employee(firstName, secondName));
			JOptionPane.showMessageDialog(null, "Employee " + people.get(peopleCounter).getFirstName() + " " + people.get(peopleCounter).getSecondName() + " was added!");
		}
		else{
			return;
		}
		sortPeople();
		File file1 = file;
		PrintStream filePrint1 = filePrint;
		FileReader fileRead1 = fileRead;
		BufferedReader fileBufferedReader1 = fileBufferedReader;
		String fileName = "database" + Integer.toString(fileCounter) + ".txt";
		try{
			file1 = new File(fileName);
			file1.createNewFile();
			filePrint1 = new PrintStream(file1);
			fileRead1 = new FileReader(file1);
			fileBufferedReader1 = new BufferedReader(fileRead1);
			String line;
	 		for(int i=0; i<people.size(); i++) {
	 			//printLine("reading line");
	 			line = "Employee " + people.get(i).getFirstName() + " " + people.get(i).getSecondName();
				//printLine(line);
				//printLine("adding " + line);
				filePrint1.println(line);
			}
			String productName = "Product";
			for(int i=0; i<product.size(); i++) {
				if (product.get(i) instanceof Laptop) {
					productName = "Laptop";
				}
				else if (product.get(i) instanceof USB) {
					productName = "USB";
				}
				else if (product.get(i) instanceof Camera) {
					productName = "Camera";
				}
	 			//printLine("reading line");
	 			line = productName + " " + product.get(i).getBrand();
				//printLine(line);
				//printLine("adding " + line);
				filePrint1.println(line);
			}
		}
		catch(FileNotFoundException e){}
		catch(IOException e1){}
		finally{
			File tempFile = file;
			file = file1;
			filePrint = filePrint1;
			fileRead = fileRead1;
			fileBufferedReader = fileBufferedReader1;
			fileCounter++;
			tempFile.delete();
		}
		//printLine("finished");
		updateTextField();
		//updateDatabase("Add employee", "");
		peopleCounter++;
	}

	public void fireAnEmployee(String initials) throws NoEmployeeFoundException, StringIndexOutOfBoundsException{
		boolean employeeRemoved = false;
		if (initials.indexOf(" ") == -1) {
			String firstName0;
			String secondName0;
			int i=0;
			while(i<(initials.length()+1)){
				firstName0 = initials.substring(0,i);
				for (int j=0; j<people.size(); j++) {
					if (firstName0.equalsIgnoreCase(people.get(j).getFirstName())) {
						if (initials.substring(i, initials.length()).equalsIgnoreCase(people.get(j).getSecondName())) {
							secondName0 = people.get(j).getSecondName();
							people.remove(j);
							updateDatabase("Fire an employee", firstName0);
							peopleCounter--;
							JOptionPane.showMessageDialog(null, "Employee " + firstName0 + " " + secondName0 + " was fired");
							updateTextField();
							return;
						}
						else {
							throw new NoEmployeeFoundException();
						}
					}
					if (firstName0.equalsIgnoreCase(people.get(j).getSecondName())) {
						secondName0 = firstName0;
						if (initials.substring(i, initials.length()).equalsIgnoreCase(people.get(j).getFirstName())) {
							firstName0 = people.get(j).getFirstName();
							people.remove(j);
							updateDatabase("Fire an employee", firstName0);
							peopleCounter--;
							JOptionPane.showMessageDialog(null, "Employee " + firstName0 + " " + secondName0 + " was fired");
							updateTextField();
							return;
						}
						else {
							throw new NoEmployeeFoundException();
						}
					}
				}
				i++;
			}		
		}
		else{
			String firstName = initials.substring(0, initials.indexOf(" "));
			String secondName = initials.substring(initials.indexOf(" ")+1, initials.length());
			//printLine(firstName + " " + secondName);
			for(int i=0; i<people.size(); i++){
				//printLine(people.get(i).getFirstName() + " " + people.get(i).getSecondName());
				if(people.get(i).getFirstName().equalsIgnoreCase(firstName) && people.get(i).getSecondName().equalsIgnoreCase(secondName)){
					//printLine("removing employee");
					people.remove(i);
					updateDatabase("Fire an employee", firstName);
					peopleCounter--;
					JOptionPane.showMessageDialog(null, "Employee " + firstName + " " + secondName + " was fired");
					updateTextField();
					return;
				}
			}
			if (!employeeRemoved) {
				throw new NoEmployeeFoundException();
			}
		}
	}

	public void addNewProduct(){
		String productType = JOptionPane.showInputDialog("\nEnter product type: ");
			if (productType.equalsIgnoreCase("usb")) {
				product.add(new USB(JOptionPane.showInputDialog("Enter brand: "), JOptionPane.showInputDialog("Enter type: ")));
				numOfUSB++;
			}
			else if (productType.equalsIgnoreCase("laptop")) {
				product.add(new Laptop(JOptionPane.showInputDialog("Enter brand: "), Integer.parseInt(JOptionPane.showInputDialog("Enter year of manufacture: "))));
				numOfLaptops++;
			}
			else if (productType.equalsIgnoreCase("camera")) {
				product.add(new Camera(JOptionPane.showInputDialog("Enter brand: "), Double.parseDouble(JOptionPane.showInputDialog("Enter value: "))));
				numOfCameras++;
			}
			else{
				JOptionPane.showMessageDialog(null, "No such device");
				return;
			}
			updateDatabase("Add product", "");
			updateTextField();
			productCounter++;
	}

	public void updateTextField(){
		String employeeList = "";
		for(int i=0; i<people.size(); i++)
			employeeList += people.get(i).getFirstName() + " " + people.get(i).getSecondName() + "\n";
		orderList.setText("Order list:" + 
						"\nNumber of USB flash drives : " + numOfUSB +
						"\nNumber of Laptops : " + numOfLaptops +
						"\nNumber of Cameras : " + numOfCameras + 
						"\nEmployees: \n" + employeeList);
	}

	public void sortPeople(){
		Person temp = new Employee();
		for (int i = 0; i < people.size(); i++) 
        {
            for (int j = i + 1; j < people.size(); j++) 
            {
                if (people.get(i).getFirstName().compareTo(people.get(j).getFirstName())>0) 
                {
                    temp = people.get(i);
                    people.set(i, people.get(j));
                    people.set(j, temp);
                }
            }
        }
	}
}