import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentView
{
			private Student student;
			private StudentDataStructure ds;
			private BorderPane wholePane;
			private GridPane mainPane;
			private HBox nameBox;
			private HBox addressBox;
			private HBox dobBox;
			private HBox phoneNumberBox;
			private HBox gradeLevelBox;
			private HBox idNumBox;
			private HBox buttonsPane;
			
			private VBox textVBox;
			private VBox labelsVBox;
			
			private Label name;
			private Label address;
			private Label dob; 
			private Label phoneNumber; 
			private Label gradeLevel;
			private Label idNum;
			
			private TextField nameText;
			private TextField addressText;
			private TextField dobText;
			private TextField phoneNumberText;
			private TextField idNumText;
			
			private Button save;
			private Button search;
			private Button delete;
			private Button clear;
			
			private HBox menuHBox;
	
			private MenuItem january;
			private MenuItem february;
			private MenuItem march;
		    private MenuItem april;
			private MenuItem may;
			private MenuItem june;
			private MenuItem july;
			private MenuItem august;
			private MenuItem september;
			private MenuItem october;
			private MenuItem november;
			private MenuItem december;
			
			private MenuBar topMenuBar;
			private Menu fileMenu;
			private MenuItem saveItem;
			private MenuItem exitItem;
			private MenuItem newItem;
			
			private ComboBox dates;
			private ComboBox month;
			private ComboBox years;
			
			private ComboBox gradesCB;
			  
			private Alert deleteAlert;
			private Alert insertAlert;
			private Alert fetchAlert;
			

		
		public StudentView(Stage stage)
		{
			student = new Student();
			ds = new StudentDataStructure();

			mainPane = new GridPane();
			wholePane = new BorderPane();
			name = new Label ("Name: ");
			address = new Label ("Address: ");
			dob = new Label ("Date of birth: ");
			phoneNumber = new Label ("Phone Number: ");
			gradeLevel = new Label ("Grade: ");
			idNum = new Label ("ID Number: ");
			
			nameText = new TextField();
			addressText = new TextField();
			dobText = new TextField();
			phoneNumberText = new TextField();
			idNumText = new TextField();
			
			nameBox = new HBox(13);
			addressBox = new HBox(14);
			dobBox = new HBox(13);
			phoneNumberBox = new HBox(5);
			gradeLevelBox = new HBox(13);
			idNumBox = new HBox(8);
			buttonsPane = new HBox(10);
			
			deleteAlert = new Alert(AlertType.CONFIRMATION);
			insertAlert = new Alert(AlertType.INFORMATION);
			fetchAlert = new Alert(AlertType.INFORMATION);
			  month = new ComboBox();
			  dates = new ComboBox();
			  years = new ComboBox();
			  gradesCB= new ComboBox();
			  
			  month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

			 
			  menuHBox = new HBox(10);
			  for(int i = 1; i<=31; i++)
			  {
				  dates.getItems().addAll(i);
			  }
			  for(int j = 1920; j<=2017;j++)
			  {
				  years.getItems().addAll(j);
			  }
			  
			  for(int k = 1; k<=12;k++)
			  {
				  gradesCB.getItems().addAll(k);
			  }
			menuHBox.getChildren().addAll(month,dates,years);
			
			textVBox = new VBox (10);
			labelsVBox = new VBox(20);
			
			save = new Button ("Save");
			search = new Button ("Search");
			delete = new Button ("Delete");
			clear = new Button ("Clear");
			
			topMenuBar = new MenuBar();
			fileMenu = new Menu("File");
			saveItem = new MenuItem("Save");
			newItem = new MenuItem ("New");
			exitItem = new MenuItem("Exit");
			
			fileMenu.getItems().addAll(saveItem,newItem,exitItem);
			topMenuBar.getMenus().addAll(fileMenu);
			save.setOnAction(event->{
				
				student.setName(nameText.getText());
				student.setAddress(addressText.getText());
				student.setDateOfBirth(dates.getSelectionModel().getSelectedItem().toString());
				student.setMonthOfBirth(month.getSelectionModel().getSelectedItem().toString());
				student.setYearOfBirth(years.getSelectionModel().getSelectedItem().toString());
				student.setPhoneNumber(phoneNumberText.getText());
				student.setGradeLevel(gradesCB.getSelectionModel().getSelectedItem().toString());
				student.setIdNum(idNumText.getText());
				
				ds.insert(student);
				if(ds.insert(student))
				  insertAlert.setTitle("Inserting Student!");
				  insertAlert.setHeaderText("Information Alert");
				  
				  insertAlert.setContentText("You inserted: " + nameText.getText()+ ".");
				  insertAlert.show();
			});
			
			search.setOnAction(event->{
				
				Student student = ds.fetch(nameText.getText());
				
				if(ds.fetch(nameText.getText())!=null)
				{
				try {
		             FileInputStream fis = null;
		             ObjectInputStream ois = null;
		        	 fis = new FileInputStream("Data.dat");
		             ois = new ObjectInputStream(fis);
		             student =(Student) ois.readObject();
		             ois.close();
		              
		         } catch (FileNotFoundException e) {
		             e.printStackTrace();
		         } catch (ClassNotFoundException e) {
		             e.printStackTrace();
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
				nameText.setText(student.getName());
				addressText.setText(student.getAddress()); 
				
				dates.setValue(student.getDateOfBirth());
				month.setValue(student.getMonthOfBirth());
				years.setValue(student.getYearOfBirth());

				phoneNumberText.setText(student.getPhoneNumber());
				gradesCB.setValue(student.getGradeLevel());
				idNumText.setText(student.getIdNum());
				}
				else
				{
				fetchAlert.setTitle("Student Not Found!");
				fetchAlert.setHeaderText("Student Not Found!");
				fetchAlert.setContentText(nameText.getText()+ " Could not be found.");
				fetchAlert.show();
				}
				
			});
			
			clear.setOnAction(event->{
				nameText.clear();
				addressText.clear();
				dates.setValue("");
				month.setValue("");
				years.setValue("");
				phoneNumberText.clear();
				gradesCB.setValue("");
				idNumText.clear();
			});
			delete.setOnAction(event-> {
				
				  deleteAlert.setTitle("Deleting Student!");
				  deleteAlert.setHeaderText("Confirmation Alert");
				  
				  deleteAlert.setContentText("You are about to delete: " + nameText.getText()+ ". Press OK to confirm");

				 Optional<ButtonType> result = deleteAlert.showAndWait();
				  
				  if (result.get() == ButtonType.OK) {

					  	ds.delete(nameText.getText());	
						nameText.clear();
						addressText.clear();
						dates.setValue("");
						month.setValue("");
						years.setValue("");
						phoneNumberText.clear();
						gradesCB.setValue("");
						idNumText.clear();
				  }			
				  else {}
			});
			saveItem.setOnAction(event->{
				
				student.setName(nameText.getText());
				student.setAddress(addressText.getText());
				student.setDateOfBirth(dates.getSelectionModel().getSelectedItem().toString());
				student.setMonthOfBirth(month.getSelectionModel().getSelectedItem().toString());
				student.setYearOfBirth(years.getSelectionModel().getSelectedItem().toString());
				student.setPhoneNumber(phoneNumberText.getText());
				student.setGradeLevel(gradesCB.getSelectionModel().getSelectedItem().toString());
				student.setIdNum(idNumText.getText());
				
				ds.insert(student);
				if(ds.insert(student))
				  insertAlert.setTitle("Inserting Student!");
				  insertAlert.setHeaderText("Information Alert");
				  
				  insertAlert.setContentText("You inserted: " + nameText.getText()+ ".");
				  insertAlert.show();
			});
			newItem.setOnAction(event->{
				nameText.clear();
				addressText.clear();
				dates.setValue("");
				month.setValue("");
				years.setValue("");
				phoneNumberText.clear();
				gradesCB.setValue("");
				idNumText.clear();
			});
			exitItem.setOnAction(event-> {
				System.exit(0);
			});
				
			labelsVBox.getChildren().addAll(name, address, dob, phoneNumber, gradeLevel, idNum);
			textVBox.getChildren().addAll(nameText, addressText, menuHBox, phoneNumberText, gradesCB, idNumText);
			buttonsPane.getChildren().addAll(save,search,delete,clear);
			
			stage.setTitle("Student DataBase");
			mainPane.add(labelsVBox,0,0);
			mainPane.add(textVBox,1,0);
			mainPane.add(buttonsPane, 1, 1);
			mainPane.setAlignment(Pos.CENTER);


			wholePane.setTop(topMenuBar);
			wholePane.setCenter(mainPane);
			stage.setScene(new Scene(wholePane,500,500));
			stage.show();
		}
		
		

}
