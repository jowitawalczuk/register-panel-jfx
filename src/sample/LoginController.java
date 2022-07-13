package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button logIn;
    @FXML
    private Hyperlink registerHyperlink;
    @FXML
    private Label wrongData;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;

    private String nameCell;
    private String passwordCell;


    public void closeOnAction(ActionEvent event){
        Stage scene=(Stage) close.getScene().getWindow();
        scene.close();
        Platform.exit();

    }
    public void registerLink(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RegistrationSample.fxml"));
            Stage scene = new Stage();
            scene.initStyle(StageStyle.UNDECORATED);
            scene.setScene(new Scene(root, 500, 812));
            scene.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    private void read(String user_name,String password) throws IOException {
        String excelFilePath = "@../../Register.xlsx";
        FileInputStream inputted = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputted);
        XSSFSheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter=new DataFormatter();
        XSSFRow row = sheet.getRow(4);
        XSSFCell cell = row.getCell(1);
        XSSFCell cell4 = row.getCell(5);
        nameCell=formatter.formatCellValue(cell);
        System.out.println(nameCell);
        passwordCell=formatter.formatCellValue(cell4);
        System.out.println(passwordCell);
        System.out.print(cell.getNumericCellValue());
        System.out.print(cell4.getNumericCellValue());
        System.out.print(cell.getStringCellValue());
        System.out.print(cell4.getStringCellValue());
   }

      public void logIn(ActionEvent event) throws IOException {
          if (event.getSource() == logIn) {

              if (!passwordField.getText().equals(passwordCell)) {
                  System.out.println(passwordField.getText());
                  System.out.println(nameCell);
                  wrongData.setText("Invalid password !");
              }
              if (username.getText()!=nameCell) {
                  wrongData.setText("Invalid username !");
                  System.out.println(username.getText());
                  System.out.println(nameCell);
              }
               else {
                  wrongData.setText("Logged in successfully :)");
              }
          }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image brandingImage = new Image(new File("@../../gif.gif").toURI().toString());
        brandingImageView.setImage(brandingImage);

    }



}
