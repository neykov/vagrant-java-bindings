package vagrant;

import java.io.File;

import vagrant.api.VagrantApi;
import vagrant.api.domain.Machine;
import vagrant.impl.cli.VagrantImpl;

public class Vagrant {


    public static VagrantApi forPath(File path) {
        return new VagrantImpl(path);
    }

    public static VagrantApi forMachine(Machine input) {
        return forPath(input.getPath());
    }

}
