package ru.itmo.galiya.manager;

import ru.itmo.galiya.base.*;
import ru.itmo.galiya.validation.*;

import java.util.*;

public class MeasurementParamManager {

    private final Map<Long, MeasurementParam> map = new HashMap<>();
    private long id = 1;

    private final MeasurementParamValidation validation = new MeasurementParamValidation();

    private long generationId() {
        return id++;
    }

    public MeasurementParam add(String name, String description) {
        long id = generationId();

        MeasurementParam param = new MeasurementParam(id, name, description);

        validation.validate(param);
        map.put(id, param);
        return param;
    }

    public MeasurementParam get(long id) {
        return map.get(id);
    }
    public Collection<MeasurementParam> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }
    public boolean exists(long id) {
        return map.containsKey(id);
    }

    public Map<Long, MeasurementParam> exportData() {
        return new HashMap<>(map);
    }
    public void replace(Map<Long, MeasurementParam> newData) {
        map.clear();
        map.putAll(newData);

        long maxId = 0;
        for (Long currentId : map.keySet()) {
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        id = maxId + 1;
    }
}
