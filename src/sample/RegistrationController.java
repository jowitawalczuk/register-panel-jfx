package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {


    private String excelFilePath = "@../../Register.xlsx";

    @FXML
    private ImageView brandingImageView;
    @FXML
    private Label wrongData;
    @FXML
    private Label wrongData1;
    @FXML
    private Label wrongData2;
    @FXML
    private Label wrongData3;
    @FXML
    private Label wrongData4;
    @FXML
    private Label wrongData5;
    @FXML
    private Label wrongData6;
    @FXML
    private Label wrongData7;
    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private Button register;
    @FXML
    private Button close;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup group;
    @FXML
    private CheckBox terms;
    @FXML
    private DatePicker data;

    String sex;

    public void registerOnAction(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == register) {

            //entered data alerts initialization
            wrongData.setText(" ");
            wrongData1.setText(" ");
            wrongData2.setText(" ");
            wrongData3.setText(" ");
            wrongData4.setText(" ");
            wrongData5.setText(" ");
            wrongData6.setText(" ");
            wrongData7.setText(" ");
            registration();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image brandingImage = new Image(new File("@../../gif1.gif").toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    public void registration() throws IOException {

        String pas1 = String.valueOf(passwordField1.getText());
        String pas2 = String.valueOf(passwordField2.getText());

        male.setToggleGroup(group);
        female.setToggleGroup(group);

        if (female.isSelected()) {
            sex = "female";
        }
        if (male.isSelected()) {
            sex = "male";
        }
        //entered data check
        if (passwordField1.getText().isEmpty() || passwordField2.getText().isEmpty() || username.getText().isEmpty() || name.getText().isEmpty() || email.getText().isEmpty() || surname.getText().isEmpty() || terms.isSelected() != true) {
            if (username.getText().isEmpty()) {
                wrongData1.setText("Invalid username");
            }
            if (username.getText().length()<6) {
                wrongData1.setText("Username must contain at least 6 characters");
            }
            if (name.getText().isEmpty()) {
                wrongData2.setText("Obligatory field");
            }
            if (surname.getText().isEmpty()) {
                wrongData3.setText("Obligatory field");
            }
            if (email.getText().isEmpty()) {
                wrongData4.setText("Invalid email");
            }
            if (passwordField1.getText().length() < 6) {
                wrongData5.setText("Password must contain at least 6 characters");
            }
            if (!pas1.equals(pas2)) {
                wrongData6.setText("Entered passwords does not match");
            }
            if (terms.isSelected() != true) {

                wrongData.setText("---> !!!");
                wrongData1.setText("Accept the terms of the agreement");
            } else if (group.getSelectedToggle() == null) {
                wrongData.setText("");
                wrongData1.setText("");
                wrongData2.setText("");
                wrongData3.setText("");
                wrongData4.setText("");
                wrongData5.setText("");
                wrongData6.setText("");
                wrongData7.setText("Choose sex");
            }
        }

        //registration terms satisfied
        else {

            save(username.getText(), name.getText(), surname.getText(), email.getText(), pas1, sex);
            wrongData1.setText("Successful registration !");
            System.out.println(" Successful registration !");
            //closing registration panel after successful registration
            Stage scene = (Stage) close.getScene().getWindow();
            scene.close();
        }
    }

    //close button
    public void closeOnAction(ActionEvent event) {
        Stage scene = (Stage) close.getScene().getWindow();
        scene.close();
    }


    //saving user data to excel sheet
    private void save(String user_name, String name, String surname, String email, String password, String sex) throws IOException {

        //saving successfully registered users
        FileInputStream inputted = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputted);
        XSSFSheet sheet = workbook.getSheetAt(0);
        User user = new User(user_name, name, surname, email, password, sex);
        ArrayList<User[]> users = new ArrayList<User[]>();
        users.add(new User[]{user});

        for (User[] us : users) {
            int rownum = sheet.getLastRowNum() + 1;
            System.out.println("User entered data in given sheet with row number:" + rownum);

            XSSFRow row = sheet.createRow(rownum++);
            int cells = 0;
            XSSFCell cell = row.createCell(cells++);
            cell.setCellValue(user_name);
            XSSFCell cell1 = row.createCell(cells++);
            cell1.setCellValue(name);
            XSSFCell cell2 = row.createCell(cells++);
            cell2.setCellValue(surname);
            XSSFCell cell3 = row.createCell(cells++);
            cell3.setCellValue(email);
            XSSFCell cell4 = row.createCell(cells++);
            cell4.setCellValue(password);
            XSSFCell cell6 = row.createCell(cells++);
            cell6.setCellValue(String.valueOf(data.getValue()));
            XSSFCell cell8 = row.createCell(cells++);
            cell8.setCellValue(sex);
            try (
                    FileOutputStream outputted = new FileOutputStream(excelFilePath)) {
                workbook.write(outputted);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File on given filepath : ' " + excelFilePath + " ' was not found.");
            }
        }
    }

    class User {
        private String username;
        private String name;
        private String surname;
        private String email;
        private String passwordField1;
        private String sex;

        public User(String username, String name, String surname, String email, String passwordField1, String sex) {
            this.username = username;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.passwordField1 = passwordField1;
            this.sex = sex;
        }
    }
}