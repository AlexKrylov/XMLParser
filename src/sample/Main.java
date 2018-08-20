package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private static String xmlPath = "C:\\xml_files\\";
    private static String outPath = "C:\\out\\";

    public static String getOutPath() {
        return outPath;
    }

    public static String getXmlPath() {
        return xmlPath;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("XML Parser");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        File xmlDirectory = new File(xmlPath);
        if (!xmlDirectory.exists()) xmlDirectory.mkdir();
        File outDirectory = new File(outPath);
        if (!outDirectory.exists()) outDirectory.mkdir();
        if (outDirectory.exists()) Writer.clearDirectory();
        launch(args);
    }
}
