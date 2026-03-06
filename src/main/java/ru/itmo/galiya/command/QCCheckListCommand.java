package ru.itmo.galiya.command;

import ru.itmo.galiya.QCStatus;
import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCCheckListCommand extends Command {

    public QCCheckListCommand() {
        super("qc_check_list","qc_check_list [--status PASS|FAIL] - показать список проверок", false );
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
    if (args.length == 0) {
        return;
    }
        if (args.length == 2 && args[0].equals("--status")) {
            if (!args[1].equals("PASS") && !args[1].equals("FAIL")) {
                throw new CommandArgsException("Статус может быть только PASS/FAIL");
            }
            return;
        }
        throw new CommandArgsException("Используй: qc_check_list или qc_check_list --status PASS|FAIL");
    }
    @Override
    public void execute(Environment env, String[] args) {
        QCStatus filterStatus = null;

        if (args.length == 2) {
            filterStatus = QCStatus.valueOf(args[1]);
        }
        if (env.getCheckManager().getAll().isEmpty()) {
            System.out.println("Список проверок пуст");
            return;
        }
        System.out.println("id | Sample | Plan | Value | Unit | Status | Owner");

        for (QCCheck check : env.getCheckManager().getAll()) {
            if (filterStatus != null && check.getStatus() != filterStatus) {
                continue;
            }
            System.out.println(check.getId() + " | " +
                    check.getSampleId() + " | " +
                    check.getPlanId() + " | " +
                    check.getValue() + " | " +
                    check.getUnit() + " | " +
                    check.getStatus() + " | " +
                    check.getOwnerUsername());
        }
    }
}

