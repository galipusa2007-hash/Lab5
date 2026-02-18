package ru.itmo.galiya.base;

import ru.itmo.galiya.QCStatus;
import java.time.Instant;

public final class QCCheck {

    // Уникальный номер проверки. Программа назначает сама.
    private long id;

    // Какой образец проверяли (id образца).
    // Должен ссылаться на реально существующий Sample.
    private long sampleId;

    // По какому плану проверяем (id плана).
    // Должен ссылаться на реально существующий QCPlan.
    private long planId;

    // Значение измерения для QC (число).
    private double value;

    // Единицы (например "pH"). Нельзя пустое. До 16 символов.
    private String unit;

    // Статус проверки: PASS или FAIL.
    private QCStatus status;

    // Кто выполнил проверку (логин). На ранних этапах можно "SYSTEM".
    private String ownerUsername;

    // Когда выполнили. Если не вводят — текущее время.
    private Instant checkedAt;

    // Когда запись создана. Программа ставит автоматически.
    private Instant createdAt;

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

    public QCCheck(long id, long sampleId, long planId, double value, String unit, QCStatus status, String ownerUsername, Instant checkedAt, Instant createdAt, Instant now) {
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

    public QCCheck(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSampleId(long sampleId) {
        this.sampleId = sampleId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setStatus(QCStatus status) {
        this.status = status;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setCheckedAt(Instant checkedAt) {
        this.checkedAt = checkedAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}