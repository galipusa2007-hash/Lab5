package ru.itmo.galiya.manager;

import ru.itmo.galiya.base.QCLimit;
import ru.itmo.galiya.validation.QCLimitValidation;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class QCLimitManager {
    private final Map<Long, QCLimit> map = new HashMap<>();
    private long id = 1;
    Instant now = Instant.now();

    private final QCLimitValidation validation = new QCLimitValidation();

    private long generationId() {
        return id++;
    }

    public QCLimit add(QCPlanManager planId, double minValue, double maxValue, Instant updatedAt) {
        long id = generationId();

        QCLimit limit = new QCLimit(id, planId, minValue, maxValue, updatedAt);
        validation.validateQCLimit(limit, planId);

        map.put(id, limit);
        return limit;
    }

    public QCLimit get(long id) {
        return map.get(id);
    }


    public QCLimit remove(long id) {
        return map.remove(id);
    }
}
