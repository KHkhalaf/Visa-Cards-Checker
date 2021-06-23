package ecommerce.visacardserviceMQ.batchMQ;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ecommerce.visacardserviceMQ.model.visacard;
import ecommerce.visacardserviceMQ.service.visacardService;

public class receiver {
    private final static String QUEUE_NAME = "batchMQ";

    public void receive(visacardService visacardService) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // LESENNER
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            int index=0;
            String cardNumber, holderName, expiratinDate, cvv;
            System.out.println(message);

            //  Extracting the data from received message
            index = message.indexOf("|");
            cardNumber = message.substring(0, index);
            message = message.substring(index+1);
            index = message.indexOf("|");
            holderName = message.substring(0, index);
            message = message.substring(index+1);
            index = message.indexOf("|");
            expiratinDate = message.substring(0,index);
            cvv = message.substring(index+1);
            visacard visacard = new visacard(cardNumber,holderName,Integer.parseInt(cvv),expiratinDate);

            visacardService.saveVisaCard(visacard);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
