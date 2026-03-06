package ru.itmo.galiya.interpreter;

import java.util.Scanner;

public abstract class Command {
    private final String name;
    private final String help;
    private final boolean requiresAdditionalInput;

    public Command(String name, String help, boolean requiresAdditionalInput) {
        this.name = name;
        this.help = help;
        this.requiresAdditionalInput = requiresAdditionalInput;
    }
    public String getName() {
        return name;
    }
    public final String getHelp() {
        return help;
    }
    public final boolean isRequiresAdditionalInput() {
        return requiresAdditionalInput;
    }

    public void CheckArgs(String[] args) throws CommandArgsException {
    }
    public void startAdditionalInput(Environment env, Scanner scanner) throws CommandArgsException {
    }
    public abstract void execute(Environment env, String[] args) throws CommandArgsException;

    public void checkArgs(String[] args) {
    }

    public void additionalInput(Environment environment, Scanner scanner) {
    }
}
