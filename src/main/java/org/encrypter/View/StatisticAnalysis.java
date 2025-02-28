package org.encrypter.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StatisticAnalysis {
    private VBox rootLayout = new VBox(20);
    private Button selectFileButton;
    private Button deleteFileButton;
    private TextField filePathField;
    private TextArea inputDataArea;

    public void newWindow() {
        Stage window = new Stage();
        initializationRootLayout(window);
        Scene scene = new Scene(rootLayout);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.showAndWait();
    }

    private void initializationRootLayout(Stage stage) {
        rootLayout.setPadding(new Insets(20));
        rootLayout.getChildren().addAll(
                createFileSelectionBox(stage),
                createTextLabelsBox(),
                createDataAreaBox(),
                createActionButtonBox(stage)
        );
    }

    private Node createFileSelectionBox(Stage stage) {
        HBox fileSelectionBox = new HBox(10);

        Text filePath = createFilePathText();
        filePathField = createFilePathField();
        this.selectFileButton = createButton("Select");
        this.deleteFileButton = createButton("Delete");

        // Додаємо обробники подій для кнопок
        selectFileButton.setOnAction(e -> handleSelectFile(stage));
        deleteFileButton.setOnAction(e -> handleDeleteFile());

        fileSelectionBox.getChildren().addAll(filePath, filePathField, selectFileButton, deleteFileButton);
        fileSelectionBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fileSelectionBox.setAlignment(Pos.CENTER_LEFT);
        return fileSelectionBox;
    }

    private Text createFilePathText() {
        Text filePathText = new Text("File Path");
        Font font = new Font(25);
        filePathText.setFont(font);
        HBox.setMargin(filePathText, new Insets(0, 10, 0, 0));
        return filePathText;
    }

    private TextField createFilePathField() {
        TextField filePathField = new TextField();
        HBox.setMargin(filePathField, new Insets(0, 10, 0, 0));
        filePathField.setPrefSize(300, 30);
        Font font = new Font(15);
        filePathField.setFont(font);
        HBox.setHgrow(filePathField, Priority.ALWAYS);
        return filePathField;
    }

    private HBox createTextLabelsBox() {
        HBox textLabelsBox = new HBox(10);

        Label textDataLabel = new Label("File data");
        textDataLabel.setFont(new Font(25));
        textLabelsBox.getChildren().addAll(textDataLabel);
        textDataLabel.setAlignment(Pos.CENTER);
        textLabelsBox.setAlignment(Pos.CENTER);
        return textLabelsBox;
    }

    private HBox createDataAreaBox() {
        HBox dataEntryBox = new HBox(10);
        inputDataArea = createInputDataArea();
        dataEntryBox.getChildren().addAll(inputDataArea);
        HBox.setHgrow(inputDataArea, Priority.ALWAYS);
        return dataEntryBox;
    }

    private TextArea createInputDataArea() {
        TextArea inputDataArea = new TextArea();
        inputDataArea.setPrefRowCount(30);
        inputDataArea.setPrefColumnCount(50);
        inputDataArea.setWrapText(true);
        return inputDataArea;
    }

    private HBox createActionButtonBox(Stage stage) {
        HBox actionButtonBox = new HBox(10);
        Button submitButton = createButton("ok");
        Button cancelButton = createButton("cancel");

        // Додаємо обробники подій для кнопок
        submitButton.setOnAction(e -> handleSubmit());
        cancelButton.setOnAction(e -> stage.close());

        actionButtonBox.getChildren().addAll(submitButton, cancelButton);
        return actionButtonBox;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(button, Priority.ALWAYS);
        return button;
    }

    private void handleSelectFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                inputDataArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleDeleteFile() {
        filePathField.clear();
        inputDataArea.clear();
    }

    private void handleSubmit() {
        String filePath = filePathField.getText();
        MainApplication.dictionaryFile = new File(filePath);
    }
}
