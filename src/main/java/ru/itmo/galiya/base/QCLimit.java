package ru.itmo.galiya.base;

import java.time.Instant;

public final class QCLimit {

    // Уникальный номер предела. Программа назначает сама.
    private long id;

    // Для какого плана пределы (id плана).
    // Должен ссылаться на реально существующий QCPlan.
    private final long planId;

    // Нижний предел (min). Может быть -inf, но обычно число.
    private final double minValue;

    // Верхний предел (max). Обычно число.
    private final double maxValue;

    // Когда установили/обновили пределы. Программа ставит автоматически.
    private final Instant updatedAt;

    public QCLimit(long id, long planId, double minValue, double maxValue, Instant updatedAt) {
        this.id = id;
        this.planId = planId;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }
    public long getPlanId() {
        return planId;
    }
    public double getMinValue() {
        return minValue;
    }
    public double getMaxValue() {
        return maxValue;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(long id) {
        this.id = id;
    }
}
