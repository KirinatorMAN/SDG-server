package com.Generator.server.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Map.entry;

@Service
public class CarInfoService {

    private final Map<Long, Map<String, Object>> repository = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public CarInfoService() {
    }

    public Map<Long, Map<String, Object>> readAll() {
        return repository;
    }

    public List<Map<String, Object>> read(int id) {
        List<Map<String, Object>> map = new ArrayList<>();
        for (Object o : repository.values()) {
            if (o instanceof Map) {
                Map<String, Object> temp = convertObjToMap(o, new TypeReference<>() {
                });
                if (temp.get("numbCar").equals(id))
                    map.add(temp);
            }
        }
        return map;
    }

    public CarInfo create(CarInfo request) {
        long key = sequence.incrementAndGet();
        request.setId(key);
        repository.put(request.getId(), Map.ofEntries(
                entry("numbCar", request.getNumbCar()),
                entry("speed", request.getSpeed()),
                entry("coords", request.getCoord())));
        return request;
    }
    public <T> T convertObjToMap(Object o, TypeReference<T> ref) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(o, ref);
    }
}