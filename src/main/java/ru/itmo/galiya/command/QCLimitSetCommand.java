package ru.itmo.galiya.command;

import ru.itmo.galiya.base.QCLimit;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCLimitSetCommand extends Command {

    public QCLimitSetCommand() {
        super("qc_limit_set", "qc_limit_set <plan_id> <min> <max> - установить пределы для плана", false);
    }

    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 3) {
            throw new CommandArgsException("Используй: qc_limit_set <plan_id> <min> <max>");
        }
        try {
            Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("plan_id должен быть числом");
        }
        try {
            Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("min должен быть числом");
        }
        try {
            Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("max должен быть числом");
        }
    }

    @Override
    public void execute(Environment env, String[] args) {
        long planId = Long.parseLong(args[0]);
        double minValue = Double.parseDouble(args[1]);
        double maxValue = Double.parseDouble(args[2]);

        QCLimit limit = env.getLimitManager().add(planId, minValue, maxValue);

        System.out.println("Обновление прошло успешно: limit_id="  + limit.getId());
    }
}