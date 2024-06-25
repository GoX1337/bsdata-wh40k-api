package org.gox.bsdata.wh40k.controller;

import lombok.RequiredArgsConstructor;
import org.gox.bsdata.wh40k.model.catalogue.Catalogue;
import org.gox.bsdata.wh40k.model.catalogue.GithubFile;
import org.gox.bsdata.wh40k.service.BsDataService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "bsdata/wh40k")
@RequiredArgsConstructor
public class BsDataController {

    private final BsDataService bsDataService;

    @GetMapping(path = "/armies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GithubFile> getArmies() throws URISyntaxException, IOException, InterruptedException {
        return bsDataService.getBsDataCataloguesUrls();
    }
}
