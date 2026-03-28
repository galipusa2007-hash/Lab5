package ru.itmo.galiya.storage;

import ru.itmo.galiya.base.*;

import java.util.Map;

public class AppData {
    private final Map<Long, MeasurementParam> measurementParams;
    private final Map<Long, Sample> samples;
    private final Map<Long, QCPlan> qcPlans;
    private final Map<Long, QCCheck> qcChecks;
    private final Map<Long, QCLimit> qcLimits;

    public AppData(Map<Long, MeasurementParam> measurementParams,
                Map<Long, Sample> samples,
                Map<Long, QCPlan> qcPlans,
                Map<Long, QCCheck> qcChecks,
                Map<Long, QCLimit> qcLimits) {
    this.measurementParams = measurementParams;
    this.samples = samples;
    this.qcPlans = qcPlans;
    this.qcChecks = qcChecks;
    this.qcLimits = qcLimits;
    }
    public Map<Long, MeasurementParam> getMeasurementParams() {
        return measurementParams;
    }
    public Map<Long, Sample> getSamples() {
        return samples;
    }
    public Map<Long, QCPlan> getQcPlans() {
        return qcPlans;
    }
    public Map<Long, QCCheck> getQcChecks() {
        return qcChecks;
    }
    public Map<Long, QCLimit> getQcLimits() {
        return qcLimits;
    }
}
