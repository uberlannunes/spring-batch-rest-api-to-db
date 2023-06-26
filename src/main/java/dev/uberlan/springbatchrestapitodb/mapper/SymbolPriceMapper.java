package dev.uberlan.springbatchrestapitodb.mapper;

import dev.uberlan.springbatchrestapitodb.model.SymbolPrice;
import dev.uberlan.springbatchrestapitodb.model.SymbolPriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SymbolPriceMapper {

    public abstract SymbolPrice mapToEntity(SymbolPriceDTO symbolPriceDTO);
}
