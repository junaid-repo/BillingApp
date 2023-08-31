package com.billing.app.accounts.externalapi;

import com.billing.app.accounts.apiClassModels.BaseOutput3;
import com.billing.app.accounts.apiClassModels.EmployeeSales;
import org.apache.http.client.methods.HttpPost;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CommTest {

    public static void main(String args[]) {

        EmployeeSales empSales = new EmployeeSales();
        empSales.setAmount(2000d);
        empSales.setCustomerCode("CUS000109");
        empSales.setEmpId(Integer.parseInt("152"));
        RestTemplate temp = new RestTemplate();


        final String uri = "http://" + "localhost:7011" + "/bs/employee/updateEmpSales/";

        ResponseEntity<BaseOutput3> response1 = temp.postForEntity(uri, empSales, BaseOutput3.class);

        System.out.println(response1.getBody().toString());

    }

}
