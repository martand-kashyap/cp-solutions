package designpatterns.structural.proxy;

import java.util.ArrayList;
import java.util.List;

class ProxyInternet implements Internet {
    private final RealInternet realInternet;
    private static final List<String> bannedSites;

    static {
        bannedSites = new ArrayList<>();
        bannedSites.add("abc.com");
        bannedSites.add("def.com");
        bannedSites.add("xyz.com");
    }

    ProxyInternet() {
        this.realInternet = new RealInternet();
    }

    @Override
    public void connectTo(String hostName) throws Exception {
        if (bannedSites.contains(hostName)) {
            throw new Exception(hostName + " has been banned.");
        }
        realInternet.connectTo(hostName);
    }
}
