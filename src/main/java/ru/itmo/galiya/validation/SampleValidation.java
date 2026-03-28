package ru.itmo.galiya.validation;

import ru.itmo.galiya.base.*;

public class SampleValidation {

    public void validate(Sample sample) {
        if (sample == null) {
            throw new ValidationException("Ошибка: объект не может быть пустым");
        }
        validateId(sample.getId());
    }

    private void validateId(long id) {
        if (id <= 0) {
            throw new ValidationException("Ошибка: Id не может быть меньше нуля");
        }
    }
}