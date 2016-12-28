package vagrant.api.domain;

import java.io.File;

public class Machine {
    private String name;
    private MachineState state;
    private File path;

    public File getPath() {
        return path;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Machine other = (Machine) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Machine[name=" + name + ", status=" + state + ", path=" + path + "]";
    }
}
