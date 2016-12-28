package vagrant.api.domain;

import java.io.File;

public class Machine {
    private String id;
    private String name;
    private MachineState state;
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
    public MachineState getState() {
        return state;
    }
    public void setStatus(MachineState status) {
        this.state = status;
    }
    public void setPath(File path) {
        this.path = path;
    }
    public boolean exists() {
        return path.exists() && new File(path, "Vagrantfile").exists();
    }

    @Override
    public String toString() {
        return "Machine[id=" + id + ", name=" + name + ", status=" + state + ", path=" + path + "]";
    }
}
