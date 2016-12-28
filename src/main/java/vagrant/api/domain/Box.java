package vagrant.api.domain;

public class Box {
    private String name;
    private String version;
    private String provider;

    public Box(String name, String version, String provider) {
        this.name = name;
        this.version = version;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public String toString() {
        return "Box[name=" + name + ", version=" + version + ", provider=" + provider + "]";
    }

}
