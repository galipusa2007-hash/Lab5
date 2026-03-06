package ru.itmo.galiya;

import ru.itmo.galiya.interpreter.CommandInterpreter;
import ru.itmo.galiya.interpreter.Environment;
import ru.itmo.galiya.manager.*;
import ru.itmo.galiya.command.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MeasurementParamManager paramManager = new MeasurementParamManager();
        SampleManager sampleManager = new SampleManager();
        QCPlanManager planManager = new QCPlanManager();
        QCLimitManager limitManager = new QCLimitManager(planManager);
        QCCheckManager checkManager = new QCCheckManager(sampleManager,planManager);

        Environment environment = new Environment(paramManager, checkManager, limitManager, planManager, sampleManager);

        Scanner scanner = new Scanner(System.in);

        CommandInterpreter interpreter = new CommandInterpreter(environment, scanner);

        interpreter.register(new HelpCommand(interpreter));
        interpreter.register(new ExitCommand(interpreter));
        interpreter.register(new QCPlanCreateCommand());
        interpreter.register(new QCPlanListCommand());
        interpreter.register(new QCCheckShowCommand());
        interpreter.register(new QCPlanUpdateCommand());
        interpreter.register(new QCLimitSetCommand());
        interpreter.register(new QCCheckAddCommand());
        interpreter.register(new QCCheckListCommand());
        interpreter.register(new QCPlanShowCommand());
        interpreter.register(new QCCheckRecalcCommand());
        interpreter.register(new QCReportCommand());

        paramManager.add("PH", "Концентрация протонов водорода");
        paramManager.add("AXID", "Кислоты");
        interpreter.run();
    }
}
