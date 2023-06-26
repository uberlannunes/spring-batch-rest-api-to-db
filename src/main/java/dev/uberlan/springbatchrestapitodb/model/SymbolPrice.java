package dev.uberlan.springbatchrestapitodb.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "symbol_prices")
public class SymbolPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    @Column(precision = 24, scale = 8)
    private BigDecimal price;

    public SymbolPrice() {
    }

    public SymbolPrice(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SymbolPrice{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
