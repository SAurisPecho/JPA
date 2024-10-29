package com.egg.persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("Libreria");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emfInstance;
    }
}
