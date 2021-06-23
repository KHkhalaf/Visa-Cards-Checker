package ecommerce.visacardserviceChecker1.service;


import ecommerce.visacardserviceChecker1.model.visacard;
import ecommerce.visacardserviceChecker1.repository.visacardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class visacardService {

    @Autowired
    private visacardRepository repo;

    public boolean checkExpirationDate(String expirationDate,String cardNumber){

        List<visacard> visacards = repo.findAll();
        for (visacard visaCard : visacards)
            if (visaCard.getCard_number().equals(cardNumber))
                if(Integer.valueOf(visaCard.getExpiration_date().substring(0,4)) < Integer.valueOf(expirationDate))
                    return true;
        return false;
    }
    @Async
    public CompletableFuture<String> sendAsyncHttpRequests(String restUrl){
        URI uri = URI.create("http://localhost:8001/api/"+restUrl);
        String response = new RestTemplate().getForObject(uri, String.class);
        System.out.println("------"+response+" ------");
        return CompletableFuture.completedFuture("------"+response+" ------");
    }
}
