package View;

import com.sun.glass.ui.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class BruteForceWindow {
    private TextField encryptBenchmarkField;
    private TextField decryptBenchmarkField;
    private VBox rootLayout = new VBox(50);

    public void newWindow() {

        Stage window = new Stage();
        initializationRootLayout(window);
        Scene scene = new Scene(rootLayout);
        window.initModality(Modality.APPLICATION_MODAL);

        window.setScene(scene);
        window.showAndWait();
    }

    private void initializationRootLayout(Stage stage) {
        rootLayout.getChildren().addAll(createInfoBox(),createBenchmarkBox(),createButtonBox(stage));
    }

    private HBox createInfoBox() {
        Text text = new Text();
        String description = "Firstly you need input benchmark encrypt and decrypt text for example: ljsjwfytw it is generator when key = 5";
        text.setText(description);
        return new HBox(text);
    }

    private HBox createBenchmarkBox() {
        HBox benchmarkBox = new HBox();
        Text encryptBenchmark = new Text("encrypt benchmark");
        Text decryptBenchmark = new Text("decrypt benchmarh");
        TextField encryptBenchmarkField = new TextField();
        this.encryptBenchmarkField = encryptBenchmarkField;

        TextField decryptBenchmarkField = new TextField();
        this.decryptBenchmarkField = decryptBenchmarkField;

        benchmarkBox.getChildren().addAll(encryptBenchmark,encryptBenchmarkField,decryptBenchmark,decryptBenchmarkField);
        return benchmarkBox;
    }

    private HBox createButtonBox(Stage stage) {
        HBox buttonBox = new HBox();
        Button submitButton = createSubmitButton(stage);
        Button cancelButton = createCancelButton(stage);
        buttonBox.getChildren().addAll(submitButton,cancelButton);
        return buttonBox;
    }
    private Button createSubmitButton(Stage stage)
    {
        Button submitButton = new Button("OK");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainApplication.BENCHMARK_ENCRYPT_TEXT = encryptBenchmarkField.getText();
                MainApplication.BENCHMARK_DECRYPT_TEXT = decryptBenchmarkField.getText();
                stage.close();
            }
        });
        return submitButton;
    }

    private Button createCancelButton(Stage stage)
    {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        return cancelButton;
    }

    public TextField getEncryptBenchmarkField() {
        return encryptBenchmarkField;
    }

    public TextField getDecryptBenchmarkField() {
        return decryptBenchmarkField;
    }
}
