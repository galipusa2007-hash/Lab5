package ru.itmo.galiya.command;

import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.Environment;
import ru.itmo.galiya.storage.AppData;
import ru.itmo.galiya.storage.FileStorage;
import ru.itmo.galiya.storage.FileValidator;

public class LoadCommand extends Command {

    private final FileStorage fileStorage;
    private final FileValidator fileValidator;

    public LoadCommand() {
        super("load","load <path> - загрузить программу из файла",false);
        this.fileStorage = new FileStorage();
        this.fileValidator = new FileValidator();
    }
    @Override
    public void checkArgs(String[] args) throws CommandArgsException {
        if (args.length != 1) {
            throw new CommandArgsException("Используй: load <path>");
        }
    }
    @Override
    public void execute(Environment env, String[] args) {
        String path = args[0];

        AppData data = fileStorage.load(path);

        fileValidator.validate(data);

        env.getParamManager().replace(data.getMeasurementParams());
        env.getSampleManager().replace(data.getSamples());
        env.getPlanManager().replace(data.getQcPlans());
        env.getCheckManager().replace(data.getQcChecks());
        env.getLimitManager().replace(data.getQcLimits());

        System.out.println("Загрузка прошла успешно: " + path);
    }
}

