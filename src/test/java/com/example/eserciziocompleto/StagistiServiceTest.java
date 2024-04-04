package com.example.eserciziocompleto;

import com.example.eserciziocompleto.entity.Stagisti;
import com.example.eserciziocompleto.repository.StagistiRepository;
import com.example.eserciziocompleto.service.StagistiService;
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
public class StagistiServiceTest {
    @Mock
    private StagistiRepository stagistiRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private StagistiService service;

    @Test
    void testCreateStagisti() {
        Stagisti stagisti = new Stagisti();
        when(stagistiRepository.save(stagisti)).thenReturn(stagisti);

        Stagisti createdStagisti = service.create(stagisti);

        verify(stagistiRepository).save(stagisti);
        assertEquals(stagisti, createdStagisti);
    }

    @Test
    void testGetExistingStagisti() {
        Long id = 1L;
        Stagisti existingStagisti = new Stagisti();
        when(stagistiRepository.findById(id)).thenReturn(Optional.of(existingStagisti));

        Stagisti retrievedStagisti = service.getById(id);

        verify(stagistiRepository).findById(id);
        assertEquals(existingStagisti, retrievedStagisti);
    }

    @Test
    void testGetNonExistingStagisti() {
        Long id = 2L;
        when(stagistiRepository.findById(id)).thenReturn(Optional.empty());

        Stagisti retrievedStagisti = service.getById(id);

        verify(stagistiRepository).findById(id);
        assertNull(retrievedStagisti);
    }

    @Test
    void testGetAllStagisti() {
        List<Stagisti> allStagisti = List.of(new Stagisti(), new Stagisti());
        when(stagistiRepository.findAll()).thenReturn(allStagisti);

        List<Stagisti> retrievedStagisti = service.getAll();

        verify(stagistiRepository).findAll();
        assertEquals(allStagisti, retrievedStagisti);
    }

    @Test
    void testUpdateExistingStagisti() {
        Long id = 1L;
        Stagisti existingStagisti = new Stagisti();
        when(stagistiRepository.existsById(id)).thenReturn(true);
        when(stagistiRepository.save(existingStagisti)).thenReturn(existingStagisti);

        Stagisti updatedStagisti = service.update(existingStagisti);

        verify(stagistiRepository).existsById(id);
        verify(stagistiRepository).save(existingStagisti);
        assertEquals(existingStagisti, updatedStagisti);
    }

    @Test
    void testUpdateNonExistingStagisti() {
        Long id = 2L;
        when(stagistiRepository.existsById(id)).thenReturn(false);

        Stagisti updatedStagisti = service.update(new Stagisti());

        verify(stagistiRepository).existsById(id);
        assertNull(updatedStagisti);
    }

    @Test
    void testDeleteExistingStagisti() {
        Long id = 1L;
        doNothing().when(stagistiRepository).deleteById(id);

        service.delete(id);

        verify(stagistiRepository).deleteById(id);
    }
}
