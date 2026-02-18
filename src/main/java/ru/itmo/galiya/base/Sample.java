package ru.itmo.galiya.base;

import ru.itmo.galiya.validation.ValidationException;

public final class Sample {

    private final long id;

    public Sample(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
