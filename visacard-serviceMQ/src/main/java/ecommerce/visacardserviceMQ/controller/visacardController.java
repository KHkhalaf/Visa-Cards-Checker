package ecommerce.visacardserviceMQ.controller;

import ecommerce.visacardserviceMQ.batchMQ.receiver;
import ecommerce.visacardserviceMQ.model.visacard;
import ecommerce.visacardserviceMQ.service.visacardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/visacardMQ")
public class visacardController {

    @Autowired
    private visacardService visacardService;

    @Autowired
    Environment environment;

    @GetMapping("/runRabbitMQ")
    public String runRabbitMQ() throws Exception {

        String serverPort = environment.getProperty("local.server.port");

        System.out.println("Port : " + serverPort);
        new receiver().receive(visacardService);

        return "";
    }
    @GetMapping("/VisaCards")
    public List<visacard> getVisaCards() throws Exception {

        new receiver().receive(visacardService);

        return visacardService.getAll();
    }

    @Value("${eureka.instance.metadataMap.zone}")
    private String zone;

    @GetMapping(value = "/zone")
    public String zone() {
        return "{\"zone\"=\"" + zone + "\"}";
    }
}
