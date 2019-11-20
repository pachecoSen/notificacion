package classes;

public class HTTP {
    public String host;

    public HTTP(String host){
        this.host=host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public HTTP host(String host){
        this.setHost(host);

        return this;
    }
}
