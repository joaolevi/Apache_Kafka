package com.joaolevi.strconsumer.listeners;

import com.joaolevi.strconsumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @SneakyThrows
    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message) {
        log.info("CREATE ::: Receive message {}", message);
        throw new IllegalArgumentException("EXCEPTION...");
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message) {
        log.info("LOG ::: Receive message {}", message);
    }
}