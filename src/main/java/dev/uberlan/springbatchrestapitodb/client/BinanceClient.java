package dev.uberlan.springbatchrestapitodb.client;

import dev.uberlan.springbatchrestapitodb.model.SymbolPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "binance", url = "https://www.binance.com/api/v3/")
public interface BinanceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ticker/price")
    List<SymbolPriceDTO> getPrices();
}
