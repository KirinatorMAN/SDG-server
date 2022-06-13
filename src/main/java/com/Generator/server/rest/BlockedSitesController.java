package com.Generator.server.rest;

import com.Generator.server.domain.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Generator.server.domain.CarInfoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BlockedSitesController {

    @Autowired
    private CarInfoService response;

    @PostMapping("/")
    public ResponseEntity<CarInfo> addblockedSite(@RequestBody CarInfo request) throws URISyntaxException {

        CarInfo createdCarInfo = response.create(request);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCarInfo.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdCarInfo);

    }

    @GetMapping("/")
    public List<CarInfo> blockedSites() {
        return response.readAll();
    }

}