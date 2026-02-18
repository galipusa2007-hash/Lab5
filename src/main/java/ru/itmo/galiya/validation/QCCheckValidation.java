package ru.itmo.galiya.validation;

import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.manager.QCPlanManager;
import ru.itmo.galiya.manager.SampleManager;

import java.time.Instant;

public class QCCheckValidation {

    private long sampleId;
    private long planId;

    public void validate(QCCheck check) {
        if (check == null) {
            throw new ValidationException("Ошибка: объект не может быть пустым");
        }
        validateId(check.getId());
        validateSampleId(check.getSampleId());
        validatePlanId(check.getPlanId());
        validateValue(check.getValue());
        validateUnit(check.getUnit());
        validateStatus(check.getStatus());
        validateOwnerUsername(check.getOwnerUsername());
        validateCheckAt(check.getCheckedAt());
        validateCreatedAt(check.getCreatedAt());

    }

    private void validateId(long id) {
        if (id <= 0) {
            throw new ValidationException("Ошибка: Id не может быть меньше нуля");
        }
    }

    private void validateSampleId(long sampleId) {
        if (sampleId <= 0) {
            throw new ValidationException("Ошибка: Id не может быть пустым");
        }
    }

    private void validatePlanId(long planId) {
        if (planId <= 0) {
            throw new ValidationException("Ошибка: план предела не может быть пустым");
        }
    }

    private void validateValue(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new ValidationException("Ошибка: результат измерения не может быть не числом или бесконечностью");
        }
    }

    private void validateUnit(String unit) {
        if (unit == null || unit.isEmpty()) {
            throw new ValidationException("Ошибка: имя не может быть пустым");
        }
        if (unit.length() > 16) {
            throw new ValidationException("Ошибка: имя не может быть таким длинным");
        }
    }

    private void validateStatus(QCStatus status) {
        if (status == null) {
            throw new ValidationException("Ошибка: статус проверки не может быть пустым");
        }
    }

    private void validateOwnerUsername(String ownerUsername) {
        if (ownerUsername == null || ownerUsername.isEmpty()) {
            throw new ValidationException("Ошибка: имя не может быть пустым");
        }
        if (ownerUsername.length() > 64) {
            throw new ValidationException("Ошибка: имя не может быть таким длинным");
        }
    }

    private void validateCheckAt(Instant checkAt) {
        if (checkAt == null) {
            throw new ValidationException("Ошибка: время проверки не может быть пустым");
        }
    }

    private void validateCreatedAt(Instant createdAt) {
        if (createdAt == null) {
            throw new ValidationException("Ошибка: время установления не может быть пустым");
        }
    }
    public void validate(QCCheck check, QCPlanManager planManager, SampleManager sampleManager) {
        validateId(check.getId());
        if (!sampleManager.exists(check.getSampleId())) {
            throw new ValidationException("Ошибка: sampleId не существует");
        }
        if (!planManager.exists(check.getPlanId())) {
            throw new ValidationException("Ошибка: planId не существует");
        }
    }
}
