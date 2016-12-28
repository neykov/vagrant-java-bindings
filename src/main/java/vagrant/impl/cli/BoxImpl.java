package vagrant.impl.cli;

import java.io.File;
import java.util.Collection;

import vagrant.api.BoxApi;
import vagrant.api.CommandIOListener;
import vagrant.api.domain.Box;
import vagrant.api.option.BoxAddOptions;
import vagrant.impl.cli.parser.BoxListParser;

public class BoxImpl implements BoxApi {
    private File path;
    private CommandIOListener ioListener;

    public BoxImpl(File path, CommandIOListener ioListener) {
        this.path = path;
        this.ioListener = ioListener;
    }

    @Override
    public void add(String name, BoxAddOptions options) {
        new VagrantCli(path, ioListener)
            .arg("box")
            .arg("add")
            .arg(name)
            .machineReadable()
            .execute();
    }

    @Override
    public Collection<Box> list() {
        String out = new VagrantCli(path, ioListener)
            .arg("box")
            .arg("list")
            .machineReadable()
            .execute();
        return new BoxListParser().parse(out);
    }

}
