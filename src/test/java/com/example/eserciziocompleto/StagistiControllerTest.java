package com.example.eserciziocompleto;


import com.example.eserciziocompleto.controller.StagistiController;
import com.example.eserciziocompleto.entity.Stagisti;
import com.example.eserciziocompleto.service.StagistiService;
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


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class StagistiControllerTest {
    @Mock
    private StagistiService stagistiService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private StagistiController controller;


    @Test
    void testCreate() {
        Stagisti stagisti = new Stagisti();
        when(stagistiService.create(stagisti)).thenReturn(stagisti);

        ResponseEntity<Stagisti> response = controller.create(stagisti);

        verify(stagistiService).create(stagisti);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(stagisti, response.getBody());
    }

    @Test
    void testGetAll() {
        List<Stagisti> stagistiList = List.of(new Stagisti(), new Stagisti());
        when(stagistiService.getAll()).thenReturn(stagistiList);

        ResponseEntity<List<Stagisti>> response = controller.getAll();

        verify(stagistiService).getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stagistiList, response.getBody());
    }

    @Test
    void testGetById() {
        Long id = 1L;
        Stagisti stagisti = new Stagisti();
        when(stagistiService.getById(id)).thenReturn(stagisti);

        ResponseEntity<Stagisti> response = controller.getById(id);

        verify(stagistiService).getById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stagisti, response.getBody());
    }

    @Test
    void testUpdateExistingStagisti() {
        Long id = 1L;
        Stagisti existingStagisti = new Stagisti();
        Stagisti updatedStagisti = new Stagisti();
        when(stagistiService.getById(id)).thenReturn(existingStagisti);
        when(stagistiService.create(existingStagisti)).thenReturn(updatedStagisti);

        ResponseEntity<Stagisti> response = controller.update(id, updatedStagisti);

        verify(stagistiService).getById(id);
        verify(stagistiService).create(existingStagisti);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedStagisti, response.getBody());
    }

    @Test
    void testUpdateNonExistingStagisti() {
        Long id = 2L;
        Stagisti updatedStagisti = new Stagisti();
        when(stagistiService.getById(id)).thenReturn(null);

        ResponseEntity<Stagisti> response = controller.update(id, updatedStagisti);

        verify(stagistiService).getById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteExistingStagisti() {
        Long id = 1L;
        Stagisti existingStagisti = new Stagisti();
        when(stagistiService.getById(id)).thenReturn(existingStagisti);

        ResponseEntity<Void> response = controller.delete(id);

        verify(stagistiService).getById(id);
        verify(stagistiService).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void testDeleteNonExistingStagisti() {
        Long id = 2L;
        when(stagistiService.getById(id)).thenReturn(null);

        ResponseEntity<Void> response = controller.delete(id);

        verify(stagistiService).getById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetByIdNonExistingStagisti() {
        Long id = 2L;
        when(stagistiService.getById(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<Stagisti> response = controller.getById(id);

        verify(stagistiService).getById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllStagistiError() {
        when(stagistiService.getAll()).thenThrow(DatabaseException.class);

        ResponseEntity<List<Stagisti>> response = controller.getAll();

        verify(stagistiService).getAll();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testPostStagistiException() {
        Stagisti invalidStagisti = new Stagisti();
        when(stagistiService.create(invalidStagisti)).thenThrow(DatabaseException.class);

        ResponseEntity<Stagisti> response = controller.create(invalidStagisti);

        verify(stagistiService).create(invalidStagisti);
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertNull(response.getBody());
    }


}
