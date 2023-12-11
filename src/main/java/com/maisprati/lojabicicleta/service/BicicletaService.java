package com.maisprati.lojabicicleta.service;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.domain.repository.BicicletaRepository;
import com.maisprati.lojabicicleta.exception.BadRequestException;
import com.maisprati.lojabicicleta.mappers.BicicletaMapper;
import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BicicletaService {

    private final BicicletaRepository bicicletaRepository;


    public BicicletaService(BicicletaRepository bicicletaRepository) {
        this.bicicletaRepository = bicicletaRepository;
    }


    public Page<Bicicleta> listAll(Pageable pageable) {
        return bicicletaRepository.findAll(pageable);
    }
    public List<Bicicleta> listAllNonPageable() {
        return bicicletaRepository.findAll();
    }

    public Bicicleta findByIdOrThrowBadRequestException(UUID id) {
        return bicicletaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Bicicleta não existe!"));
    }

    @Transactional
    public Bicicleta save(BicicletaPostRequestBody bicicletaPostRequestBody) throws BadRequestException {
        return bicicletaRepository.save(BicicletaMapper.INSTANCE.toBicicleta(bicicletaPostRequestBody));

    }

    public void delete(UUID id) {
        bicicletaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(BicicletaPutRequestBody bicicletaPutRequestBody) throws BadRequestException {
        Bicicleta bicicletaSalva = findByIdOrThrowBadRequestException(bicicletaPutRequestBody.getId());
        Bicicleta bicicleta = BicicletaMapper.INSTANCE.toBicicleta(bicicletaPutRequestBody);
        bicicleta.setId(bicicletaSalva.getId());

        bicicletaRepository.save(bicicleta);


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
