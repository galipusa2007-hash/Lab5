package ru.itmo.galiya.base;

import ru.itmo.galiya.manager.QCPlanManager;

import java.time.Instant;

public final class QCLimit {

    // Уникальный номер предела. Программа назначает сама.
    private long id;

    // Для какого плана пределы (id плана).
    // Должен ссылаться на реально существующий QCPlan.
    private long planId;

    // Нижний предел (min). Может быть -inf, но обычно число.
    private double minValue;

    // Верхний предел (max). Обычно число.
    private double maxValue;

    // Когда установили/обновили пределы. Программа ставит автоматически.
    private Instant updatedAt;

    public QCLimit(long id, QCPlanManager planId, double minValue, double maxValue, Instant updatedAt) {
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

    public QCLimit(long id, long planId, double minValue, double maxValue, Instant updatedAt) {
        this.id = id;
        this.planId = planId;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.updatedAt = updatedAt;
    }

    public QCLimit(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
