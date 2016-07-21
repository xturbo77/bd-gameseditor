package de.xturbo77.bitdefender.gameseditor.steam;

import java.util.List;

/**
 *
 * @author schmidt
 */
public class SteamApps {

    private List<SteamApp> apps;

    public List<SteamApp> getApps() {
        return apps;
    }

    public void setApps(List<SteamApp> apps) {
        this.apps = apps;
    }

    public int size() {
        return apps != null ? apps.size() : 0;
    }

}
