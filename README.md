# Apache Kafka Projects

This repository is to save a message producer and cosumer project and a payment project. 

### Techs used
- Apache Kafka (Kafdrop)
- Docker (Docker-Compose)
- Zookeeper
- Postman
- SpringBoot
- Java
- WSL (Linux on Windows)

Also, if you don't have IntelliJ license, you can create and run SpringBoot projects using the 'Sping Initializr Java Support' and 'Spring Boot Tools' extension in VS Code. They were built by Microsoft. 

--------------------

## Producer and Consumer Project

The objective with this project is to show how we can produce and consume messages using Apache Kafka with **Kafdrop** interface. 

We will follow this flow at below:

[![FlowDiagram](https://github.com/joaolevi/Apache_Kafka/blob/main/images/fluxo.png?raw=true "FlowDiagram")](https://github.com/joaolevi/Apache_Kafka/blob/main/images/fluxo.png?raw=true "FlowDiagram")

#### Running the Docker-Compose

The first step is run the docker-compose where the Zookeeper, Kafka and Kafdrop are ran.

If you have WSL installed with docker and docker-compose dependencies installed, just run in the command line `docker-compose up -d`. The `-d` is used to say to Docker to run it in the background and keep the command line free. 

You will see in the Docker Desktop something like this:

[![docker-compose](https://github.com/joaolevi/Apache_Kafka/blob/main/images/containers-docker.png?raw=true "docker-compose")](https://github.com/joaolevi/Apache_Kafka/blob/main/images/containers-docker.png?raw=true "docker-compose")

#### Kafdrop
To see the Kafdrop interface, you can access it in your browser at `localhost:19000`

[![kafdrop](https://github.com/joaolevi/Apache_Kafka/blob/main/images/kafkadrop-first.png?raw=true "kafdrop")](https://github.com/joaolevi/Apache_Kafka/blob/main/images/kafkadrop-first.png?raw=true "kafdrop")


#### Running the Spring projects
Next, to run the project you need to be in the producer directory and to click in the play button and in another vscode you need to be in the consumer directory and do the same.  It will create a topic at Kafdrop named by 'str-topic' as we can see in the image above.

#### The Request using Postman
With all things running fine, we finally can send a request to the producer that will be serialized and saved at Apache Kafka topic. 

The endpoint was wrote in the StringProducerResource.java. As we can see in the code below, the request is a `POST` and the enpoint is `/producer`. 

```
@RestController
@RequestMapping(value = "/producer")
public class StringProducerResource {

    private final StringProducerService producerService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
```

So, the complete URL will be: `localhost:8000/producer` because the producer are listen in the door 8000 and it will accept a raw JSON. 

The Producer is waiting a key named `message` and a value (string).  The raw JSON must be like:

[![postman](https://github.com/joaolevi/Apache_Kafka/blob/main/images/postman-first.png?raw=true "postman")](https://github.com/joaolevi/Apache_Kafka/blob/main/images/postman-first.png?raw=true "postman")

If the request was returned as 'CREATED' the request was OK. 

#### Message in Kafdrop
To see the message consumed by the consumer, you can open the Kafdrop, click in the str-topic, see messages, and you will see the messages in the topics:

[![kafdrop_with_message](https://github.com/joaolevi/Apache_Kafka/blob/main/images/kafkadrop-with-message.png?raw=true "kafdrop_with_message")](https://github.com/joaolevi/Apache_Kafka/blob/main/images/kafkadrop-with-message.png?raw=true "kafdrop_with_message")

#### Consuming the Message
As the message in Kafdrop was saved, the consumer can take the massage from there if it knows which **topic, partition and offset** the message is in. As we created just 2 partitions in KafkaAdminConfig.java (.partitions(**2**)) as we can see in the code below, the message can be save in the partition 0 or 1, depending on the Kafka system or if you set it. 

```
    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("str-topic").partitions(2).replicas(1).build()
        );
    }
```

To see the message being consumed by the Consumer, we can send another message:

```
{
    "message":"Apache Kafka repository"
}
```
Taking a look on the terminal where the Consumer is running, we can see the message be consumed

[![](https://github.com/joaolevi/Apache_Kafka/blob/main/images/consumindo_msg.png?raw=true)](https://github.com/joaolevi/Apache_Kafka/blob/main/images/consumindo_msg.png?raw=true)

-----------------------------------------------

## The Payment-Service Project

This project is very similar with the Producer and Consumer project. The difference is in the JSON message. Here, I prepared the software to receive a complete JSON object.

The main objective is to manage more than one informations received by requests and to create a Collection on Postman.

#### JSON Object

The body to be sent in the request needs to have the fields:

- id
- idUser
- idProduct
- cardNumber

Now, the request must be like this:

```
{
    "id":111,
    "idUser":22222,
    "idProduct":3,
    "cardNumber":"4444 5555 6666 7777 8888"
}
```

The request must use another endpoint: `/payments`

So, the complete URL will be: `localhost:19000/payments`

#### Receiving the Payment

If the payment was sent (request), the consumer from the payment-service software should confirm if the data (JSON Body) is correct and validate the payment.

[![](https://github.com/joaolevi/Apache_Kafka/blob/main/images/payment-recived-terminal.png?raw=true)](https://github.com/joaolevi/Apache_Kafka/blob/main/images/payment-recived-terminal.png?raw=true)

#### Kafdrop saving payments request

Now, we can see the requests done in the payment-topic on Kafdrop in the `localhost:19000`. The last one was saved on the Partition 0 and offset 2

[![](https://github.com/joaolevi/Apache_Kafka/blob/main/images/kafka_received_payments.png?raw=true)](https://github.com/joaolevi/Apache_Kafka/blob/main/images/kafka_received_payments.png?raw=true)
