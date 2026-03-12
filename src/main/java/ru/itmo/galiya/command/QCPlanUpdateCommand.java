package ru.itmo.galiya.command;

import ru.itmo.galiya.QCFrequency;
import ru.itmo.galiya.base.MeasurementParam;
import ru.itmo.galiya.base.QCPlan;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

import java.time.Instant;

public class QCPlanUpdateCommand extends Command {//обновление существующего плана

    public QCPlanUpdateCommand() {
        super("qc_plan_update", "qc_plan_update <plan_id> name=VALUE frequency=VALUE param=VALUE", false);
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length < 2) {
            throw new CommandArgsException("Используй: qc_plan_update <plan_id> name=VALUE frequency=VALUE param=VALUE");
        }
        try {
            Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("plan_id должен быть числом");
        }

        for (int i = 1; i < args.length; i++) {
            if (!args[i].contains("=")) {
                throw new CommandArgsException("Ввод должен быть в другом формате, например name=WaterQC ");
            }

            String[] pair = args[i].split("=", 2);
            String failed = pair[0];

            if (!failed.equals("name") && !failed.equals("frequency") && !failed.equals("param")) {
                throw new CommandArgsException("Ввод должен содержать только параметры: name, frequency ");
            }
            if (pair.length < 2 || pair[1].isEmpty()) {
                throw new CommandArgsException("Значение ввода должен быть не пустым");
            }
            if (failed.equals("frequency")) {
                try {
                    QCFrequency.valueOf(pair[1]);
                }  catch (IllegalArgumentException e) {
                    throw new CommandArgsException("Можно использовать только:  EACH_SAMPLE, DAILY или WEEKLY");
                }
            }
        }
    }
    @Override
    public void execute(Environment env, String[] args) {
        long planId = Long.parseLong(args[0]);

        QCPlan plan = env.getPlanManager().get(planId);
        if (plan == null) {
            System.out.println("План с id=" + planId + " не найден");
            return;
        }
        for (int j = 1; j < args.length; j++) {
            String[] pair = args[j].split("=", 2);
            String failed = pair[0];
            String value = pair[1];

            if (failed.equals("name")) {
                plan.setName(value);
            }
            else if (failed.equals("frequency")) {
                plan.setFrequency(QCFrequency.valueOf(value));
            }
            else if (failed.equals("param")) {
                MeasurementParam foundParam = null;

                for (MeasurementParam param : env.getParamManager().getAll()) {
                    if (param.getName().equalsIgnoreCase(value)) {
                        foundParam = param;
                        break;
                    }
                }
                if (foundParam == null) {
                    System.out.println("Параметр '" + value + "' не найден");
                    return; 
                }
                plan.setParam(foundParam);
            }
        }
        plan.setUpdatedAt(Instant.now());
        System.out.println("Обновление прошло успешно");
    }
}
