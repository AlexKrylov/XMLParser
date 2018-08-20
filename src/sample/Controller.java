package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    private ComboBox<String> comboBoxTag;
    @FXML
    private ComboBox<String> comboBoxAttr;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button equals;
    @FXML
    private Button addFile;
    @FXML
    private Label txtLabel;
    @FXML
    private Button openXmlDir;
    @FXML
    private Button openOutDir;
    @FXML
    private ListView<String> listView;
    private static String selectedFilePath;

    public static String getSelectedFilePath() {
        return selectedFilePath;
    }

    @FXML
    void onClickButtonStartConverter() {
        converter();
    }

    @FXML
    void onClickAddTagAndAttr() {
        addListToComboBoxToList();
    }

    @FXML
    void onClickAddTxtFile() {
        chooseFile();
    }

    @FXML
    void onClickSearchEquals() {
        startEquals();
    }

    @FXML
    void onClickOpenOutDir() throws IOException {
        openOutLocation();
    }

    @FXML
    void onCLickOpenXmlDir() throws IOException {
        openXmlLocation();
    }

    private void addListToComboBoxToList() {
        File xmlDirectory = new File(Main.getXmlPath());
        File[] listFile = xmlDirectory.listFiles();
        if (listFile != null) {
            if (listFile.length > 0) {
                labelText("Ждите...");
                Thread thread = new Thread(() -> {
                    disableButton();
                    comboBoxAttr.setValue(comboBoxAttr.getPromptText());
                    comboBoxTag.setValue(comboBoxTag.getPromptText());
                    try {
                        updateView(listFile);
                        comboBoxTag.getItems().addAll(TagList.listTag(listFile[0]));
                        comboBoxAttr.getItems().addAll(AttrList.listAttr(listFile[0]));
                        enableButton();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
            labelText("Тэги и аттрибуты добавлены");
        } else labelText("Нет файлов в директории");
    }

    private void startEquals() {
        if (selectedFilePath != null) {
            labelText("Ждите...");
            new Thread(() -> {
                disableButton();
                try {
                    SearchEquals.searchEquals(comboBoxTag.getValue(), comboBoxAttr.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                enableButton();
                finishTask();
            }).start();
        } else if (comboBoxTag.getValue() != null && comboBoxAttr.getValue() != null && !comboBoxTag.getValue().equals("Tag") && !comboBoxAttr.getValue().equals("Attribute")) {
            labelText("Файл не добавлен");
        } else {
            labelText("Не выбран тэг или аттрибут");
        }
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        fileChooser.setTitle("Выберите файл");
        if (selectedFile != null) {
            selectedFilePath = selectedFile.getAbsolutePath();
            labelText("Выбран файл: " + selectedFile.getName());
        }
    }

    private void converter() {
        if (comboBoxTag.getValue() != null && comboBoxAttr.getValue() != null && !comboBoxTag.getValue().equals("Tag") && !comboBoxAttr.getValue().equals("Attribute")) {
            labelText("Ждите...");
            Thread thread = new Thread(() -> {
                disableButton();
                try {
                    ConvertXmlToTxt.parsing(comboBoxTag.getValue(), comboBoxAttr.getValue());
                    finishTask();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                enableButton();
                finishTask();
            });
            thread.start();
        } else {
            labelText("Не выбран тэг или аттрибут");
        }
    }

    private void openXmlLocation() throws IOException {
        Desktop.getDesktop().open(new File(Main.getXmlPath()));
    }

    private void openOutLocation() throws IOException {
        Desktop.getDesktop().open(new File(Main.getOutPath()));
    }

    private void labelText(String text) {
        txtLabel.setText(text);
        txtLabel.setFont(Font.font("Verdana", 20));
        txtLabel.setTextFill(Color.RED);
    }

    private void disableButton() {
        searchButton.setDisable(true);
        addButton.setDisable(true);
        equals.setDisable(true);
        addFile.setDisable(true);
        comboBoxAttr.setDisable(true);
        comboBoxTag.setDisable(true);
        openOutDir.setDisable(true);
        openXmlDir.setDisable(true);
    }

    private void enableButton() {
        searchButton.setDisable(false);
        addButton.setDisable(false);
        equals.setDisable(false);
        addFile.setDisable(false);
        comboBoxAttr.setDisable(false);
        comboBoxTag.setDisable(false);
        openOutDir.setDisable(false);
        openXmlDir.setDisable(false);
    }

    private void finishTask() {
        Platform.runLater(() -> {
            labelText("Готово!");
        });
    }

    private void updateView(File[] listFile) {
        Platform.runLater(() -> {
            try {
                comboBoxTag.getItems().clear();
                comboBoxAttr.getItems().clear();
                listView.getItems().clear();
                listView.getItems().addAll(ListTable.xmlView(listFile[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}