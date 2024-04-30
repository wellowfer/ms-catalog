package br.com.fiap.catalog.infrastructure.controllers.mappers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityMapper {
    public ResponseEntity<?> toEntityOk(Object obj) {
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    public ResponseEntity<?> toEntityCreated(Object obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    public ResponseEntity<?> toEntityBadRequest(Object obj) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
    }

    public ResponseEntity<?> toEntityNotFound(Object obj) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj);
    }

    public ResponseEntity<?> toEntityInternalServerError(Object obj) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(obj);
    }

    public ResponseEntity<?> toEntityAccepted() {
        return ResponseEntity.accepted().build();
    }
}
