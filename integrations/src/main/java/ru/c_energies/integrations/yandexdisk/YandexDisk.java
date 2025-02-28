package ru.c_energies.integrations.yandexdisk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;
import ru.c_energies.utils.converters.DateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class YandexDisk {
    private final Logger LOG = LogManager.getLogger(YandexDisk.class);
    private final String token;
    private final String dirDist;
    private final String host = "https://webdav.yandex.ru";
    public YandexDisk(String token, String dirDist){
        this.dirDist = dirDist;
        this.token = token;
    }
    public void upload() throws IOException {
        LOG.debug("Start method upload");
        String rotateDate = new DateFormat((int)Instant.now().getEpochSecond()).convert();
        String uri = this.dirDist+"reestr.db"+"-"+rotateDate;
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator()+"reestr.db";
        LOG.info("file path = {}", filePath);
        File fileBin = new File(filePath);
        InputStreamResource fileInputStream = new InputStreamResource(new FileInputStream(fileBin));
        final WebClient client = WebClient.create(this.host);
        Object httpStatusMono = client.put()
                .uri(uri)
                .header("Authorization", "OAuth "+this.token)
                .body(BodyInserters.fromResource(fileInputStream))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    response.bodyToMono(String.class)
                            .publishOn(Schedulers.elastic())
                            .subscribe(body -> LOG.error("Error send message code = {}, body = {}", response.statusCode().value(), body));
                    return Mono.empty();
                })
                .bodyToMono(Object.class).retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000)))
                .block();

        LOG.debug("End method upload");
    }
}
