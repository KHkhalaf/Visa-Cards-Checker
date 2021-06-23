package ecommerce.visacardserviceChecker.service;
import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.List;
@Service
public class MQServiceDelegate {

    @Autowired
    private
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callMQServiceAndSendData_Fallback")
    public String callMQServiceAndSendData() {
        URI uri = URI.create("http://localhost:8001/api/visacardMQ/VisaCards");
        List response = new RestTemplate().getForObject(uri, List.class);

        System.out.println("Response Received as " + response + " -  " + new Date());

        return "----------- NORMAL FLOW !!! " + " ----------  " + new Date();
    }


    public String callMQServiceAndSendData_Fallback() {

        System.out.println("the Service is down!!! fallback route enabled...");

        return " No Response From this Service at this moment. " +
                "the Service will be back soon... - " + new Date();
    }
}
