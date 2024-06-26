package org.gox.bsdata.wh40k.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.gox.bsdata.wh40k.model.catalogue.Catalogue;
import org.gox.bsdata.wh40k.model.catalogue.GithubFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BsDataService {

    private static final Logger logger = LoggerFactory.getLogger(BsDataService.class);

    private static final String BS_DATA_WH_40K_REPO_URL = "https://api.github.com/repos/BSData/wh40k-10e/contents";
    private static final String FILE_TYPE = "file";
    private static final String CAT_FILE_EXTENSION = ".cat";
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;

    @Cacheable("armies")
    public List<GithubFile> getBsDataCataloguesUrls() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BS_DATA_WH_40K_REPO_URL))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("GET armies status code {}", response.statusCode());

        List<GithubFile> list = objectMapper.readValue(response.body(), new TypeReference<>(){});
        AtomicLong id = new AtomicLong(1);
        return list.stream()
                .filter(githubFile -> githubFile.getName() != null)
                .filter(githubFile -> FILE_TYPE.equals(githubFile.getType()))
                .filter(githubFile -> githubFile.getName().endsWith(CAT_FILE_EXTENSION))
                .sorted(Comparator.comparing(GithubFile::getName))
                .peek(githubFile -> {
                    githubFile.setName(githubFile.getName().replace(CAT_FILE_EXTENSION, ""));
                    githubFile.setId(id.getAndIncrement());
                })
                .toList();
    }

    @Cacheable("catalogues")
    public Optional<Catalogue> getCatalog(long armyId) throws URISyntaxException, IOException, InterruptedException {
        Optional<String> urlOpt = this.getBsDataCataloguesUrls().stream()
                .filter(githubFile -> githubFile.getId() == armyId)
                .findFirst()
                .map(GithubFile::getDownload_url);

        if (urlOpt.isPresent()) {
            return getCatalog(urlOpt.get());
        }
        return Optional.empty();
    }

    private Optional<Catalogue> getCatalog(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            logger.info("GET army status code {}", response.statusCode());

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return Optional.of(xmlMapper.readValue(response.body(), Catalogue.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
