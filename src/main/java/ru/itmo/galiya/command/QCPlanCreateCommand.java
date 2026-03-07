package ru.itmo.galiya.command;

import ru.itmo.galiya.QCFrequency;
import ru.itmo.galiya.base.MeasurementParam;
import ru.itmo.galiya.base.QCPlan;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

import java.util.Scanner;

public class QCPlanCreateCommand extends Command {

    private String name;
    private long paramId;
    private QCFrequency frequency;
    private String ownerUsername;

    public QCPlanCreateCommand() {
        super("qc_plan_create", "qc_plan_create - создать план интерактивно", true);
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 0) {
            throw new CommandArgsException("Введите только qc_plan_create");
        }
    }
    @Override
    public void additionalInput(Environment environment, Scanner scanner) {
        System.out.println("Название плана: ");
        name = scanner.nextLine().trim();

        System.out.println("Параметр: ");
        paramId = Long.parseLong(scanner.nextLine().trim());

        System.out.println("Частота: (выберите из:EACH_SAMPLE/DAILY/WEEKLY)");
        String frequencyInput = scanner.nextLine().trim();
        frequency = QCFrequency.valueOf(frequencyInput);

        System.out.println("Владелец:");
        ownerUsername = scanner.nextLine().trim();
    }

    @Override
    public void execute(Environment env, String[] args) {
        if (!env.getParamManager().exists(paramId)) {
            System.out.println("Параметр не найден");
            return;
        }
        MeasurementParam foundParam = env.getParamManager().get(paramId);

        QCPlan plan = env.getPlanManager().add(name, foundParam, frequency, ownerUsername);
        System.out.println("Обновление прошло успешно: plan_id="  + plan.getId());
    }
}
