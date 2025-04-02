package ru.c_energies.update;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;

import java.util.*;

/**
 * help - https://github.com/centic9/jgit-cookbook/blob/master/src/main/java/org/dstadler/jgit/porcelain/ListRemoteRepository.java
 */
public class WorkGit {
    private final Logger LOG = LogManager.getLogger(WorkGit.class);
    private final String repo;
    public WorkGit(String repo){
        this.repo = repo;
    }
    public String lastTag() {
        Map<String, Ref> map = null;
        try {
            map = Git.lsRemoteRepository()
                    .setHeads(false)
                    .setTags(true)
                    .setRemote(this.repo)
                    .callAsMap();
        } catch (Exception e) {
            LOG.debug(e, e.fillInStackTrace());
            throw new RuntimeException(e);
        }
        List<String> listTags = new ArrayList<>();
        for (Map.Entry<String, Ref> entry : map.entrySet()) {
            listTags.add(entry.getKey().replace("refs/tags/", ""));
            LOG.debug("Key: " + entry.getKey() + ", Ref: " + entry.getValue());
        }
        Collections.sort(listTags);
        Collections.reverse(listTags);
        String lastTag = listTags.size() > 0 ? listTags.get(0) : "";
        LOG.debug("last tag = {}", lastTag);
        return lastTag;
    }
}
