package ecommerce.visacardserviceChecker1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ecommerce.visacardserviceChecker1.service.visacardService;

import java.io.FileNotFoundException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

@RestController
@RequestMapping("/api/visacardChecker1")
public class visacardController {

    @Autowired
    private visacardService visacardService;


    @GetMapping("/checkExpirationDate")
    public boolean checkExpirationDate(@RequestParam String expirationDate, @RequestParam String cardNumber){

        return visacardService.checkExpirationDate(expirationDate, cardNumber);
    }
    @GetMapping(value = "/VisaCards")
    public void getVisaCards() throws FileNotFoundException {

        visacardService.sendAsyncHttpRequests("visacardChecker/VisaCards");
        visacardService.sendAsyncHttpRequests("visacardMQ/VisaCards");

    }

    @Value("${eureka.instance.metadataMap.zone}")
    private String zone;

    @GetMapping(value = "/zone")
    public String zone() {
        return "{\"zone\"=\"" + zone + "\"}";
    }
}
