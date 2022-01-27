package lesson14.HomeWork14.ComplaintFreeWorld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class FreeWorldClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(FreeWorldClient.class.getResource("/ComplaintFreeWorld.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("ComplaintFreeWorld");
       stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

