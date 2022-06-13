package com.Generator.server.domain;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CarInfoService {

    // DB repository mock
    private Map<Long, CarInfo> repository = Arrays.asList( new CarInfo(1L, "https://telegram.org/")
//                    new CarInfo[]{
//                            new CarInfo(1L, "https://telegram.org/"),
//                            new CarInfo(2L, "https://azure.microsoft.com/"),
//                            new CarInfo(3L, "https://vk.com/"),
//                    }
            ).stream()
            .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));

    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(3);

    public List<CarInfo> readAll() {
        return repository.values().stream().collect(Collectors.toList());
    }

    public CarInfo read(Long id) {
        return repository.get(id);
    }

    public CarInfo create(CarInfo request) {
        long key = sequence.incrementAndGet();
        request.setId(key);
        repository.put(key, request);
        return request;
    }

    public CarInfo update(Long id, CarInfo student) {
        student.setId(id);
        CarInfo oldStudent = repository.replace(id, student);
        return oldStudent == null ? null : student;
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}
