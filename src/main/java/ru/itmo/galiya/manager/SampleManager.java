package ru.itmo.galiya.manager;

import ru.itmo.galiya.base.Sample;
import ru.itmo.galiya.validation.SampleValidation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SampleManager {

    private final Map<Long, Sample> map = new HashMap<>();
    private long id = 1;

    private final SampleValidation validation = new SampleValidation();

    private long generateId() {
        return id++;
    }

    public Sample add() {
        long sampleId = generateId();
        Sample sample = new Sample(sampleId);

        validation.validate(sample);

        map.put(sampleId, sample);
        return sample;
    }

    public Sample get(long id) {
        return map.get(id);
    }
    public Collection<Sample> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }
    public boolean exists(long id) {
        return map.containsKey(id);
    }
    public void remove(long id) {
        map.remove(id);
    }

}


