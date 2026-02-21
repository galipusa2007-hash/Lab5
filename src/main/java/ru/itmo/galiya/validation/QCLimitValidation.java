package ru.itmo.galiya.validation;

import ru.itmo.galiya.base.QCLimit;
import ru.itmo.galiya.manager.QCPlanManager;

import java.time.Instant;

public class QCLimitValidation {
    public void validate(QCLimit limit, QCPlanManager planManager) {
        validateQCLimit(limit);
        if (planManager == null) {
            throw new ValidationException("Ошибка: объект не может быть пустым");
        }
        if (!planManager.exists(limit.getPlanId())) {
            throw new ValidationException("Ошибка: объект c таким id не существует");
        }
    }


    private void validateQCLimit(QCLimit limit) {
        if (limit == null) {
            throw new ValidationException("Ошибка: объект не может быть пустым");

        }
        validateId(limit.getId());
        validatePlanId(limit.getPlanId());
        validateMinValue(limit.getMinValue());
        validateMaxValue(limit.getMaxValue());
        validateRange(limit.getMinValue(), limit.getMaxValue());
        validateUpdatedAt(limit.getUpdatedAt());
    }

    private void validateId(long id) {
        if (id <= 0) {
            throw new ValidationException("Ошибка: Id не может быть меньше нуля");
        }
    }

    private void validatePlanId(long planId) {
        if (planId <= 0) {
            throw new ValidationException("Ошибка: план проверки не может быть пустым");
        }
    }

    private void validateMinValue(double minValue) {
        if (Double.isNaN(minValue)) {
            throw new ValidationException("Ошибка: нижний предел не может быть не числом");
        }
        if (minValue == Double.POSITIVE_INFINITY) {
            throw new ValidationException("Ошибка: нижний предел не может быть положительной бесконечностью");
        }
    }

    private void validateMaxValue(double maxValue) {
        if (Double.isNaN(maxValue)) {
            throw new ValidationException("Ошибка: верхний предел не может быть не числом");
        }
        if (Double.isInfinite(maxValue)) {
            throw new ValidationException("Ошибка: верхний предел не может быть бесконечностью");
        }
    }

    private void validateRange(double minValue, double maxValue) {
        if (minValue > maxValue) {
            throw new ValidationException("Ошибка: нижний предел не может быть больше верхнего предела");
        }
    }

    private void validateUpdatedAt(Instant updatedAt) {
        if (updatedAt == null) {
            throw new ValidationException("Ошибка: время установки/обновления не может быть пустым");
        }
    }
}


