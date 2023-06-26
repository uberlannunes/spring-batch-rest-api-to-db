package dev.uberlan.springbatchrestapitodb.model;

import java.math.BigDecimal;

public record SymbolPriceDTO(String symbol, BigDecimal price) {
}
