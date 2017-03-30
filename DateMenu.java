import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DateMenu
{
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
	
	  private ComboBox dates;
	  private ComboBox month;
	  private ComboBox years;

	
	public DateMenu(Stage stage) {
		
		  month = new ComboBox();
		
		  month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
		  
		  dates = new ComboBox();
		  years = new ComboBox();
		 
		  menuHBox = new HBox(10);
		  for(int i = 1; i<=31; i++)
		  {
			  dates.getItems().addAll(i);
		  }
		  for(int j = 1920; j<=2017;j++)
		  {
			  years.getItems().addAll(j);
		  }
		  
		  menuHBox.getChildren().addAll(month,dates,years);
		  
		  stage.setScene(new Scene(menuHBox));
		  stage.show();
		 
	}
	


}
