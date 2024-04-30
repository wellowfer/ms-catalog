package br.com.fiap.catalog.exception;

import jakarta.persistence.EntityNotFoundException;

public class CatalogException extends EntityNotFoundException {

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, (Exception) cause);
    }
}