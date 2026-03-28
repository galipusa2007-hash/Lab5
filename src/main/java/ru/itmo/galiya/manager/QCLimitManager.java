package ru.itmo.galiya.manager;

import ru.itmo.galiya.base.*;
import ru.itmo.galiya.validation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QCLimitManager {
    private final Map<Long, QCLimit> map = new HashMap<>();
    private long id = 1;

    private final QCLimitValidation validation = new QCLimitValidation();
    private final QCPlanManager planManager;
    public QCLimitManager(QCPlanManager planManager) {
        this.planManager = planManager;
    }

    private long generationId() {
        return id++;
    }

    public QCLimit add(long planId, double minValue, double maxValue) {
        long id = generationId();
        Instant now = Instant.now();

        QCLimit limit = new QCLimit(id, planId, minValue, maxValue, now);
        validation.validate(limit, planManager);

        map.put(id, limit);
        return limit;
    }

    public QCLimit get(long id) {
        return map.get(id);
    }
    public Collection<QCLimit> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }
    public Map<Long, QCLimit> exportData() {
        return new HashMap<>(map);
    }
    public void replace(Map<Long, QCLimit> newData) {
        map.clear();
        map.putAll(newData);

        long maxId = 0;
        for (Long currentId : map.keySet()) {
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        id = maxId;
    }
}
