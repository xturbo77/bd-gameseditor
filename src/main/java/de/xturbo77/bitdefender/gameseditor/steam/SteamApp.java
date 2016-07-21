package de.xturbo77.bitdefender.gameseditor.steam;

/**
 *
 * @author schmidt
 */
public class SteamApp {

    private int appid;
    private String name;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + " [" + getAppid() + "]";
    }

}
