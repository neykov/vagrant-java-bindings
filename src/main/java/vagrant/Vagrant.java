package vagrant;

import java.io.File;

import vagrant.api.CommandIOListener;
import vagrant.api.VagrantApi;
import vagrant.api.domain.Machine;
import vagrant.impl.cli.VagrantImpl;

public class Vagrant {

    private static final CommandIOListener nopIOListener = new CommandIOListener() {
        @Override
        public void onInput(String input) {}

        @Override
        public void onOutput(String output) {}
    };

    public static VagrantApi forPath(File path) {
        return new VagrantImpl(path, nopIOListener);
    }

    public static VagrantApi forPath(File path, CommandIOListener listener) {
        return new VagrantImpl(path, listener);
    }

    public static VagrantApi forMachine(Machine input) {
        return forPath(input.getPath(), nopIOListener);
    }

    public static VagrantApi forMachine(Machine input, CommandIOListener listener) {
        return forPath(input.getPath(), listener);
    }

}
