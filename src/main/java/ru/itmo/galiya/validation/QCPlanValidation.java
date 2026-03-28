package ru.itmo.galiya.validation;

import ru.itmo.galiya.QCFrequency;
import ru.itmo.galiya.base.*;

import java.time.Instant;

public class QCPlanValidation {

    public void validate(QCPlan plan) {
        if (plan == null) {
            throw new ValidationException("Ошибка: объект не может быть пустым");
        }
        validateId(plan.getId());
        validateName(plan.getName());
        validateParam(plan.getParam());
        validateFrequency(plan.getFrequency());
        validateOwnerUsername(plan.getOwnerUsername());
        validateCreatedAt(plan.getCreatedAt());
        validateUpdatedAt(plan.getUpdatedAt());
        validateTimeOrder(plan.getCreatedAt(), plan.getUpdatedAt() );
    }

    private void validateId(long id) {
        if (id <= 0) {
            throw new ValidationException("Ошибка: Id не может быть меньше нуля");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Ошибка: имя не может быть пустым");
        }
        if (name.length() > 128) {
            throw new ValidationException("Ошибка: имя не может быть таким длинным");
        }
    }

    private void validateParam(MeasurementParam param) {
        if (param == null) {
            throw new ValidationException("Ошибка: параметр не может быть пустым");
        }
    }

    private void validateFrequency(QCFrequency frequency) {
        if (frequency == null) {
            throw new ValidationException("Ошибка: частота не может быть пустым");
        }
    }

    private void validateOwnerUsername(String ownerUsername) {
        if (ownerUsername == null || ownerUsername.isEmpty()) {
            throw new ValidationException("Ошибка: имя владельца не может быть пустым");
        }
        if (ownerUsername.length() > 64) {
            throw new ValidationException("Ошибка: имя владельца не может быть таким длинным");
        }
    }

    private void validateCreatedAt(Instant createdAt) {
        if (createdAt == null) {
            throw new ValidationException("Ошибка: время установления не может быть пустым");
        }
    }

    private void validateUpdatedAt(Instant updatedAt) {
        if (updatedAt == null) {
            throw new ValidationException("Ошибка: время установления не может быть пустым");
        }
    }
    private void validateTimeOrder(Instant createdAt, Instant updatedAt) {
        if (updatedAt.isBefore(createdAt)) {
            throw new ValidationException("Ошибка: время обновления не может быть раньше времени создания");
        }
    }
}



