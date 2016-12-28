package vagrant.api.domain;

public class SshConfig {
    private String hostName;
    private String user;
    private int port;
    private String identityFile;

    public String getIdentityFile() {
        return identityFile;
    }

    public String getUser() {
        return user;
    }

    public int getPort() {
        return port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIdentityFile(String identityFile) {
        this.identityFile = identityFile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
        result = prime * result + ((identityFile == null) ? 0 : identityFile.hashCode());
        result = prime * result + port;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        SshConfig other = (SshConfig) obj;
        if (hostName == null) {
            if (other.hostName != null)
                return false;
        } else if (!hostName.equals(other.hostName))
            return false;
        if (identityFile == null) {
            if (other.identityFile != null)
                return false;
        } else if (!identityFile.equals(other.identityFile))
            return false;
        if (port != other.port)
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SshConfig[hostName=" + hostName + ", user=" + user + ", port=" + port + ", identityFile="
                + identityFile + "]";
    }

}
