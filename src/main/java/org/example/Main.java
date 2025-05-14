package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Error with downloading configuration");
        }

        Core core = new Core();
        core.init(args[0]);
    }
}