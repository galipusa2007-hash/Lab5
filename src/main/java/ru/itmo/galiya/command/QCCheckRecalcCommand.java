package ru.itmo.galiya.command;

import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.base.QCLimit;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCCheckRecalcCommand extends Command {//пересчет статуса

    public QCCheckRecalcCommand() {
        super("qc_check_recalc", "qc_check_recalc <check_id> - пересчитать статус проверки", false);
    }
    @Override
    public void checkArgs( String[] args) throws CommandArgsException {
        if (args.length != 1) {
            throw new CommandArgsException("Используй: qc_check_recalc <check_id>");
        }
        try {
            Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("check_id должен быть числом");
        }
    }

    @Override
    public void execute(Environment env, String[] args) {
        long checkId = Long.parseLong(args[0]);

        QCCheck check = env.getCheckManager().get(checkId);
        if (check == null) {
            System.out.println("Проверка с id=" + checkId + " не найдена");
            return;
        }
        QCLimit foundLimit = null;
        for (QCLimit limit : env.getLimitManager().getAll()) {
            if (limit.getPlanId() == check.getPlanId()) {
                foundLimit = limit;
                break;
            }
        }
        if (foundLimit == null) {
            System.out.println("Для плана с id=" + check.getPlanId() + "пределы не найдены");
            return;
        }
        double value = check.getValue();
        if (value >= foundLimit.getMinValue() && value <= foundLimit.getMaxValue()) {
            check.setStatus(QCStatus.PASS);
        }  else {
            check.setStatus(QCStatus.FAIL);
        }
        System.out.println("Обновление прошло успешно: status =" + check.getStatus());
    }
}
