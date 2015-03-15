package vagrant.api.domain;

import java.io.File;

public class Machine {
    private String id;
    private String name;
    private MachineState status;
    private File path;

    public File getPath() {
        return path;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MachineState getStatus() {
        return status;
    }
    public void setStatus(MachineState status) {
        this.status = status;
    }
    public void setPath(File path) {
        this.path = path;
    }
    public boolean exists() {
        return path.exists() && new File(path, "Vagrantfile").exists();
    }

}
