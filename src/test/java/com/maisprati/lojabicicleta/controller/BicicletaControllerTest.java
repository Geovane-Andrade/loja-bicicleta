package com.maisprati.lojabicicleta.controller;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.exception.BadRequestException;
import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;
import com.maisprati.lojabicicleta.service.BicicletaService;
import com.maisprati.lojabicicleta.util.BicicletaCreator;
import com.maisprati.lojabicicleta.util.BicicletaPatchRequestBodyCreator;
import com.maisprati.lojabicicleta.util.BicicletaPostRequestBodyCreator;
import com.maisprati.lojabicicleta.util.BicicletaPutRequestBodyCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class BicicletaControllerTest {
    @InjectMocks
    private BicicletaController bicicletaController;
    @Mock
    private BicicletaService bicicletaServiceMock;

    @BeforeEach
    void setUp() throws BadRequestException {
        PageImpl<Bicicleta> bicicletaPage = new PageImpl<>(List.of(BicicletaCreator.criarBicicletaSemId()));
        BDDMockito.when(bicicletaServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(bicicletaPage);

        BDDMockito.when(bicicletaServiceMock.listAllNonPageable())
                .thenReturn(List.of(BicicletaCreator.criarBicicletaSemId()));

        BDDMockito.when(bicicletaServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.any()))
                .thenReturn(BicicletaCreator.criarBicicletaComId());

        BDDMockito.when(bicicletaServiceMock.save(ArgumentMatchers.any(BicicletaPostRequestBody.class)))
                .thenReturn(BicicletaCreator.criarBicicletaComId());

        BDDMockito.doNothing().when(bicicletaServiceMock).replace(ArgumentMatchers.any(BicicletaPutRequestBody.class));

        BDDMockito.doNothing().when(bicicletaServiceMock)
                .update(ArgumentMatchers.any(), ArgumentMatchers.any(BicicletaPatchRequestBody.class));

        BDDMockito.doNothing().when(bicicletaServiceMock).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("list retorna lista de bicicletas dentro da página quando sucesso")
    void list_RetornaObjetoDaListaDeBicicletasDentroDaPagina_QuandoSucesso() {
        String modeloEsperado = BicicletaCreator.criarBicicletaSemId().getModelo();

        Page<Bicicleta> bicicletaPage = bicicletaController.list(null).getBody();

        Assertions.assertNotNull(bicicletaPage);

        Assertions.assertFalse(bicicletaPage.toList().isEmpty());
        Assertions.assertEquals(bicicletaPage.toList().get(0).getModelo(), modeloEsperado);
    }

    @Test
    @DisplayName("listAll retorna lista de todas bicicletas quando sucesso ")
    void listAll_RetornaListaDeBicicletasDentroDaPagina_QuandoSucesso() {
        String modeloEsperado = BicicletaCreator.criarBicicletaSemId().getModelo();

        List<Bicicleta> bicicletaList = bicicletaController.listAll().getBody();

        Assertions.assertNotNull(bicicletaList);

        Assertions.assertFalse(bicicletaList.isEmpty());

        Assertions.assertEquals(bicicletaList.get(0).getModelo(), modeloEsperado);
    }

    @Test
    @DisplayName("findById retorna bicicleta quando sucesso ")
    void findById_RetornaBicicletaDentroDaPagina_QuandoSucesso() {
        UUID idEsperado = BicicletaCreator.criarBicicletaComId().getId();


        Bicicleta bicicleta = bicicletaController.findById(UUID.fromString("573a8a5d-843d-4892-b7f1-dabacd9963ae")).getBody();

        Assertions.assertNotNull(bicicleta);

        Assertions.assertEquals(bicicleta.getId(), idEsperado);
    }

    @Test
    @DisplayName("save retorna bicicleta salva quando sucesso ")
    void save_RetornaBicicleta_QuandoSucesso() {

        Bicicleta bicicleta = bicicletaController.save(BicicletaPostRequestBodyCreator.criarBicicletaPostRequestBody()).getBody();

        Assertions.assertNotNull(bicicleta);

        Assertions.assertEquals(bicicleta, BicicletaCreator.criarBicicletaComId());
    }


    @Test
    @DisplayName("replace retorna bicicleta atualizada quando sucesso ")
    void replace_RetornaBicicletaCompletamenteAtualizada_QuandoSucesso() {
        Assertions.assertDoesNotThrow(() -> bicicletaController.replace(BicicletaPutRequestBodyCreator.criarBicicletaPutRequestBody()));

        ResponseEntity<Void> entity = bicicletaController.replace(BicicletaPutRequestBodyCreator.criarBicicletaPutRequestBody());

        Assertions.assertNotNull(entity);

        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("update retorna atributo atualizado quando sucesso ")
    void update_RetornaBicicletaAtualizada_QuandoSucesso() {
        Assertions.assertDoesNotThrow(() -> bicicletaController.update(UUID.randomUUID(),BicicletaPatchRequestBodyCreator.criarBicicletaPatchRequestBody()));

        ResponseEntity<Bicicleta> entity = bicicletaController.update(UUID.randomUUID(),BicicletaPatchRequestBodyCreator.criarBicicletaPatchRequestBody());

        Assertions.assertNotNull(entity);

        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete retorna bicicleta excluída quando sucesso ")
    void delete_RetornaBicicleta_QuandoSucesso() {
        Assertions.assertDoesNotThrow(() -> bicicletaController.delete(UUID.randomUUID()));

        ResponseEntity<Void> entity = bicicletaController.delete(UUID.randomUUID());

        Assertions.assertNotNull(entity);

        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}