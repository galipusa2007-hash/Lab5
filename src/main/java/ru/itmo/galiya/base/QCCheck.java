package ru.itmo.galiya.base;

import ru.itmo.galiya.QCStatus;
import java.time.Instant;

public final class QCCheck {

    // Уникальный номер проверки. Программа назначает сама.
    private long id;

    // Какой образец проверяли (id образца).
    // Должен ссылаться на реально существующий Sample.
    private final long sampleId;

    // По какому плану проверяем (id плана).
    // Должен ссылаться на реально существующий QCPlan.
    private final long planId;

    // Значение измерения для QC (число).
    private final double value;

    // Единицы (например "pH"). Нельзя пустое. До 16 символов.
    private final String unit;

    // Статус проверки: PASS или FAIL.
    private QCStatus status;

    // Кто выполнил проверку (логин). На ранних этапах можно "SYSTEM".
    private final String ownerUsername;

    // Когда выполнили. Если не вводят — текущее время.
    private final Instant checkedAt;

    // Когда запись создана. Программа ставит автоматически.
    private final Instant createdAt;

    public QCCheck(long id, long sampleId, long planId, double value, String unit, QCStatus status, String ownerUsername, Instant checkedAt, Instant createdAt) {
        this.id = id;
        this.sampleId = sampleId;
        this.planId = planId;
        this.value = value;
        this.unit = unit;
        this.status = status;
        this.ownerUsername = ownerUsername;
        this.checkedAt = checkedAt;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }
    public long getSampleId() {
        return sampleId;
    }
    public long getPlanId() {
        return planId;
    }
    public double getValue() {
        return value;
    }
    public String getUnit() {
        return unit;
    }
    public QCStatus getStatus() {
        return status;
    }
    public String getOwnerUsername() {
        return ownerUsername;
    }
    public Instant getCheckedAt() {
        return checkedAt;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setStatus(QCStatus status) {
        this.status = status;
    }
}