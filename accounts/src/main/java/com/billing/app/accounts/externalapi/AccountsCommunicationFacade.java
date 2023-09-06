package com.billing.app.accounts.externalapi;

import com.billing.app.accounts.apiClassModels.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AccountsCommunicationFacade {

    @Autowired
    RestTemplate temp;
    
    @Autowired
    EmpServiceFiengClient epFC;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public StockDetails getStockDetialsById(Integer id) {
        log.info("Entered into getStockDetails method of StocksServiceCommunication class");
        // StockDetails response = new StockDetails();
        final String uri = "http://" + "STOCK-SERVICE" + "/bs/stocks/findProductById/" + String.valueOf(id);

        log.info("The formatted uri is --> " + uri);

        ResponseEntity<StockDetails> response = temp.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeader()), StockDetails.class);

        return response.getBody();


    }
  
    
    public CustomerDetails getCustDetailsByCode(String code) {
        log.info("Entered into getCustDetailsByCode method of StocksServiceCommunication class");
        // StockDetails response = new StockDetails();
        final String uri = "http://" + "CUSTOMER-SERVICE" + "/bs/customer/get/" + code;

        log.info("The formatted uri is --> " + uri);

        ResponseEntity<CustomerDetails> response = temp.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeader()), CustomerDetails.class);

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

    public void updateProdCounts(String id, String count) {

        final String uri = "http://" + "STOCK-SERVICE" + "/bs/stocks/updateStocksCounts/" + id+"/"+count;
        log.info("The formatted uri is --> " + uri);

        ResponseEntity<BaseOutput2> response = temp.exchange(uri, HttpMethod.POST, new HttpEntity<>(httpHeader()), BaseOutput2.class);
      //  log.info("the respose from getChargesDetails call --> " + response.getBody());
       // return response.getBody();
    }

    public void updateEmpSalesRecord(EmployeeSales empSales) {

       
        final String uri="http://" + "EMPLOYEE-SERVICE" + "/bs/employee/updateEmpSales";
        
       HttpEntity<EmployeeSales> request = new HttpEntity<>(empSales,  httpHeader());


       ResponseEntity<BaseOutput3> response=temp.exchange(uri,HttpMethod.POST, request, BaseOutput3.class);
       // ResponseEntity<BaseOutput3> response1= temp.postForEntity(uri, empSales, BaseOutput3.class);

    }

    private HttpHeaders httpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setBearerAuth(getJwtToken());

        return httpHeaders;
    }
    
    public String getJwtToken() {
    	
    	AuthRequest auth= new AuthRequest();
    	auth.setUsername("atifsohail5");
    	auth.setPassword("pass");
    	
    	String token=epFC.generateToken(auth).getBody();
    	System.out.println("the generated token is --> "+token);
    	
    	return token;
    }


	



}
