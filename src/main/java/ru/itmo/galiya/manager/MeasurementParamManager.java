package ru.itmo.galiya.manager;

import ru.itmo.galiya.base.MeasurementParam;
import ru.itmo.galiya.validation.MeasurementParamValidation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MeasurementParamManager {

    private final Map<Long, MeasurementParam> map = new HashMap<>();
    private long Id = 1;

    private final MeasurementParamValidation validation = new MeasurementParamValidation();

    private long generationId() {
        return Id++;
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

    public void remove(long id) {
        map.remove(id);
    }
}
