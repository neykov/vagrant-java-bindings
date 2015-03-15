package vagrant.impl.cli;

import java.io.File;
import java.util.Collection;

import vagrant.api.BoxApi;
import vagrant.api.VagrantApi;
import vagrant.api.domain.Machine;
import vagrant.api.domain.SshConfig;
import vagrant.impl.cli.parser.AllStatusParser;
import vagrant.impl.cli.parser.SshConfigParser;
import vagrant.impl.cli.parser.StatusParser;
import vagrant.impl.cli.parser.VersionParser;

public class VagrantImpl implements VagrantApi {
    private File path;

    public VagrantImpl(File path) {
        this.path = path;
    }

    @Override
    public BoxApi box() {
        return new BoxImpl(path);
    }

    @Override
    public String getVersion() {
        String out = vagrant()
            .arg("version")
            .machineReadable()
            .execute();
        return new VersionParser().parse(out);
    }

    @Override
    public void init(String box) {
        path.mkdirs();
        vagrant()
            .arg("init")
            .arg(box)
            .machineReadable()
            .execute();
    }
    
    @Override
    public void up(String name) {
        vagrant()
            .arg("up")
            .arg(name)
            .machineReadable()
            .execute();
    }
    
    @Override
    public void destroy(String name) {
        vagrant()
            .arg("destroy")
            .arg(name)
            .arg("--force")
            .machineReadable()
            .execute();
    }

    @Override
    public Collection<Machine> status() {
        String out = vagrant()
            .arg("status")
            .machineReadable()
            .execute();
        return new AllStatusParser(path).parse(out);
    }

    @Override
    public Machine status(String name) {
        String out = vagrant()
            .arg("status")
            .arg(name)
            .machineReadable()
            .execute();
        return new StatusParser(path).parse(out);
    }

    @Override
    public void reload(String name) {
        vagrant()
            .arg("reload")
            .arg(name)
            .machineReadable()
            .execute();
    }

    @Override
    public void resume(String name) {
        vagrant()
            .arg("resume")
            .arg(name)
            .machineReadable()
            .execute();
    }
    
    @Override
    public void suspend(String name) {
        vagrant()
            .arg("suspend")
            .arg(name)
            .machineReadable()
            .execute();
    }

    @Override
    public SshConfig sshConfig(String name) {
        String out = vagrant()
            .arg("ssh-config")
            .arg(name)
            .machineReadable()
            .execute();
        return new SshConfigParser().parse(out).get(name);
    }

    @Override
    public boolean exists() {
        return path.exists() && new File(path, "Vagrantfile").exists();
    }

    @Override
    public File getPath() {
        return path;
    }

    @Override
    public String ssh(String name, String command) {
        return vagrant()
            .arg("ssh")
            .arg(name)
            .arg("-c")
            .arg(command)
            .execute();
    }

    private VagrantCli vagrant() {
        return new VagrantCli(path);
    }
}
