package ru.itmo.galiya.command;

import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCCheckAddCommand extends Command {//добавление

    public QCCheckAddCommand() {
        super("qc_check_add", "qc_check_add <sample_id> <plan_id> <value> <unit><status> <owner>", false);
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 6) {
            throw new CommandArgsException("Используй: qc_check_add <sample_id> <plan_id> <value> <unit> <status> <owner>");
        }
        try {
            Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("sample_id должен быть числом");
        }

        try {
            Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("plan_id должен быть числом");
        }

        try {
            Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("value должен быть числом");
        }
        if (!args[4].equals("PASS") && !args[4].equals("FAIL")) {
            throw new CommandArgsException("status должен быть PASS/FAIL");
        }
    }
    @Override
    public void execute(Environment env, String[] args) {
        long sampleId = Long.parseLong(args[0]);
        long planId = Long.parseLong(args[1]);
        double value = Double.parseDouble(args[2]);
        String unit = args[3];
        QCStatus status = QCStatus.valueOf(args[4]);
        String owner = args[5];

        QCCheck check = env.getCheckManager().add(sampleId, planId, value, unit, status, owner);

        System.out.println("Обновление прошло успешно: check.getId())" + check.getId());
    }
}
