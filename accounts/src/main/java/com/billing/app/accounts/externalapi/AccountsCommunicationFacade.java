package com.billing.app.accounts.externalapi;

import com.billing.app.accounts.apiClassModels.ChargesDetails;
import com.billing.app.accounts.apiClassModels.StockDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class AccountsCommunicationFacade {

    @Autowired
    RestTemplate temp;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public StockDetails getStockDetialsById(Integer id) {
        log.info("Entered into getStockDetails method of StocksServiceCommunication class");
        // StockDetails response = new StockDetails();
        final String uri = "http://" + "STOCK-SERVICE" + "/bs/stocks/findProductById/" + String.valueOf(id);

        log.info("The formatted uri is --> " + uri);

        ResponseEntity<StockDetails> response = temp.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeader()), StockDetails.class);

        return response.getBody();


    }

    public List<ChargesDetails> getChargesDetails(String category) {

        final String uri = "http://" + "EXTRACHARGES-SERVICE" + "/bs/charges/get/" + category;
        log.info("The formatted uri is --> " + uri);

        ResponseEntity<List<ChargesDetails>> response = temp.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeader()), new ParameterizedTypeReference<List<ChargesDetails>>() {
        });
        log.info("the respose from getChargesDetails call --> " + response.getBody());
        return response.getBody();
    }

    private HttpHeaders httpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }


}
