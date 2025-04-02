package ru.c_energies.update;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.c_energies.databases.entity.version.VersionChange;
import ru.c_energies.databases.entity.version.VersionRow;
import ru.c_energies.databases.entity.version.VersionTable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.List;

/**
 * Скачиваем из репы файл по последней версии
 */
public class UpdateApp {
    private final Logger LOG = LogManager.getLogger(UpdateApp.class);
    private final String url = "https://gitlab.com/api/v4/projects/67038642/packages/generic/autonomy-register/%s/web-1.0-SNAPSHOT.jar";
    public void start() throws SQLException {
        LOG.info("Сравниваем версии приложения");
        //Смотрим версию по tag в https://gitlab.com/autonomy-register/autonomy-register-v1.git
        WorkGit workGit = new WorkGit("https://gitlab.com/autonomy-register/autonomy-register-v1.git");
        String lastTag = workGit.lastTag();
        List<VersionRow> versionRowList = new VersionTable().list();
        String currentVersion = !versionRowList.isEmpty() ? versionRowList.get(0).value() : "";
        if(!currentVersion.equals(lastTag)){
            LOG.info("Начинаем обновление приложения");
            //Скачиваем jar-файл и пытаемся заменить
            String rootPath = Paths.get("").toAbsolutePath().toString() + "/web-1.0-SNAPSHOT.jar";
            Path path = Paths.get(rootPath);
            WebClient client = WebClient.builder()
                    .baseUrl(String.format(this.url, lastTag))
                    .build();
            Flux<DataBuffer> dataBufferFlux = client.get().retrieve().bodyToFlux(DataBuffer.class);
            DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE).block(); //Creates new file or overwrites exisiting file
            if(!versionRowList.isEmpty()){
                new VersionChange(lastTag).update();
            }else{
                new VersionChange(lastTag).insert();
            }
            //Для завершения обновления, Вам необходимо вручную перезапустить приложение
            LOG.info("Обновление приложения завершено");
        }

    }
}
