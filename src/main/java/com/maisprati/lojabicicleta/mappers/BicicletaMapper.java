package com.maisprati.lojabicicleta.mappers;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class BicicletaMapper {
    public static final BicicletaMapper INSTANCE = Mappers.getMapper(BicicletaMapper.class);
    public abstract Bicicleta toBicicleta(BicicletaPostRequestBody bicicletaPostRequestBody);
    public abstract Bicicleta toBicicleta(BicicletaPutRequestBody bicicletaPutRequestBody);
    public abstract Bicicleta toBicicleta(BicicletaPatchRequestBody bicicletaPatchRequestBody);
}
