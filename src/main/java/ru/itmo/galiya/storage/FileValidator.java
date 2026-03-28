package ru.itmo.galiya.storage;

import ru.itmo.galiya.base.*;
import ru.itmo.galiya.validation.*;

import java.util.Map;

public class FileValidator {
    private final MeasurementParamValidation measurementParamValidation = new MeasurementParamValidation();//чтобы не писать заново все условии для валидации
    private final SampleValidation sampleValidation = new SampleValidation();
    private final QCPlanValidation qcPlanValidation = new QCPlanValidation();
    private final QCCheckValidation qcCheckValidation = new QCCheckValidation();
    private final QCLimitValidation qcLimitValidation = new QCLimitValidation();

    public void validate(AppData data) {
        if (data == null) {
            throw new FileStorageException("Загруженные данные отсутствуют");
        }
        validateMapExists(data.getMeasurementParams(), "MeasurementParams");//проверка мапов
        validateMapExists(data.getSamples(), "Samples");
        validateMapExists(data.getQcPlans(), "qcPlans");
        validateMapExists(data.getQcChecks(), "qcChecks");
        validateMapExists(data.getQcLimits(), "qcLimits");

        validateMeasurementParams(data.getMeasurementParams());//проверяем внутренности каждого класса
        validateSamples(data.getSamples());
        validateQcPlans(data.getQcPlans(), data.getMeasurementParams());
        validateQcChecks(data.getQcChecks(),data.getSamples(), data.getQcPlans());
        validateQcLimit(data.getQcLimits(),data.getQcPlans());
    }
    private  void validateMapExists(Map<?, ?> map, String name) {
        if (map == null) {
            throw new FileStorageException("Ошибка загрузки: отсутствует коллекция '" + name + "'");
        }
    }
    private  void validateMeasurementParams(Map<Long, MeasurementParam> params) {
        for (Map.Entry<Long, MeasurementParam> entry : params.entrySet()) {
            Long key = entry.getKey();
            MeasurementParam param = entry.getValue();

            if (key == null) {
                throw new FileStorageException("Ошибка загрузки: MeasurementParam содержит пустой ключ");
            }
            if (param == null) {
                throw new FileStorageException("Ошибка загрузки: measurementParam с key=" + key + " отсутствует");
            }
            if (param.getId() != key) {
                throw new FileStorageException("Ошибка загрузки: measurementParam key=" + key + " не совпадает с id объекта=" + param.getId());
            }
            try {
                measurementParamValidation.validate(param);
            } catch (ValidationException e) {
                throw new FileStorageException("Ошибка загрузки: measurementParam id=" + param.getId()  + " невалиден: " + e.getMessage(), e);
            }
        }
    }
    private  void validateSamples(Map<Long, Sample> samples) {
        for (Map.Entry<Long, Sample> entry : samples.entrySet()) {
            Long key = entry.getKey();
            Sample sample = entry.getValue();

            if (key == null) {
                throw new FileStorageException("Ошибка загрузки: Sample содержит пустой ключ");
            }
            if (sample == null) {
                throw new FileStorageException("Ошибка загрузки: Sample с key=" + key + " отсутствует");
            }
            if (sample.getId() != key) {
                throw new FileStorageException("Ошибка загрузки: Sample key=" + key + " не совпадает с id объекта=" + sample.getId());
            }
            try {
                sampleValidation.validate(sample);
            } catch (ValidationException e) {
                throw new FileStorageException("Ошибка загрузки: Sample id=" + sample.getId()  + " невалиден: " + e.getMessage(), e);
            }
        }
    }
    private void validateQcPlans(Map<Long, QCPlan> qcPlans, Map<Long, MeasurementParam> measurementParamMap) {
        for (Map.Entry<Long, QCPlan> entry : qcPlans.entrySet()) {
            Long key = entry.getKey();
            QCPlan plan = entry.getValue();

            if (key == null) {
                throw new FileStorageException("Ошибка загрузки: QCPlan содержит пустой ключ");
            }
            if (plan == null) {
                throw new FileStorageException("Ошибка загрузки: QCPlan с key=" + key + " отсутствует");
            }
            if (plan.getId() != key) {
                throw new FileStorageException("Ошибка загрузки: QCPlan key=" + key + " не совпадает с id объекта=" + plan.getId());
            }
            try {
                qcPlanValidation.validate(plan);
            } catch (ValidationException e) {
                throw new FileStorageException("Ошибка загрузки: QCPlan id=" + plan.getId()  + " невалиден: " + e.getMessage(), e);
            }
            if (plan.getParam() == null) {
                throw new FileStorageException("Ошибка загрузки: QCPlan id=" + plan.getId() + " не содержит measurementParam");
            }
            long paramId = plan.getParam().getId();

            if (!measurementParamMap.containsKey(paramId)) {
                throw new FileStorageException("Ошибка загрузки: QCPlan id=" + plan.getId() + " ссылается на несуществующий measurementParam id=" + paramId);
            }
        }
    }
    private void validateQcLimit(Map<Long, QCLimit> qcLimits, Map<Long, QCPlan> qcPlans) {
        for (Map.Entry<Long, QCLimit> entry : qcLimits.entrySet()) {
            Long key = entry.getKey();
            QCLimit limit = entry.getValue();

            if (key == null) {
                throw new FileStorageException("Ошибка загрузки: QCLimit содержит пустой ключ");
            }
            if (limit == null) {
                throw new FileStorageException("Ошибка загрузки: QCLimit с key=" + key + " отсутствует");
            }
            if (limit.getId() != key) {
                throw new FileStorageException("Ошибка загрузки: QCLimit key=" + key + " не совпадает с id объекта=" + limit.getId());
            }
            try {
                qcLimitValidation.validate(limit);
            } catch (ValidationException e) {
                throw new FileStorageException("Ошибка загрузки: QCLimit id=" + limit.getId() + " невалиден: " + e.getMessage(), e);
            }
            if (!qcPlans.containsKey(limit.getPlanId())) {
                throw new FileStorageException("Ошибка загрузки: QCLimit id=" + limit.getId() + " ссылается на несуществующий QCPlan id=" + limit.getPlanId());
            }
        }
    }
    private void validateQcChecks(Map<Long, QCCheck> qcChecks, Map<Long, Sample> samples, Map<Long, QCPlan> qcPlans) {
        for (Map.Entry<Long, QCCheck> entry : qcChecks.entrySet()) {
            Long key = entry.getKey();
            QCCheck check = entry.getValue();

            if (key == null) {
                throw new FileStorageException("Ошибка загрузки: QCCheck содержит пустой ключ");
            }
            if (check == null) {
                throw new FileStorageException("Ошибка загрузки: QCCheck с key=" + key + " отсутствует");
            }
            if (check.getId() != key) {
                throw new FileStorageException("Ошибка загрузки: QCCheck key=" + key + " не совпадает с id объекта=" + check.getId());
            }
            try {
                qcCheckValidation.validate(check);
            } catch (ValidationException e) {
                throw new FileStorageException("Ошибка загрузки: QCCheck id=" + check.getId() + " невалиден: " + e.getMessage(), e);
            }
            if (!samples.containsKey(check.getSampleId())) {
                throw new FileStorageException("Ошибка загрузки: QCCheck id=" + check.getId() + " ссылается на несуществующий Sample id=" + check.getSampleId());
            }
            if (!qcPlans.containsKey(check.getPlanId())) {
                throw new FileStorageException("Ошибка загрузки: QCCheck id=" + check.getId() + " ссылается на несуществующий QCPlan id=" + check.getSampleId());
            }
        }
    }
}
