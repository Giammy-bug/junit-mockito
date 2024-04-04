package com.example.eserciziocompleto;

import com.example.eserciziocompleto.controller.SuperioriController;

import com.example.eserciziocompleto.entity.Superiori;
import com.example.eserciziocompleto.service.SuperioriService;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


public class SuperioriControllerTest {

    @Mock
    private SuperioriService superioriService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private SuperioriController superioriController;

    @Test
    void TestCreate() {
        Superiori superiori = new Superiori();
        when(superioriService.create(superiori)).thenReturn(superiori);

        ResponseEntity<Superiori> response = superioriController.create(superiori);

        verify(superioriService).create(superiori);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(superiori, response.getBody());
    }

    @Test
    void testGetAll() {
        List<Superiori> superioriList = List.of(new Superiori(), new Superiori());
        when(superioriService.getAll()).thenReturn(superioriList);

        ResponseEntity<List<Superiori>> response = superioriController.getAll();

        verify(superioriService).getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(superioriList, response.getBody());
    }

    @Test
    void testGetById() {
        Long id = 1L;
        Superiori superiori = new Superiori();
        when(superioriService.getById(id)).thenReturn(superiori);

        ResponseEntity<Superiori> response = superioriController.getById(id);

        verify(superioriService).getById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(superiori, response.getBody());
    }

    @Test
    void testUpdateExistingSuperiori() {
        Long id = 1L;
        Superiori existingSuperiori = new Superiori();
        Superiori updatedSuperiori = new Superiori();
        when(superioriService.getById(id)).thenReturn(existingSuperiori);
        when(superioriService.create(existingSuperiori)).thenReturn(updatedSuperiori);

        ResponseEntity<Superiori> response = superioriController.update(id, updatedSuperiori);

        verify(superioriService).getById(id);
        verify(superioriService).create(existingSuperiori);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSuperiori, response.getBody());
    }

    @Test
    void testUpdateNonExistingSuperiori() {
        Long id = 2L;
        Superiori updatedSuperiori = new Superiori();
        when(superioriService.getById(id)).thenReturn(null);

        ResponseEntity<Superiori> response = superioriController.update(id, updatedSuperiori);

        verify(superioriService).getById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteExistingSuperiori() {
        Long id = 1L;
        Superiori existingSuperiori = new Superiori();
        when(superioriService.getById(id)).thenReturn(existingSuperiori);

        ResponseEntity<Void> response = superioriController.delete(id);

        verify(superioriService).getById(id);
        verify(superioriService).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteNonExistingSuperiori() {
        Long id = 2L;
        when(superioriService.getById(id)).thenReturn(null);

        ResponseEntity<Void> response = superioriController.delete(id);

        verify(superioriService).getById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetByIdNonExistingSuperiori() {
        Long id = 2L;
        when(superioriService.getById(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<Superiori> response = superioriController.getById(id);

        verify(superioriService).getById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllSuperioriError() {
        when(superioriService.getAll()).thenThrow(DatabaseException.class);

        ResponseEntity<List<Superiori>> response = superioriController.getAll();

        verify(superioriService).getAll();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testPostSuperioriException() {
        Superiori invalidSuperiori = new Superiori();
        when(superioriService.create(invalidSuperiori)).thenThrow(DatabaseException.class);

        ResponseEntity<Superiori> response = superioriController.create(invalidSuperiori);

        verify(superioriService).create(invalidSuperiori);
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertNull(response.getBody());
    }
}
