package com.example.eserciziocompleto;

import com.example.eserciziocompleto.entity.Superiori;
import com.example.eserciziocompleto.repository.SuperioriRepository;
import com.example.eserciziocompleto.service.SuperioriService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SuperioriServiceTest {
    @Mock
    private SuperioriRepository superioriRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private SuperioriService service;

    @Test
    void testCreateSuperiori() {
        Superiori superiori = new Superiori();
        when(superioriRepository.save(superiori)).thenReturn(superiori);

        Superiori createdSuperiori = service.create(superiori);

        verify(superioriRepository).save(superiori);
        assertEquals(superiori, createdSuperiori);
    }

    @Test
    void testGetExistingSuperiori() {
        Long id = 1L;
        Superiori existingSuperiori = new Superiori();
        when(superioriRepository.findById(id)).thenReturn(Optional.of(existingSuperiori));

        Superiori retrievedSuperiori = service.getById(id);

        verify(superioriRepository).findById(id);
        assertEquals(existingSuperiori, retrievedSuperiori);
    }

    @Test
    void testGetNonExistingSuperiori() {
        Long id = 2L;
        when(superioriRepository.findById(id)).thenReturn(Optional.empty());

        Superiori retrievedSuperiori = service.getById(id);

        verify(superioriRepository).findById(id);
        assertNull(retrievedSuperiori);
    }

    @Test
    void testGetAllSuperiori() {
        List<Superiori> allSuperiori = List.of(new Superiori(), new Superiori());
        when(superioriRepository.findAll()).thenReturn(allSuperiori);

        List<Superiori> retrievedSuperiori = service.getAll();

        verify(superioriRepository).findAll();
        assertEquals(allSuperiori, retrievedSuperiori);
    }

    @Test
    void testUpdateExistingSuperiori() {
        Long id = 1L;
        Superiori existingSuperiori = new Superiori();
        when(superioriRepository.existsById(id)).thenReturn(true);
        when(superioriRepository.save(existingSuperiori)).thenReturn(existingSuperiori);

        Superiori updatedSuperiori = service.update(existingSuperiori);

        verify(superioriRepository).existsById(id);
        verify(superioriRepository).save(existingSuperiori);
        assertEquals(existingSuperiori, updatedSuperiori);
    }

    @Test
    void testUpdateNonExistingSuperiori() {
        Long id = 2L;
        when(superioriRepository.existsById(id)).thenReturn(false);

        Superiori updatedSuperiori = service.update(new Superiori());

        verify(superioriRepository).existsById(id);
        assertNull(updatedSuperiori);
    }

    @Test
    void testDeleteExistingSuperiori() {
        Long id = 1L;
        doNothing().when(superioriRepository).deleteById(id);

        service.delete(id);

        verify(superioriRepository).deleteById(id);
    }
}
