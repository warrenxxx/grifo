///*
// * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
// * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
// * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
// * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
// * Vestibulum commodo. Ut rhoncus gravida arcu.
// */
//
///**
// * The detail class is implemment to
// *
// * @version :1.0
// * @Author :Warren
// * @since :10/05/2018
// */
//package innovar.io.grifo.config.models;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
//public class Detail{
//    private StringProperty codigo;
//    private StringProperty cantidad;
//    private StringProperty description;
//    private StringProperty precioUnitario;
//    private StringProperty precioTotal;
//
//    public Detail(String codigo,String cantidad,String description,String precioUnitario,String precioTotal) {
//        this.codigo=new SimpleStringProperty(codigo);
//        this.cantidad = new SimpleStringProperty(cantidad);
//        this.description = new SimpleStringProperty(description);
//        this.precioUnitario = new SimpleStringProperty(precioUnitario);
//        this.precioTotal = new SimpleStringProperty(precioTotal);
//    }
//
//    public String getCodigo() {
//        return codigo.get();
//    }
//
//    public StringProperty codigoProperty() {
//        return codigo;
//    }
//
//    public void setCodigo(String codigo) {
//        this.codigo.set(codigo);
//    }
//
//    public String getCantidad() {
//        return cantidad.get();
//    }
//
//    public StringProperty cantidadProperty() {
//        return cantidad;
//    }
//
//    public void setCantidad(String cantidad) {
//        this.cantidad.set(cantidad);
//    }
//
//    public String getDescription() {
//        return description.get();
//    }
//
//    public StringProperty descriptionProperty() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description.set(description);
//    }
//
//    public String getPrecioUnitario() {
//        return precioUnitario.get();
//    }
//
//    public StringProperty precioUnitarioProperty() {
//        return precioUnitario;
//    }
//
//    public void setPrecioUnitario(String precioUnitario) {
//        this.precioUnitario.set(precioUnitario);
//    }
//
//    public String getPrecioTotal() {
//        return precioTotal.get();
//    }
//
//    public StringProperty precioTotalProperty() {
//        return precioTotal;
//    }
//
//    public void setPrecioTotal(String precioTotal) {
//        this.precioTotal.set(precioTotal);
//    }
//}
