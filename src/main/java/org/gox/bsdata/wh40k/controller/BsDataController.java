package org.gox.bsdata.wh40k.controller;

import lombok.RequiredArgsConstructor;
import org.gox.bsdata.wh40k.model.catalogue.Catalogue;
import org.gox.bsdata.wh40k.model.catalogue.GithubFile;
import org.gox.bsdata.wh40k.service.BsDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "bsdata/wh40k")
@RequiredArgsConstructor
public class BsDataController {

    private final BsDataService bsDataService;

    @GetMapping(path = "/armies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GithubFile>> getArmies() throws URISyntaxException, IOException, InterruptedException {
        return  new ResponseEntity<>(bsDataService.getBsDataCataloguesUrls(), HttpStatus.OK);
    }

    @GetMapping(path = "/armies/{armyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Catalogue> getArmy(@PathVariable("armyId") long armyId) throws URISyntaxException, IOException, InterruptedException {
        return bsDataService.getCatalog(armyId)
                .map(catalogue -> new ResponseEntity<>(catalogue, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
