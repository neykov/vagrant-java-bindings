package vagrant.api;

import java.io.File;
import java.util.Collection;

import vagrant.api.domain.Machine;
import vagrant.api.domain.SshConfig;

public interface VagrantApi {
    String DEFAULT_MACHINE = "default";

    String getVersion();
    void init(String string);
    BoxApi box();
    void up(String name);
    void up(String name, String provider);
    void destroy(String name);
    Collection<Machine> status();
    Machine status(String name);
    void halt(String name);
    void haltForced(String name);
    void reload(String name);
    void resume(String name);
    void suspend(String name);
    String ssh(String name, String command);
    SshConfig sshConfig(String name);

    File getPath();
    boolean exists();
}
