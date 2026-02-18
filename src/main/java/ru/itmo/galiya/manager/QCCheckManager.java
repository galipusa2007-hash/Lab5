package ru.itmo.galiya.manager;

import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.validation.QCCheckValidation;
import ru.itmo.galiya.validation.ValidationException;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QCCheckManager {
    private final Map<Long, QCCheck> map = new HashMap<>();

    private long id = 1;

    private final QCCheckValidation validation = new QCCheckValidation();

    private final SampleManager sampleManager;
    private final QCPlanManager planManager;
    public QCCheckManager(SampleManager sampleManager, QCPlanManager planManager) {
        this.sampleManager = sampleManager;
        this.planManager = planManager;
    }

    private long generateId() {
        return id++;
    }

    public QCCheck add(long sampleId, long planId, double value, String unit, QCStatus status, String ownerUsername, Instant createdAt, Instant checkedAtOrNull) {
        if (!sampleManager.exists(sampleId)) {
            throw new ValidationException("Ошибка: sampleId не существует");
        }
        if (!planManager.exists(planId)) {
        throw new ValidationException("Ошибка: planId не существует");
        }

        long id = generateId();
        Instant now = Instant.now();

        Instant checkedAt;
        if (checkedAtOrNull != null) {
            checkedAt = checkedAtOrNull;
        } else  {
            checkedAt = now;
        }

        QCCheck check = new QCCheck(id, sampleId, planId, value, unit, status, ownerUsername, checkedAt, createdAt, now);
        validation.validate(check);
        map.put(id, check);
        return check;
    }

    public QCCheck get(long id) {
        return map.get(id);
    }

    public Collection<QCCheck> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }

    public boolean exists(long id) {
        return map.containsKey(id);
    }

    public void remove(long id) {
        map.remove(id);
    }

}
