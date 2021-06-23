package ecommerce.visacardserviceChecker.batchMQ;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ecommerce.visacardserviceChecker.model.visacard;
import ecommerce.visacardserviceChecker.service.MQServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class sender {

    private final static String QUEUE_NAME = "batchMQ";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private MQServiceDelegate mqServiceDelegate;

    public void send(visacard visaCard){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        new RestTemplate().getForObject("http://localhost:8001/api/visacardMQ/runRabbitMQ", String.class);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = visaCard.getCard_number()+"|"+visaCard.getHolder_name()+"|"+visaCard.getExpiration_date()+"|"+visaCard.getCvv() ;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
