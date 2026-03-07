package ru.itmo.galiya.base;

import ru.itmo.galiya.QCFrequency;
import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.manager.*;

public class AppData {
    public static void loadDemoData (MeasurementParamManager measurementParamManager, QCCheckManager qcCheckManager, QCLimitManager qCLimitManager, QCPlanManager qCPlanManager, SampleManager sampleManager) {

        MeasurementParam ph = measurementParamManager.add("PH", "Концентрация протонов водорода");
        MeasurementParam acid = measurementParamManager.add("ACID", "Кислоты");
        MeasurementParam substance = measurementParamManager.add("SUBSTANCE", "Вещество");
        MeasurementParam nitrate = measurementParamManager.add("NITRATE", "Нистраты");
        MeasurementParam conductivity = measurementParamManager.add("CONDUCTIVITY", "Электропроводимость");

        sampleManager.add();
        sampleManager.add();
        sampleManager.add();
        sampleManager.add();
        sampleManager.add();

        QCPlan plan1 = qCPlanManager.add("WaterQC", ph, QCFrequency.DAILY, "SYSTEM");
        QCPlan plan2 = qCPlanManager.add("AcidQC", acid, QCFrequency.WEEKLY, "SYSTEM");
        QCPlan plan3 = qCPlanManager.add("SubstanceQC", substance, QCFrequency.EACH_SAMPLE, "SYSTEM");
        QCPlan plan4 = qCPlanManager.add("NitrateQC", nitrate, QCFrequency.DAILY, "SYSTEM");
        QCPlan plan5 = qCPlanManager.add("ConductivityQC", conductivity, QCFrequency.WEEKLY, "SYSTEM");

        qCLimitManager.add(plan1.getId(), 0.00, 14.00);//pH
        qCLimitManager.add(plan2.getId(), 0.00, 14.00);//pH
        qCLimitManager.add(plan3.getId(), 0.00, 300.00);
        qCLimitManager.add(plan4.getId(), 0.00, 300.00);
        qCLimitManager.add(plan5.getId(), 100.00, 600.00);

        qcCheckManager.add(1, plan1.getId(), 1.11, "pH", QCStatus.PASS, "SYSTEM");
        qcCheckManager.add(2, plan2.getId(), 2.22, "pH", QCStatus.PASS, "SYSTEM");
        qcCheckManager.add(3, plan3.getId(), 33.33, "mg", QCStatus.PASS, "SYSTEM");
        qcCheckManager.add(4, plan4.getId(), 44.44, "mg", QCStatus.PASS, "SYSTEM");
        qcCheckManager.add(5, plan5.getId(), 555.55, " S/m", QCStatus.PASS, "SYSTEM");

    }
}
