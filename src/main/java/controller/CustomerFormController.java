package controller;

import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import repo.CustomerRepo;
import tm.Customertm;

import java.util.List;

public class  CustomerFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel;
    public TableView <Customertm> tblCustomers;
    public TableColumn tblID;
    public TableColumn tblName;
    public TableColumn tblAddress;
    public TableColumn tblTel;

    public void initialize() {
        tblID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        loadAllCustomer();


    }

    private void loadAllCustomer() {
        try {
            CustomerRepo customerRepository = new CustomerRepo();
            ObservableList<Customertm> obList = FXCollections.observableArrayList();
            List<Customer> cusList = customerRepository.getAll();

            for (Customer customer : cusList) {
                obList.add(new Customertm(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTel()
                ));
            }
            tblCustomers.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }


    }

    public void txtSearchOn_Action(ActionEvent actionEvent) {


    }

    public void save_INAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtTel.getText()));
        CustomerRepo customerRepo = new CustomerRepo();
        String Cid = customerRepo.saveCustomer(customer);
        System.out.println("Save Customer Id" + Cid);


    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void update_OnAction(ActionEvent actionEvent) {
    }
}
