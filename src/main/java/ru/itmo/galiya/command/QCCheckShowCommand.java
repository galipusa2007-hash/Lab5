package ru.itmo.galiya.command;

import ru.itmo.galiya.base.QCCheck;
import ru.itmo.galiya.base.QCPlan;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCCheckShowCommand extends Command {

    public QCCheckShowCommand() {
        super("qc_check_show", "qc_check_show <check_id> - показать одну проверку", false);
    }

    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 1) {
            throw new CommandArgsException("Используй: qc_check_show <check_id>");
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
        System.out.println("QCCheck: " + check.getId());
        System.out.println("SampleId: " + check.getSampleId() );
        System.out.println("PlanId: " + check.getPlanId() );
        System.out.println("Value: " + check.getValue() );
        System.out.println("Unit: " +  check.getUnit() );
        System.out.println("Status: " +  check.getStatus() );
        System.out.println("Owner: " + check.getOwnerUsername());
        System.out.println("CheckedAt: " +  check.getCheckedAt());
        System.out.println("CreatedAt: " +   check.getCreatedAt());
    }
}
