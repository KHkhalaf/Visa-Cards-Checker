package ecommerce.visacardserviceChecker.controller;

import ecommerce.visacardserviceChecker.batchMQ.sender;
import ecommerce.visacardserviceChecker.model.visacard;
import ecommerce.visacardserviceChecker.service.MQServiceDelegate;
import ecommerce.visacardserviceChecker.service.visacardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api/visacardChecker")
public class visacardController {

    @Autowired
    private visacardService visacardService;

    @Autowired
    private MQServiceDelegate mqServiceDelegate;
    @GetMapping("/isValid")
    public boolean isValid(@RequestParam String cardNumber){

        return visacardService.isValid(cardNumber);
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public String register(@RequestBody visacard visaCard, HttpServletResponse response){

        sender sender = new sender();
        sender.send(visaCard);
        return "completed !!!";
    }


    @GetMapping("/VisaCards")
    public String getVisaCards() {

       return mqServiceDelegate.callMQServiceAndSendData();
    }

    @Value("${eureka.instance.metadataMap.zone}")
    private String zone;

    @GetMapping(value = "/zone")
    public String zone() {
        return "{\"zone\"=\"" + zone + "\"}";
    }
}
