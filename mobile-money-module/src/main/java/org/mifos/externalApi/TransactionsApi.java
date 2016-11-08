package org.mifos.externalApi;

/**
 * Created by daniel on 11/2/16.
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.mifos.portfolio.Transactions;
import org.mifos.repos.TransactionsRepository;
import org.mifos.util.Init;


@RestController
public class TransactionsApi {

    @SuppressWarnings("unused")
    @CrossOrigin
    @RequestMapping(value="/mobile_mo/api/v1/loans", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<String> repayLaon(
            @RequestParam(value="phone", required=true)long phoneNumber,
            @RequestParam(value="amount", required=true) long amount,
            @RequestParam(value="clientId", required=true)int clientID,
            @RequestParam(value="accountId", required=true)int accountID){

        //final String url = "http://api.furthermarket.com/FM/MTN/MoMo/Requestpayment?MyaccountID={accountNo}&CustomerPhonenumber=237{phone}&Amount={amount}&ItemDesignation=%22trans%22&ItemDescription=%22%22";

        final String url = Init.getMomoWithdrawalsUrl();

        Map<String, String> params = new HashMap<String, String>();

        params.put("accountNo", String.valueOf(accountID));
        params.put("phone", String.valueOf(phoneNumber));
        params.put("amount", String.valueOf(amount));


        try{
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class, params);
            System.out.println("result: " + response);

		    /*
			 * error handling: rudimentary but necessary
			 */
            if(response.contains("error")){
                System.out.println("Error making request to Mobile money api.");
                return new ResponseEntity<String>("\"Loan repayments failure\"", HttpStatus.EXPECTATION_FAILED);
            }

			/*
			 * Since transaction was successfully  carried out save it to the database
			 */

            Transactions trans = new Transactions();

            trans.setAmount((int) amount);
            //trans.setClient_id(clientID);
            trans.setDate(new Date());
            trans.setType("Loan repayments");
            trans.setOffice(trans.getOffice());
            trans.setStaff(trans.getStaff());
            //trans.setRecipient("null");

            // Making sure values are saved in the database.
            try{
                transactionRepo.save(trans);
            } catch(Exception ex){
                System.out.println("Saving to database error in repayments: " + ex.getMessage());
                return new ResponseEntity<String>("\"Error saving to database in repayments\"", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<String>("\"Repayments success\"", HttpStatus.OK);
        } catch(Exception ex){
            System.out.println("Error making request to Mobile money api.\nErrorMessage: " + ex.getMessage());
            return new ResponseEntity<String>("\"Loan repayments failure\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Data repository.
    @Autowired
    private TransactionsRepository transactionRepo;
}

