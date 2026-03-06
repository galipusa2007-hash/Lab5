package ru.itmo.galiya.command;

import ru.itmo.galiya.base.QCPlan;
import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;

public class QCPlanListCommand extends Command {
    public QCPlanListCommand() {
        super("qc_plan_list", "qc_plan_list [--param PARAM] - показать список планов в системе", false);
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length == 0) {
            return;
        }
        if (args.length == 2 && args[0].equals("--param")) {
            return;
        }
        throw new CommandArgsException("Используй: qc_plan_list или qc_plan_list --param PARAM");
    }
    @Override
    public void execute(Environment env, String[] args) {
        String filterParam = null;
        if (args.length == 2) {
            filterParam = args[1];
        }
        if (env.getPlanManager().getAll().isEmpty()) {
            System.out.println("Список планов в системе пуст");
            return;
        }
        System.out.println("id | Name | Param | Frequency | Owner");

        for (QCPlan plan : env.getPlanManager().getAll()) {
            String paramName = plan.getParam().getName();

            if (filterParam != null && !paramName.equalsIgnoreCase(filterParam)) {
                continue;
            }
            System.out.println(plan.getId() + " | ");
            System.out.println(plan.getName() + " | ");
            System.out.println(paramName + " | ");
            System.out.println(plan.getFrequency() + " | ");
            System.out.println(plan.getOwnerUsername());
        }
    }
}
