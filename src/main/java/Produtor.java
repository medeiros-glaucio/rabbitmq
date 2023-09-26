import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Produtor {

    public static void main(String[] args) throws  Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        try (
                Connection connection = connectionFactory.newConnection();
                Channel canal = connection.createChannel();

        ) {
            String mensagem = "Olá, cumprimentos de GLÁUCIO ALVES MEDEIROS!" + ".";
            String NOME_FILA = "replica";

            //(queue, passive, durable, exclusive, autoDelete, arguments)
            canal.queueDeclare(NOME_FILA, true, false, false, null);

            // ​(exchange, routingKey, mandatory, immediate, props, byte[] body)
            canal.basicPublish("",
                    NOME_FILA,
                    false,
                    false,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    mensagem.getBytes());

            System.out.println("Mensagem enviada: " + mensagem);
        }

    }

}


