package com.maisprati.lojabicicleta.controller;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;
import com.maisprati.lojabicicleta.service.BicicletaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/bicicletas")
public class BicicletaController {

    private final BicicletaService bicicletaService;


    public BicicletaController(BicicletaService bicicletaService) {
        this.bicicletaService = bicicletaService;
    }

    @GetMapping
    public List<Bicicleta> listarTodasBicicletas() {
        return bicicletaService.listAll();
    }

    @GetMapping("/{id}")
    public Bicicleta listarPorId(@PathVariable UUID id) {
        return bicicletaService.findByIdOrThrowBadRequestException(id);
    }

    @PostMapping
    public ResponseEntity<Bicicleta> criarBicicleta(@RequestBody BicicletaPostRequestBody bicicletaPostRequestBody) throws Exception {
        return new ResponseEntity<>(bicicletaService.save(bicicletaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBicicleta(@PathVariable UUID id) {
        bicicletaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> atualizarBicicleta(@RequestBody BicicletaPutRequestBody bicicletaPutRequestBody) throws Exception {
        bicicletaService.replace(bicicletaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Bicicleta> atualizarParcialmenteBicicleta(@PathVariable UUID id,
                                                                    @RequestBody BicicletaPatchRequestBody bicicletaPatchRequestBody) throws Exception {
        bicicletaService.update(id, bicicletaPatchRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
