package com.Generator.server.rest;

import com.Generator.server.domain.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Generator.server.domain.CarInfoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {

    @Autowired
    private CarInfoService response;

    @PostMapping("/{numbCar}")
    public ResponseEntity<CarInfo> addCarInfo(@RequestBody CarInfo request) {
        CarInfo createdCarInfo = response.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCarInfo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdCarInfo);
    }

    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, Map<String,Object>> allCarInfo() {
        return response.readAll();
    }

    @GetMapping(value = "/",params = {"numbCar"})
    public List<Map<String, Object>> read(@RequestParam final String numbCar) {
        return response.read(Integer.parseInt(numbCar));
    }

}