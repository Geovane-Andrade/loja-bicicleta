package com.maisprati.lojabicicleta.service;

import com.maisprati.lojabicicleta.domain.models.Bicicleta;
import com.maisprati.lojabicicleta.domain.repositories.BicicletaRepository;
import com.maisprati.lojabicicleta.mapper.BicicletaMapper;
import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BicicletaService {

    private final BicicletaRepository bicicletaRepository;


    public BicicletaService(BicicletaRepository bicicletaRepository) {
        this.bicicletaRepository = bicicletaRepository;
    }


    public Bicicleta findByIdOrThrowBadRequestException(UUID id) {
        return bicicletaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bicicleta não existe!"));
    }

    public Bicicleta save(BicicletaPostRequestBody bicicletaPostRequestBody) throws Exception {
        return bicicletaRepository.save(BicicletaMapper.INSTANCE.toBicicleta(bicicletaPostRequestBody));

    }

    public List<Bicicleta> listAll() {
        return bicicletaRepository.findAll();
    }

    public void replace(BicicletaPutRequestBody bicicletaPutRequestBody) throws Exception {
        Bicicleta bicicletaSalva = findByIdOrThrowBadRequestException(bicicletaPutRequestBody.getId());
        Bicicleta bicicleta = BicicletaMapper.INSTANCE.toBicicleta(bicicletaPutRequestBody);
        bicicleta.setId(bicicletaSalva.getId());

        bicicletaRepository.save(bicicleta);


    }


    public void delete(UUID id) {
        bicicletaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(UUID id, BicicletaPatchRequestBody bicicletaPatch) {
        Bicicleta bicicletaSalva = findByIdOrThrowBadRequestException(id);
        Bicicleta instanceBicicleta = BicicletaMapper.INSTANCE.toBicicleta(bicicletaPatch);
        if (instanceBicicleta.getModelo() != null && !instanceBicicleta.getModelo().equals(bicicletaSalva.getModelo())) {
            bicicletaSalva.setModelo(instanceBicicleta.getModelo());
        }
        if (instanceBicicleta.getDescricao() != null && !instanceBicicleta.getDescricao().equals(bicicletaSalva.getDescricao())) {
            bicicletaSalva.setDescricao(instanceBicicleta.getDescricao());
        }
        if (instanceBicicleta.getPrecoPago() != null && !instanceBicicleta.getPrecoPago().equals(bicicletaSalva.getPrecoPago())) {
            bicicletaSalva.setPrecoPago(instanceBicicleta.getPrecoPago());
        }
        if (instanceBicicleta.getDataCompra() != null && !instanceBicicleta.getDataCompra().equals(bicicletaSalva.getDataCompra())) {
            bicicletaSalva.setDataCompra(instanceBicicleta.getDataCompra());
        }
        if (instanceBicicleta.getNomeComprador() != null && !instanceBicicleta.getNomeComprador().equals(bicicletaSalva.getNomeComprador())) {
            bicicletaSalva.setNomeComprador(instanceBicicleta.getNomeComprador());
        }
        if (instanceBicicleta.getNomeLoja() != null && !instanceBicicleta.getNomeLoja().equals(bicicletaSalva.getNomeLoja())) {
            bicicletaSalva.setNomeLoja(instanceBicicleta.getNomeLoja());
        }

        bicicletaRepository.save(bicicletaSalva);

    }
}
