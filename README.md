#Apache Kafka Projects

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
