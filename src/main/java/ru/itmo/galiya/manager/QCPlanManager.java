package ru.itmo.galiya.manager;

import ru.itmo.galiya.QCFrequency;
import ru.itmo.galiya.base.MeasurementParam;
import ru.itmo.galiya.base.QCPlan;
import ru.itmo.galiya.validation.QCPlanValidation;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QCPlanManager {

    private final Map<Long, QCPlan> map = new HashMap<>();
    private long id = 1;

    private final QCPlanValidation validation = new QCPlanValidation();

    private long generationId() {
        return id++;
    }

    public QCPlan add(String name, MeasurementParam param, QCFrequency frequency, String ownerUsername) {
        long id = generationId();
        Instant now = Instant.now();

        QCPlan plan = new QCPlan(id, name, param, frequency, ownerUsername, now, now);

        validation.validate(plan);
        map.put(id, plan);
        return plan;
    }

    public QCPlan get(long id) {
        return map.get(id);
    }
    public Collection<QCPlan> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }
    public boolean exists(long id) {
        return map.containsKey(id);
    }
    public void remove(long id) {
        map.remove(id);
    }
}
