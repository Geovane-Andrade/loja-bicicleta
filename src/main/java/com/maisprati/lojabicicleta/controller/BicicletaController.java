package com.maisprati.lojabicicleta.controller;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.exception.BadRequestException;
import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;
import com.maisprati.lojabicicleta.service.BicicletaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Bicicleta>> list(Pageable pageable) {
        return ResponseEntity.ok(bicicletaService.listAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bicicleta>> listAll() {
        return ResponseEntity.ok(bicicletaService.listAllNonPageable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bicicleta> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bicicletaService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Bicicleta> save(@RequestBody BicicletaPostRequestBody bicicletaPostRequestBody) throws BadRequestException {
        return new ResponseEntity<>(bicicletaService.save(bicicletaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bicicletaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody BicicletaPutRequestBody bicicletaPutRequestBody) throws BadRequestException {
        bicicletaService.replace(bicicletaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Bicicleta> update(@PathVariable UUID id,
                                            @RequestBody BicicletaPatchRequestBody bicicletaPatchRequestBody) throws BadRequestException {
        bicicletaService.update(id, bicicletaPatchRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
