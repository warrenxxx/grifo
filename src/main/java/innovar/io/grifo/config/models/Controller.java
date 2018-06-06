/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.config.models;
import innovar.io.grifo.dto.RequestMovementDetailDto;
import innovar.io.grifo.entity.MovementDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML public TableColumn cCodigo;
    @FXML public TableColumn cCantidad;
    @FXML public TableColumn cDescription;
    @FXML public TextField lblNombre;
    @FXML public TextField lblRuc;
    @FXML public TextField lblDireccion;
    @FXML public TableView tbTable;
    @FXML public TableColumn cPrecioUnitario;
    @FXML public TableColumn cPrecioTotal;
    @FXML public TextField lblTotal;
    @FXML public DatePicker dtpFecha;

    public Controller setLblNombre(String lblNombre) {

        this.lblNombre.setText(lblNombre);
        return this;
    }

    public Controller setLblRuc(String lblRuc) {
        this.lblRuc.setText(lblRuc);
        return this;
    }

    public Controller setLblDireccion(String lblDireccion) {
        this.lblDireccion.setText(lblDireccion);
        return this;
    }

    public Controller setTbTable(TableView tbTable) {
        this.tbTable = tbTable;
        return this;
    }



    public Controller setLblTotal(TextField lblTotal) {
        this.lblTotal = lblTotal;
        return this;
    }

    public Controller setDtpFecha(LocalDate dtpFecha) {
        this.dtpFecha.setValue(dtpFecha);
        return this;
    }

    ObservableList<Detail> observableList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lblNombre.setText("paulina pinelina");
        this.cCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.cCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.cDescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.cPrecioTotal.setCellValueFactory(new PropertyValueFactory("precioTotal"));
        this.cPrecioUnitario.setCellValueFactory(new PropertyValueFactory("precioUnitario"));
        this.tbTable.setItems(observableList);
    }
    @Autowired
    ReactiveMongoOperations operations;
    public Controller addAllItems(MovementDetail... details){
        this.observableList.clear();
        for(MovementDetail x:details){
            this.observableList.add(
                    new Detail(
                            "codig",
                            String.valueOf(x.getQuantityGal()),
                            "description",
                            String.valueOf("455"),
                            String.valueOf(x.getQuantitySol())
                    )
            );
        }
        return this;
    }
}
