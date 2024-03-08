package com.example.adproyect;

import com.example.adproyect.DAO.Hibernate.ClienteDAOHibernate;
import com.example.adproyect.DAO.Hibernate.HabitacionDAOHibernate;
import com.example.adproyect.DAO.Hibernate.HotelDAOHibernate;
import com.example.adproyect.DAO.Hibernate.ReservaDAOHibernate;
import com.example.adproyect.Entidades.Cliente;
import com.example.adproyect.Entidades.Habitacion;
import com.example.adproyect.Entidades.Hotel;
import com.example.adproyect.Entidades.Reserva;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
        HabitacionDAOHibernate habitacionDAO = new HabitacionDAOHibernate();
        HotelDAOHibernate hotelDAO = new HotelDAOHibernate();
        ReservaDAOHibernate reservaDAO = new ReservaDAOHibernate();

        while (true) {
            System.out.println("Bienvenido al sistema de reservas de hotel");
            System.out.println("1. Ver habitaciones disponibles");
            System.out.println("2. Ver hoteles");
            System.out.println("3. Ver clientes");
            System.out.println("4. Ver reservas");
            System.out.println("5. Añadir cliente");
            System.out.println("6. Añadir reserva");
            System.out.println("7. Añadir habitación");
            System.out.println("8. Añadir hotel");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    mostrarHabitacionesDisponibles(habitacionDAO);
                    break;
                case 2:
                    mostrarHoteles(hotelDAO);
                    break;
                case 3:
                    verClientes(clienteDAO);
                    break;
                case 4:
                    verReservas(reservaDAO);
                    break;
                case 5:
                    añadirCliente(clienteDAO, scanner);
                    break;
                case 6:
                    añadirReserva(clienteDAO, habitacionDAO, reservaDAO, scanner);
                    break;
                case 7:
                    añadirHabitacion(habitacionDAO, hotelDAO, scanner);
                    break;
                case 8:
                    añadirHotel(hotelDAO, scanner);
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void añadirHotel(HotelDAOHibernate hotelDAO, Scanner scanner) {
        System.out.print("Nombre del hotel: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección del hotel: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono del hotel: ");
        String telefono = scanner.nextLine();
        System.out.print("Número de estrellas: ");
        int estrellas = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Hotel hotel = new Hotel(nombre, direccion, telefono, estrellas);
        hotelDAO.save(hotel);
        System.out.println("Hotel añadido correctamente.");
    }

    private static void añadirHabitacion(HabitacionDAOHibernate habitacionDAO, HotelDAOHibernate hotelDAO, Scanner scanner) {
        System.out.print("ID del hotel: ");
        long idHotel = scanner.nextLong();
        System.out.println("Tipo de habitación: ");
        String tipo = scanner.nextLine();
        System.out.print("Precio por noche: ");
        double precioPorNoche = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        Hotel hotel = hotelDAO.findById(idHotel);
        if (hotel != null) {
            Habitacion habitacion = new Habitacion(tipo, precioPorNoche, true, hotel);
            habitacionDAO.save(habitacion);
            System.out.println("Habitación añadida correctamente.");
        } else {
            System.out.println("Hotel no encontrado. Asegúrese de que el ID sea válido.");
        }
    }

    private static void verReservas(ReservaDAOHibernate reservaDAO) {
        List<Reserva> reservas = reservaDAO.getAll();
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private static void verClientes(ClienteDAOHibernate clienteDAO) {
        List<Cliente> clientes = clienteDAO.getAll();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void mostrarHabitacionesDisponibles(HabitacionDAOHibernate habitacionDAO) {
        System.out.println("Habitaciones disponibles:");
        List habitaciones = habitacionDAO.obtenerHabitacionesDisponibles();
        for (Object habitacion : habitaciones) {
            System.out.println(habitacion);
        }
    }

    private static void mostrarHoteles(HotelDAOHibernate hotelDAO) {
        System.out.println("Hoteles disponibles:");
        List<Hotel> hoteles = hotelDAO.getAll();
        for (Hotel hotel : hoteles) {
            System.out.println(hotel);
        }
    }

    private static void añadirCliente(ClienteDAOHibernate clienteDAO, Scanner scanner) {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo electrónico del cliente: ");
        String correo = scanner.nextLine();
        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, correo, telefono);
        clienteDAO.save(cliente);
        System.out.println("Cliente añadido correctamente.");
    }

    private static void añadirReserva(ClienteDAOHibernate clienteDAO, HabitacionDAOHibernate habitacionDAO, ReservaDAOHibernate reservaDAO, Scanner scanner) {
        System.out.print("ID del cliente: ");
        long idCliente = scanner.nextLong();
        System.out.print("ID de la habitación: ");
        long idHabitacion = scanner.nextLong();
        System.out.print("Fecha de entrada (YYYY-MM-DD): ");
        String entradaStr = scanner.next();
        LocalDate fechaEntrada = LocalDate.parse(entradaStr);
        System.out.print("Fecha de salida (YYYY-MM-DD): ");
        String salidaStr = scanner.next();
        LocalDate fechaSalida = LocalDate.parse(salidaStr);

        Cliente cliente = clienteDAO.findById(idCliente);
        Habitacion habitacion = habitacionDAO.findById(idHabitacion);

        if (cliente != null && habitacion != null) {
            double precioTotal = calcularPrecioTotal(habitacion.getPrecioPorNoche(), fechaEntrada, fechaSalida);
            Reserva reserva = new Reserva(fechaEntrada, fechaSalida, precioTotal, idCliente, idHabitacion, cliente, habitacion);
            reservaDAO.save(reserva);
            System.out.println("Reserva realizada correctamente.");
        } else {
            System.out.println("Cliente o habitación no encontrados. Asegúrese de que los IDs sean válidos.");
        }
    }

    private static double calcularPrecioTotal(double precioPorNoche, LocalDate fechaEntrada, LocalDate fechaSalida) {
        long numNoches = fechaEntrada.until(fechaSalida).getDays();
        return precioPorNoche * numNoches;
    }
}
