package ru.itmo.galiya.validation;

import ru.itmo.galiya.base.MeasurementParam;

public class MeasurementParamValidation {

    public void validate(MeasurementParam measurementParam) {
        if (measurementParam == null) {
            throw new ValidationException("Ошибка: объект не может быть пустым");
        }
        validateId(measurementParam.getId());
        validateName(measurementParam.getName());
        validateDescription(measurementParam.getDescription());
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
        if (name.length() > 255) {
            throw new ValidationException("Ошибка: имя не может быть таким длинным");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new ValidationException("Ошибка: описание не может быть пустым");
        }
        if (description.length() > 255) {
            throw new ValidationException("Ошибка: описание не может быть таким длинным");
        }
    }
}