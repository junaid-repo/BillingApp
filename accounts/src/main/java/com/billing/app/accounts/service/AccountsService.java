package com.billing.app.accounts.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.app.accounts.apiClassModels.ChargesDetails;
import com.billing.app.accounts.apiClassModels.CustomerDetails;
import com.billing.app.accounts.apiClassModels.EmployeeSales;
import com.billing.app.accounts.apiClassModels.StockDetails;
import com.billing.app.accounts.dto.CollectionRequest;
import com.billing.app.accounts.dto.OrderDetailsResponse;
import com.billing.app.accounts.dto.OrderWiseProductDetailsDTO;
import com.billing.app.accounts.dto.ProductDetails;
import com.billing.app.accounts.dto.ProductDetailsList;
import com.billing.app.accounts.dto.TotalCartValue;
import com.billing.app.accounts.entities.CollectionResponse;
import com.billing.app.accounts.entities.ItemCountsEntity;
import com.billing.app.accounts.entities.OrderDetails;
import com.billing.app.accounts.entities.OrderWiseProductDetails;
import com.billing.app.accounts.externalapi.AccountsCommunicationFacade;
import com.billing.app.accounts.externalapi.EmpServiceFiengClient;
import com.billing.app.accounts.repositories.AccountsCollectionRepository;
import com.billing.app.accounts.repositories.AccountsOrderItemsRepository;
import com.billing.app.accounts.repositories.AccountsOrderRepository;
import com.billing.app.accounts.repositories.OrderWiseProductsRepository;
import com.billing.app.accounts.validations.OrderStatusValidation;


@Service
public class AccountsService {

    @Autowired
    AccountsCommunicationFacade comm;

    @Autowired
    AccountsCollectionRepository accCollRepo;

    @Autowired
    AccountsOrderRepository accOrderRepo;
    
    @Autowired
    OrderWiseProductsRepository owpRepo;

    @Autowired
    AccountsOrderItemsRepository accOrderItemsRepo;

    @Autowired
    EmpServiceFiengClient epFC;

    @Autowired
    OrderStatusValidation valOd;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public TotalCartValue billing(ProductDetailsList req) {
        TotalCartValue response = new TotalCartValue();

        Double discount;
        Double gst;
        Double mrp = 0d;

        Double packageCharge;
        Double totalPrice = 0d;
        Double totalGst = 0d;
        Double totalPkgCharges = 0d;
        Double totalDiscount = 0d;

        List<ProductDetails> products = req.getProducts();


        for (ProductDetails pd : products) {
            Double price;
            Integer pdId = pd.getProductId();

            StockDetails stockDetails = comm.getStockDetialsById(pdId);
            List<ChargesDetails> chargesDetails = new ArrayList<>();

            log.info("The stock details from external service call is --> " + stockDetails);
            String category = stockDetails.getCategory();
            chargesDetails = comm.getChargesDetails(category);


            Double tGst = 0d;
            Double tDiscount = 0d;
            Double tPkg = 0d;


            price = stockDetails.getCostPerUnit() * pd.getUnits();

            for (ChargesDetails tempCharges : chargesDetails) {
                if (tempCharges.getType().equals("GST")) {
                    tGst = price * tempCharges.getCharge() / 100;
                }

                if (tempCharges.getType().equals("DIS")) {
                    if (tempCharges.getChargeType().equals("F"))
                        tDiscount = tempCharges.getCharge();
                    else
                        tDiscount = price * tempCharges.getCharge() / 100;

                }
                if (tempCharges.getType().equals("PKG")) {
                    if (tempCharges.getChargeType().equals("F"))
                        tPkg = tempCharges.getCharge();
                    else
                        tPkg = price * tempCharges.getCharge() / 100;

                }

            }

            mrp = mrp + price;
            price = price + tGst + tPkg - tDiscount;
            totalGst = totalGst + tGst;
            totalDiscount = totalDiscount + tDiscount;
            totalPkgCharges = totalPkgCharges + tPkg;
            totalPrice = totalPrice + price;


        }

        OrderDetails orderDetails = new OrderDetails();
        OrderDetails oout = new OrderDetails();
        LocalDateTime date = LocalDateTime.now();

        String orderCode = "";
        orderDetails.setDiscount(totalDiscount);
        orderDetails.setPrice(mrp);
        orderDetails.setGst(totalGst);
        orderDetails.setPackageCharge(totalPkgCharges);
        orderDetails.setTotalPrice(totalPrice);
        orderDetails.setCreatedDate(date);
        orderDetails.setCustomerCode(req.getCustomerCode());
        oout = accOrderRepo.save(orderDetails);
        
        final Integer orderId=oout.getId();

        if (oout.getId() > 0) {
            orderCode = "OD0000" + String.valueOf(oout.getId());
            oout.setOrderCode(orderCode);
            oout = accOrderRepo.save(oout);
        }

        if (oout.getId() > 0) {
            for (ProductDetails pd : products) {
                int pId = pd.getProductId();
                int pCount = pd.getUnits();


                ItemCountsEntity ice = new ItemCountsEntity();

                ice.setItemId(pId);
                ice.setCounts(pCount);
                ice.setCreatedDate(date);
                ice.setStatus("N");
                ice.setOrderId(oout.getId());

                accOrderItemsRepo.save(ice);


            }

        }
        Runnable rn = new Runnable() {
        	@Override
        	public void run() {
        		
				for (ProductDetails pd : products) {
					Double price;
					Integer pdId = pd.getProductId();

					StockDetails stockDetails = comm.getStockDetialsById(pdId);
					List<ChargesDetails> chargesDetails = new ArrayList<>();

					log.info("The stock details from external service call is --> " + stockDetails);
					String category = stockDetails.getCategory();
					chargesDetails = comm.getChargesDetails(category);

					Double tGst = 0d;
					Double tDiscount = 0d;
					Double tPkg = 0d;

					price = stockDetails.getCostPerUnit() * pd.getUnits();

					for (ChargesDetails tempCharges : chargesDetails) {
						if (tempCharges.getType().equals("GST")) {
							tGst = price * tempCharges.getCharge() / 100;
						}

						if (tempCharges.getType().equals("DIS")) {
							if (tempCharges.getChargeType().equals("F"))
								tDiscount = tempCharges.getCharge();
							else
								tDiscount = price * tempCharges.getCharge() / 100;

						}
						if (tempCharges.getType().equals("PKG")) {
							if (tempCharges.getChargeType().equals("F"))
								tPkg = tempCharges.getCharge();
							else
								tPkg = price * tempCharges.getCharge() / 100;

						}

					}
					OrderWiseProductDetails owp = new OrderWiseProductDetails();
					owp.setDiscount(tDiscount);
					owp.setGst(tGst);
					owp.setCost(price);
					owp.setUntis(pd.getUnits());
					owp.setMrp(stockDetails.getCostPerUnit());
					owp.setPkgChg(tPkg);
					owp.setOrderId(orderId);
					owp.setProductId(pdId);
					owp.setTotalAmt(price + tGst + tPkg - tDiscount);

					owpRepo.save(owp);

				}
        		
        	}
        	
        	
        };
        Thread td = new Thread(rn);
        td.start();

        response.setPrice(mrp);
        response.setDiscount(totalDiscount);
        response.setGst(totalGst);
        response.setPackageCharge(totalPkgCharges);
        response.setTotalPrice(totalPrice);
        response.setOrderId(String.valueOf(oout.getId()));
        response.setOrderCode(orderCode);
        response.setCustomerCode(req.getCustomerCode());

        return response;
    }

    public CollectionResponse doCollection(CollectionRequest req) {
    	
    	OrderDetails od= new OrderDetails();
    	od=getOrderDetails(req.getOrderCode());
        CollectionResponse response = new CollectionResponse();
        OrderDetails odOut = new OrderDetails();
        odOut = valOd.validatedOrderStatus(od.getId());

        if (odOut.getOrderStatus().equals("T")) {
            String msg = "Your order number " + odOut.getOrderCode() + " is already paid ";
            response.setReturnCode("444");
            response.setReturnMsg(msg);
            return response;
        }


        LocalDateTime date = LocalDateTime.now();
        CollectionRequest out = new CollectionRequest();
        req.setStatus("I");
        req.setDate(date);
        req.setAmount(od.getPrice());
        req.setCustCode(od.getCustomerCode());
        req.setOrderId(od.getId());


        out = accCollRepo.save(req);
        String voucherNo = "";
        if (out.getId() > 0) {
            voucherNo = "MB00001" + String.valueOf(out.getId());
            out.setStatus("P");
            out.setVoucherNo(voucherNo);

        }
        out = accCollRepo.save(req);

        try {
            odOut = accOrderRepo.updateOrderStatus(req.getOrderId(), "T");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }



        Integer odId = req.getOrderId();

        List<ItemCountsEntity> iceList = new ArrayList<>();

        iceList = accOrderItemsRepo.findByOrderId(odId);

        for (ItemCountsEntity iceTemp : iceList) {

            String itemId = String.valueOf(iceTemp.getItemId());
            String itemCount = String.valueOf(iceTemp.getCounts());

            comm.updateProdCounts(itemId, itemCount);


        }

        try {

            EmployeeSales esales = new EmployeeSales();
            esales.setEmpId(52);
            esales.setAmount(req.getAmount());
            esales.setCustomerCode(req.getCustCode());
            epFC.updateEmployeeSales(esales);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        if (out.getId() < 0) {
            response.setReturnCode("444");
            response.setReturnMsg("Something went wrong");
            return response;
        }
        response.setReturnMsg("Updated");
        response.setReturnCode("201");
        response.setVoucherNo(voucherNo);
        response.setDate(date);

        return response;

    }

	public OrderDetails getOrderDetails(String orderCode) {
		// TODO Auto-generated method stub
		return accOrderRepo.findByOrderCode(orderCode);
	}
	
	public OrderDetailsResponse getOrderSummry(String orderCode) {
		OrderDetailsResponse odr = new OrderDetailsResponse();
		
		OrderDetails oout= accOrderRepo.findByOrderCode(orderCode);
		
		odr.setCreatedDate(oout.getCreatedDate());
		odr.setCustomerCode(oout.getCustomerCode());
		odr.setDiscount(oout.getDiscount());
		odr.setGst(oout.getGst());
		odr.setOrderCode(orderCode);
		odr.setOrderStatus(oout.getOrderStatus());
		odr.setPackageCharge(oout.getPackageCharge());
		odr.setPrice(oout.getPrice());
		odr.setTotalPrice(oout.getTotalPrice());
		Integer unitTotal=0;
		List<OrderWiseProductDetails> productDetailsList=owpRepo.findbyOrderId(oout.getId());
		List<OrderWiseProductDetailsDTO> productDetails= new ArrayList<>();
		for(OrderWiseProductDetails temp:productDetailsList) {
			OrderWiseProductDetailsDTO tempDto= new OrderWiseProductDetailsDTO();
			
			tempDto.setCost(temp.getCost());
			tempDto.setDiscount(temp.getDiscount());
			tempDto.setGst(temp.getGst());
			tempDto.setMrp(temp.getMrp());
			tempDto.setPkgChg(temp.getPkgChg());
			tempDto.setTotalAmt(temp.getTotalAmt());
			tempDto.setUnits(temp.getUntis());
			StockDetails stockDetails= new StockDetails();
			stockDetails=comm.getStockDetialsById(temp.getProductId());
			tempDto.setProductName(stockDetails.getBrand()+" "+stockDetails.getModel());
			unitTotal=unitTotal+temp.getUntis();
			productDetails.add(tempDto);
			odr.setProductDetails(productDetails);
		}
		
		
		CustomerDetails custDetails = new CustomerDetails();
		custDetails=comm.getCustDetailsByCode(oout.getCustomerCode());
		odr.setCustomerDetail(custDetails);
		odr.setUnits(unitTotal);
		
		
		
		
		return odr;
	}
}
