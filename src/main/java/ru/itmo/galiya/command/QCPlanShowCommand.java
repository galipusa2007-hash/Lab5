package ru.itmo.galiya.command;

import ru.itmo.galiya.base.QCPlan;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCPlanShowCommand extends Command {//пвывод одного плана по id
    public QCPlanShowCommand() {
        super("qc_plan_show", "qc_plan_show <plan_id> - показать один план", false);
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 1) {
            throw new CommandArgsException ("Используй: qc_plan_show <plan_id>");
        }
        try {
            Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandArgsException("plan_id должен быть числом");
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
        System.out.println("QCPlan # " + plan.getId());
        System.out.println("Name: " + plan.getName());
        System.out.println("Param: " + plan.getParam().getName());
        System.out.println("Frequency: " + plan.getFrequency());
        System.out.println("Owner: " + plan.getOwnerUsername());
        System.out.println("createdAt: " + plan.getCreatedAt());
        System.out.println("updatedAt: " + plan.getUpdatedAt());
    }
}
