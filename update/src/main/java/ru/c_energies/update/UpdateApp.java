package ru.c_energies.update;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Скачиваем из репы файл по последней версии
 */
public class UpdateApp {
    private final String url = "https://gitlab.com/api/v4/projects/67038642/packages/generic/autonomy-register/%s/web-1.0-SNAPSHOT.jar";
    public void start(){
        //Смотрим версию по tag в https://gitlab.com/autonomy-register/autonomy-register-v1.git
        WorkGit workGit = new WorkGit("https://gitlab.com/autonomy-register/autonomy-register-v1.git");
        String lastTag = workGit.lastTag();
        //Скачиваем jar-файл и пытаемся заменить
        String rootPath = Paths.get("").toAbsolutePath().toString() + "/web-1.0-SNAPSHOT.jar";
        Path path = Paths.get(rootPath);
        WebClient client = WebClient.builder()
                .baseUrl(String.format(this.url, lastTag))
                .build();
        Flux<DataBuffer> dataBufferFlux = client.get().retrieve().bodyToFlux(DataBuffer.class);
        DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE).block(); //Creates new file or overwrites exisiting file

        //Для завершения обновления, Вам необходимо вручную перезапустить приложение
    }
}
