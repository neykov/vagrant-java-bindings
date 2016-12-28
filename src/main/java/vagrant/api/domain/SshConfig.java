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
    public String toString() {
        return "SshConfig[hostName=" + hostName + ", user=" + user + ", port=" + port + ", identityFile="
                + identityFile + "]";
    }

}
