module com.example.adproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.example.adproyect to javafx.fxml;
    opens com.example.adproyect.Entidades;
    exports com.example.adproyect;
}