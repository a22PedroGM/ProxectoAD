package com.example.adproyect;

import com.example.adproyect.DAO.Hibernate.ClienteDAOHibernate;
import com.example.adproyect.DAO.Hibernate.ReservaDAOHibernate;
import com.example.adproyect.Entidades.Cliente;
import com.example.adproyect.Entidades.Reserva;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Optional;

public class InterfazReservasHotel extends Application {

    private TextField txtIdCliente;
    private TextField txtIdHabitacion;
    private TextField txtPrecio;
    private DatePicker dpFechaInicio;
    private DatePicker dpFechaFin;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Añadir Reserva");

        // Crear etiquetas y campos de texto
        Label lblIdCliente = new Label("ID Cliente:");
        txtIdCliente = new TextField();

        Label lblIdHabitacion = new Label("ID Habitación:");
        txtIdHabitacion = new TextField();

        Label lblPrecio = new Label("Precio: ");
        txtPrecio = new TextField();

        Label lblFechaInicio = new Label("Fecha de Inicio:");
        dpFechaInicio = new DatePicker();

        Label lblFechaFin = new Label("Fecha de Fin:");
        dpFechaFin = new DatePicker();

        // Crear botón para añadir reserva
        Button btnAnadirReserva = new Button("Añadir Reserva");
        btnAnadirReserva.setOnAction(event -> anadirReserva());

        // Crear menus
        Menu consultarMenu = new Menu("Consultar");
        Menu añadirCliente = new Menu("Añadir Cliente");
        // Crear opciones
        MenuItem añadirClienteItem = new MenuItem("Añadir Cliente");
        MenuItem clientesMenuItem = new MenuItem("Clientes");
        añadirClienteItem.setOnAction(event -> addCliente());
        añadirCliente.getItems().add(añadirClienteItem);
        clientesMenuItem.setOnAction(event -> consultarClientes());
        consultarMenu.getItems().add(clientesMenuItem);

        // Crear menú bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(consultarMenu);
        menuBar.getMenus().add(añadirCliente);

        // Crear GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Añadir etiquetas y campos al GridPane
        gridPane.add(lblIdCliente, 0, 0);
        gridPane.add(txtIdCliente, 1, 0);
        gridPane.add(lblIdHabitacion, 0, 1);
        gridPane.add(txtIdHabitacion, 1, 1);
        gridPane.add(lblFechaInicio, 0, 2);
        gridPane.add(dpFechaInicio, 1, 2);
        gridPane.add(lblFechaFin, 0, 3);
        gridPane.add(dpFechaFin, 1, 3);
        gridPane.add(lblPrecio,0,4);
        gridPane.add(txtPrecio,1,4);
        gridPane.add(btnAnadirReserva, 0, 5, 2, 1);

        // Crear VBox para contener menú y GridPane
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, gridPane);

        // Crear escena
        Scene scene = new Scene(vBox, 400, 300);

        // Mostrar la escena
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCliente() {
        // Crear un diálogo de entrada para que el usuario ingrese el nombre, correo y teléfono del cliente
        Dialog<Cliente> dialog = new Dialog<>();
        dialog.setTitle("Añadir Cliente");
        dialog.setHeaderText(null);

        // Establecer el botón de aceptar en el diálogo
        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAceptar, ButtonType.CANCEL);

        // Crear campos de texto para el nombre, correo y teléfono del cliente
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        TextField txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo Electrónico");
        TextField txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");

        // Crear un GridPane para contener las etiquetas y campos de texto
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Nombre:"), 0, 0);
        gridPane.add(txtNombre, 1, 0);
        gridPane.add(new Label("Correo Electrónico:"), 0, 1);
        gridPane.add(txtCorreo, 1, 1);
        gridPane.add(new Label("Teléfono:"), 0, 2);
        gridPane.add(txtTelefono, 1, 2);

        // Agregar el GridPane al diálogo
        dialog.getDialogPane().setContent(gridPane);

        // Convertir el resultado del diálogo a un objeto Cliente cuando el usuario hace clic en Aceptar
        dialog.setResultConverter(buttonType -> {
            if (buttonType == btnAceptar) {
                return new Cliente(txtNombre.getText(), txtCorreo.getText(), txtTelefono.getText());
            }
            return null;
        });

        // Mostrar el diálogo y esperar a que el usuario ingrese la información del cliente
        Optional<Cliente> result = dialog.showAndWait();

        // Procesar la información del cliente ingresada por el usuario
        result.ifPresent(cliente -> {
                    // Llamar al método de tu DAO para añadir el cliente a la base de datos
                    ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
                    clienteDAO.save(cliente);
                });
    }

    // Método para añadir la reserva
    private void anadirReserva() {
        try {
            long idCliente = Long.parseLong(txtIdCliente.getText());
            long idHabitacion = Long.parseLong(txtIdHabitacion.getText());
            LocalDate fechaInicio = dpFechaInicio.getValue();
            LocalDate fechaFin = dpFechaFin.getValue();
            double precio = Double.parseDouble(txtPrecio.getText());

            ReservaDAOHibernate reservaDAO = new ReservaDAOHibernate();
            Reserva reserva = new Reserva(fechaInicio,fechaFin,precio,idCliente,idHabitacion);

            reservaDAO.save(reserva);

            // Notificar al usuario
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Reserva añadida correctamente");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Manejo de errores si los campos no contienen números válidos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, introduce números válidos en los campos ID Cliente e ID Habitación");
            alert.showAndWait();
        }
    }

    // Método para consultar clientes
    private void consultarClientes() {
        // Crear un diálogo de entrada para que el usuario ingrese el ID del cliente
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consulta de Clientes");
        dialog.setHeaderText(null);
        dialog.setContentText("Por favor, introduce el ID del cliente:");

        // Mostrar el diálogo y esperar a que el usuario ingrese el ID del cliente
        Optional<String> result = dialog.showAndWait();

        // Procesar el ID del cliente ingresado por el usuario
        result.ifPresent(idCliente -> {
            // Convertir el ID del cliente a tipo long
            try {
                long id = Long.parseLong(idCliente);

                // Llamar al método de tu DAO para buscar el cliente en la base de datos
                ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
                Cliente cliente = clienteDAO.findById(id);

                if (cliente != null) {
                    // Mostrar la información del cliente en un diálogo de alerta
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información del Cliente");
                    alert.setHeaderText(null);
                    alert.setContentText("ID: " + cliente.getId() + "\n"
                            + "Nombre: " + cliente.getNombre() + "\n"
                            + "Correo Electrónico: " + cliente.getCorreo() + "\n"
                            + "Teléfono: " + cliente.getTelefono());
                    alert.showAndWait();
                } else {
                    // Mostrar un mensaje si el cliente no se encuentra en la base de datos
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cliente no encontrado");
                    alert.setHeaderText(null);
                    alert.setContentText("El cliente con ID " + id + " no se encuentra en la base de datos.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Manejar errores si el ID del cliente no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, introduce un ID de cliente válido.");
                alert.showAndWait();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

