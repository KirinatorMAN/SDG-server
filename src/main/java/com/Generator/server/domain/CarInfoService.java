package com.Generator.server.domain;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.entry;

@Service
public class CarInfoService {

    // DB repository mock
    CarInfo cartest = new CarInfo(1L,1,60, new CoordInfo(15,16));

    private Map<Long, Map<String,Object>> repository = new HashMap<>();

    public CarInfoService(){
        repository.put(cartest.getId(),Map.ofEntries(
                entry("numbCar",cartest.getNumbCar()),
                entry("speed",cartest.getSpeed()),
                entry("coord",cartest.getCoord())));
    }

    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(3);

    public Map<Long, Map<String,Object>> readAll() {
        return repository;
    }

    public Map<String,Object> read(Long id) {
        return repository.get(id);
    }

    public CarInfo create(CarInfo request) {
        long key = sequence.incrementAndGet();
        request.setId(key);
        repository.put(request.getId(), Map.ofEntries(
                entry("numbCar",request.getNumbCar()),
                entry("speed",request.getSpeed()),
                entry("coords",request.getCoord())));
        return request;
    }

    public Map<String,Object> update(Long id, CarInfo car) {
        car.setId(id);
        Map<String,Object> newcar = Map.ofEntries(
                entry("numbCar",car.getNumbCar()),
                entry("speed",car.getSpeed()),
                entry("coords",car.getCoord()));
        Map<String,Object> oldStudent = repository.replace(id, newcar);
        return oldStudent == null ? null : newcar;
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}
