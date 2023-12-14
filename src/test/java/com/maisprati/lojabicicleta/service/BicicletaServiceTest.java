package com.maisprati.lojabicicleta.service;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.domain.repository.BicicletaRepository;
import com.maisprati.lojabicicleta.exception.BadRequestException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class BicicletaServiceTest {
    @InjectMocks
    private BicicletaService bicicletaService;
    @Mock
    private BicicletaRepository bicicletaRepositoryMock;

    @BeforeEach
    void setUp() throws BadRequestException {
        PageImpl<Bicicleta> bicicletaPage = new PageImpl<>(List.of(BicicletaCreator.criarBicicletaSemId()));
        BDDMockito.when(bicicletaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(bicicletaPage);

        BDDMockito.when(bicicletaRepositoryMock.findAll())
                .thenReturn(List.of(BicicletaCreator.criarBicicletaSemId()));

        BDDMockito.when(bicicletaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(BicicletaCreator.criarBicicletaComId()));

        BDDMockito.when(bicicletaRepositoryMock.save(ArgumentMatchers.any(Bicicleta.class)))
                .thenReturn(BicicletaCreator.criarBicicletaComId());


        BDDMockito.doNothing().when(bicicletaRepositoryMock).delete(ArgumentMatchers.any(Bicicleta.class));

    }

    @Test
    @DisplayName("listAll retorna lista de bicicletas dentro da página quando sucesso")
    void listAll_RetornaObjetoDaListaDeBicicletasDentroDaPagina_QuandoSucesso() {
        String modeloEsperado = BicicletaCreator.criarBicicletaSemId().getModelo();

        Page<Bicicleta> bicicletaPage = bicicletaService.listAll(PageRequest.of(1, 1));

        Assertions.assertNotNull(bicicletaPage);

        Assertions.assertFalse(bicicletaPage.toList().isEmpty());
        Assertions.assertEquals(bicicletaPage.toList().get(0).getModelo(), modeloEsperado);
    }

    @Test
    @DisplayName("listAllNonPageable retorna lista de todas bicicletas quando sucesso ")
    void listAllNonPageable_RetornaListaDeBicicletasDentroDaPagina_QuandoSucesso() {
        String modeloEsperado = BicicletaCreator.criarBicicletaSemId().getModelo();

        List<Bicicleta> bicicletaList = bicicletaService.listAllNonPageable();

        Assertions.assertNotNull(bicicletaList);

        Assertions.assertFalse(bicicletaList.isEmpty());

        Assertions.assertEquals(bicicletaList.get(0).getModelo(), modeloEsperado);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException retorna bicicleta quando sucesso ")
    void findByIdOrThrowBadRequestException_RetornaBicicletaDentroDaPagina_QuandoSucesso() {
        UUID idEsperado = BicicletaCreator.criarBicicletaComId().getId();


        Bicicleta bicicleta = bicicletaService.findByIdOrThrowBadRequestException(UUID.fromString("573a8a5d-843d-4892-b7f1-dabacd9963ae"));

        Assertions.assertNotNull(bicicleta);

        Assertions.assertEquals(bicicleta.getId(), idEsperado);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException lança a exceção BadRequestException quando não existe Bicicleta. ")
    void findByIdOrThrowBadRequestException_lancaBadRequestException_QuandoNaoExisteBicicleta() {
        BDDMockito.when(bicicletaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class,
                () -> bicicletaService.findByIdOrThrowBadRequestException(UUID.fromString("573a8a5d-843d-4892-b7f1-dabacd9963ae")) );


    }

    @Test
    @DisplayName("save retorna bicicleta salva quando sucesso ")
    void save_RetornaBicicleta_QuandoSucesso() {

        Bicicleta bicicleta = bicicletaService.save(BicicletaPostRequestBodyCreator.criarBicicletaPostRequestBody());

        Assertions.assertNotNull(bicicleta);

        Assertions.assertEquals(bicicleta, BicicletaCreator.criarBicicletaComId());
    }


    @Test
    @DisplayName("replace retorna bicicleta atualizada quando sucesso ")
    void replace_RetornaBicicletaCompletamenteAtualizada_QuandoSucesso() {
        Assertions.assertDoesNotThrow(() -> bicicletaService.replace(BicicletaPutRequestBodyCreator.criarBicicletaPutRequestBody()));

    }

    @Test
    @DisplayName("update retorna atributo atualizado quando sucesso ")
    void update_RetornaBicicletaAtualizada_QuandoSucesso() {
        Assertions.assertDoesNotThrow(() -> bicicletaService.update(UUID.randomUUID(), BicicletaPatchRequestBodyCreator.criarBicicletaPatchRequestBody()));

    }

    @Test
    @DisplayName("delete retorna bicicleta excluída quando sucesso ")
    void delete_RetornaBicicleta_QuandoSucesso() {
        Assertions.assertDoesNotThrow(() -> bicicletaService.delete(UUID.randomUUID()));
    }

}