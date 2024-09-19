package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller.controlExceptionHandler;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        return new ResponseEntity<>("Maximum upload size exceeded, upload an image less than 10MB", HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(MaxUploadSizeExceededException ex) {
        if (ex.getCause() instanceof MaxUploadSizeExceededException) {
            return new ResponseEntity<>("Maximum upload size exceeded, upload an image less than 10MB", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<String> handleFileAlreadyExistsException(FileAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("File Already Exists");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        if (ex.getCause() instanceof java.nio.file.FileAlreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("File Already Exists");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    public ResponseEntity<String> handleBuildingNotFoundException(BuildingNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingUnitException.class)
    public ResponseEntity<String> handleExistingUnitException(ExistingUnitException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnitNotFoundException.class)
    public ResponseEntity<String> handleUnitNotFoundException(UnitNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CondoOwnerNotFoundException.class)
    public ResponseEntity<String> handleCondoOwnerNotFoundException(CondoOwnerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingAccountException.class)
    public ResponseEntity<String> handleExistingAccountException(ExistingAccountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectDocumentOrPasswordException.class)
    public ResponseEntity<String> handleIncorrectDocumentOrPasswordException(IncorrectDocumentOrPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBuildingResidentException.class)
    public ResponseEntity<String> handleInvalidBuildingResidentException(InvalidBuildingResidentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoComplaintsFoundException.class)
    public ResponseEntity<String> handleNoComplaintsFoundException(NoComplaintsFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<String> handleDocumentNotFoundException(DocumentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OwnerAlreadyAssignedToUnitException.class)
    public ResponseEntity<String> handleOwnerAlreadyAssignedToUnitException(OwnerAlreadyAssignedToUnitException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDocumentException.class)
    public ResponseEntity<String> handleDuplicateDocumentException(DuplicateDocumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
