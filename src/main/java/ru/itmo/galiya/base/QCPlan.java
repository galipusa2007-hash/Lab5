package ru.itmo.galiya.base;

import ru.itmo.galiya.QCFrequency;
import java.time.Instant;

public final class QCPlan {

    // Уникальный номер плана QC. Программа назначает сама.
    private long id;

    // Название плана (например "Water QC basic"). Нельзя пустое.До 128символов .
    private String name;

    // Какой параметр контролируем (PH, NITRATE...).Выбирается из MeasurementParam .
    private MeasurementParam param;

    // Частота контроля (например DAILY). Выбирается из QCFrequency .
    private QCFrequency frequency;

    // Кто создал план (логин). На ранних этапах можно "SYSTEM".
    private String ownerUsername;

    // Когда создан. Программа ставит автоматически.
    private Instant createdAt;

    // Когда изменяли. Программа обновляет автоматически.
    private Instant updatedAt;

    public QCPlan(long id, String name, MeasurementParam param, QCFrequency frequency, String ownerUsername, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.param = param;
        this.frequency = frequency;
        this.ownerUsername = ownerUsername;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public QCPlan(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public MeasurementParam getParam() {
        return param;
    }
    public QCFrequency getFrequency() {
        return frequency;
    }
    public String getOwnerUsername() {
        return ownerUsername;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setParam(MeasurementParam param) {
        this.param = param;
    }
    public void setFrequency(QCFrequency frequency) {
        this.frequency = frequency;
    }
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}