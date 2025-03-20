package com.nichi.front;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;


public class HelloController implements Initializable {
    @FXML
    private TableView<Companydto> tableview;
    @FXML
    private TableColumn<Companydto, String> CompanyIDColumn;
    @FXML
    private TableColumn<Companydto, String> BoothNOColumn;
    @FXML
    private TableColumn<Companydto, String> NameColumn;
    @FXML
    private TableColumn<Companydto, String> CountryoforiginColumn;
    @FXML
    private TableColumn<Companydto, String> categoryColumn;
    @FXML
    private TableColumn<Companydto, String> Email;
    @FXML
    private TableColumn<Companydto, String> PhoneNoColumn;
    @FXML
    private TableColumn<Companydto, String> WebsiteColumn;
    @FXML
    private TableColumn<Companydto, String> AddressColumn;
    @FXML
    private Button importBtn;
    @FXML
    private Button japanBtn;
    @FXML
    private Button englishBtn;
    @FXML
    private Button exportBtn;

    private final ObservableList<Companydto> companyData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        setupContextMenu();

    }

    private void setupTableColumns() {
        CompanyIDColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCompanyID()));
        BoothNOColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBoothNO()));
        NameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        CountryoforiginColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCountryoforigin()));
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));
        Email.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        PhoneNoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPhoneNo()));
        WebsiteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getWebsite()));
        AddressColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress()));
    }

    @FXML
    private void importData() {
        loadTableDataForDate();
    }


    public void loadTableDataForDate() {
        System.out.println("Inside method to load data");
        try (Connection con = Database.getConnection();
             PreparedStatement stm = con.prepareStatement("SELECT * FROM company")) {

            ResultSet rs = stm.executeQuery();
            companyData.clear();

            while (rs.next()) {
                Companydto comp = new Companydto(
                        rs.getString("company_id"),
                        rs.getString("booth_no"),
                        rs.getString("name"),
                        rs.getString("country_of_origin"),
                        rs.getString("category"),
                        rs.getString("email"),
                        rs.getString("phone_no"),
                        rs.getString("website"),
                        rs.getString("address"),
                        rs.getString("sheet_type")
                );
                companyData.add(comp);
            }

            tableview.setItems(companyData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void japanData() {
        System.out.println("Inside method to load data");
        try (Connection con = Database.getConnection();
             PreparedStatement stm = con.prepareStatement("SELECT * FROM company where sheet_type = 'Jpn'")) {

            ResultSet rs = stm.executeQuery();
            companyData.clear();

            while (rs.next()) {
                Companydto comp = new Companydto(
                        rs.getString("company_id"),
                        rs.getString("booth_no"),
                        rs.getString("name"),
                        rs.getString("country_of_origin"),
                        rs.getString("category"),
                        rs.getString("email"),
                        rs.getString("phone_no"),
                        rs.getString("website"),
                        rs.getString("address"),
                        rs.getString("sheet_type")
                );
                companyData.add(comp);
            }

            tableview.setItems(companyData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void englishData() {
        System.out.println("Inside method to load data");
        try (Connection con = Database.getConnection();
             PreparedStatement stm = con.prepareStatement("SELECT * FROM company where sheet_type = 'Eng'")) {

            ResultSet rs = stm.executeQuery();
            companyData.clear();

            while (rs.next()) {
                Companydto comp = new Companydto(
                        rs.getString("company_id"),
                        rs.getString("booth_no"),
                        rs.getString("name"),
                        rs.getString("country_of_origin"),
                        rs.getString("category"),
                        rs.getString("email"),
                        rs.getString("phone_no"),
                        rs.getString("website"),
                        rs.getString("address"),
                        rs.getString("sheet_type")
                );
                companyData.add(comp);
            }

            tableview.setItems(companyData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void exportToExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        fileChooser.setTitle("Save Excel File");


        File file = fileChooser.showSaveDialog(exportBtn.getScene().getWindow());

        if (file != null) {
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {

                createSheet(workbook, "Japan Data", "Jpn");
                createSheet(workbook, "English Data", "Eng");


                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    workbook.write(fileOut);
                    System.out.println("Export successful: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Error exporting data: " + e.getMessage());
            }
        }
    }

    private void createSheet(XSSFWorkbook workbook, String sheetName, String sheetType) {
        Sheet sheet = workbook.createSheet(sheetName);
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Company ID", "Booth No", "Name", "Country", "Category", "Email", "Phone No", "Website", "Address"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (Companydto comp : companyData) {
            if (comp.getSheetNumber().equalsIgnoreCase(sheetType)) {  // Filter by type
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(comp.getCompanyID());
                row.createCell(1).setCellValue(comp.getBoothNO());
                row.createCell(2).setCellValue(comp.getName());
                row.createCell(3).setCellValue(comp.getCountryoforigin());
                row.createCell(4).setCellValue(comp.getCategory());
                row.createCell(5).setCellValue(comp.getEmail());
                row.createCell(6).setCellValue(comp.getPhoneNo());
                row.createCell(7).setCellValue(comp.getWebsite());
                row.createCell(8).setCellValue(comp.getAddress());
            }
        }
    }

    private void setupContextMenu() {
        MenuItem addItem = new MenuItem("Add");
        MenuItem updateItem = new MenuItem("Update");
        MenuItem deleteItem = new MenuItem("Delete");

        addItem.setOnAction(event -> adddata());
        updateItem.setOnAction(event -> updatedata());
        deleteItem.setOnAction(event -> deleteStock());

        ContextMenu contextMenu = new ContextMenu(addItem, updateItem, deleteItem);

        tableview.setRowFactory(tv -> {
            TableRow<Companydto> row = new TableRow<>();
            row.setOnContextMenuRequested(event -> {
                if (!row.isEmpty()) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
    }


    private void adddata() {
        Companydto newCompany = AddCompanyDialog.showAddDialog(
                (Stage) tableview.getScene().getWindow()
        );
        if (newCompany != null) {

            String query = "INSERT INTO company (company_id, booth_no, name, country_of_origin, category, email, phone_no, website, address, sheet_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newCompany.getCompanyID());
                stmt.setString(2, newCompany.getBoothNO());
                stmt.setString(3, newCompany.getName());
                stmt.setString(4, newCompany.getCountryoforigin());
                stmt.setString(5, newCompany.getCategory());
                stmt.setString(6, newCompany.getEmail());
                stmt.setString(7, newCompany.getPhoneNo());
                stmt.setString(8, newCompany.getWebsite());
                stmt.setString(9, newCompany.getAddress());
                stmt.setString(10, newCompany.getSheetNumber()); // assuming 'Jpn' or 'Eng' as a value

                int result = stmt.executeUpdate();
                if (result > 0) {
                    loadTableDataForDate(); // Reload table
                }
            } catch (Exception e) {
                System.out.println("Error adding data: " + e.getMessage());
            }
        }
    }

    private void updatedata() {
        Companydto selectedCompany = tableview.getSelectionModel().getSelectedItem();
        if (selectedCompany != null) {

            Companydto updatedCompany = UpdateCompanyDialog.showUpdateDialog((Stage)
            tableview.getScene().getWindow(),selectedCompany);
            if (updatedCompany != null) {
                String query = "UPDATE company SET booth_no = ?, name = ?, country_of_origin = ?, category = ?, email = ?, phone_no = ?, website = ?, address = ?, sheet_type = ? WHERE company_id = ?";
                try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, updatedCompany.getBoothNO());
                    stmt.setString(2, updatedCompany.getName());
                    stmt.setString(3, updatedCompany.getCountryoforigin());
                    stmt.setString(4, updatedCompany.getCategory());
                    stmt.setString(5, updatedCompany.getEmail());
                    stmt.setString(6, updatedCompany.getPhoneNo());
                    stmt.setString(7, updatedCompany.getWebsite());
                    stmt.setString(8, updatedCompany.getAddress());
                    stmt.setString(9, updatedCompany.getSheetNumber());
                    stmt.setString(10, updatedCompany.getCompanyID());

                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        loadTableDataForDate();
                    }
                } catch (Exception e) {
                    System.out.println("Error updating data: " + e.getMessage());
                }
            }
        } else {
            showAlert("Please select a company to update.");
        }
    }

    private void deleteStock() {
        Companydto selectedCompany = tableview.getSelectionModel().getSelectedItem();
        if (selectedCompany != null) {
            String query = "DELETE FROM company WHERE company_id = ?";
            try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, selectedCompany.getCompanyID());

                int result = stmt.executeUpdate();
                if (result > 0) {
                    companyData.remove(selectedCompany);
                }
            } catch (Exception e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
        } else {
            showAlert("Please select a company to delete.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


