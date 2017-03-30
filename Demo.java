import javafx.application.Application;
import javafx.stage.Stage;

 
public class Demo extends Application {
 
    @Override
    public void start(Stage stage) throws Exception {
        StudentView window = new StudentView(stage);
        //DateMenu date = new DateMenu(stage);
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}