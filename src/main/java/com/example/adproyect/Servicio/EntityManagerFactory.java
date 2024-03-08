package com.example.adproyect.Servicio;

public class EntityManagerFactory {
    private static EntityManagerFactory instance;

    private EntityManagerFactory() {
    }

    public static EntityManagerFactory getInstance() {
        if (instance == null) {
            synchronized (EntityManagerFactory.class){
                if (instance == null) {
                    instance = new EntityManagerFactory();
                }
            }
        }
        return instance;
    }

    public static void close() {
    }
}
