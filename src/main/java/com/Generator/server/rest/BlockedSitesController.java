package com.Generator.server.rest;

import com.Generator.server.domain.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Generator.server.domain.CarInfoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

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
    public Map<Long, Map<String,Object>> blockedSites() {
        return response.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> read(@PathVariable("id") Long id) {
        Map<String,Object> foundStudent = response.read(id);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundStudent);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        response.delete(id);

        return ResponseEntity.noContent().build();
    }
}