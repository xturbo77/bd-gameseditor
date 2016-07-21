package de.xturbo77.bitdefender.gameseditor.steam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author schmidt
 */
public class ISteamAppsClient {

    private static final Logger LOG = LoggerFactory.getLogger(ISteamAppsClient.class);
    private static final String STEAM_APPS_SEARCH_ENDPOINT = "https://api.steampowered.com/ISteamApps/GetAppList/v2";

    private SteamApps steamApps;

    public SteamApp getSteamApp(int appid) {
        if (steamApps == null) {
            steamApps = loadSteamApps();
        }
        for (SteamApp app : steamApps.getApps()) {
            if (appid == app.getAppid()) {
                return app;
            }
        }
        return null;
    }

    private SteamApps loadSteamApps() {
        Client client = ClientBuilder.newClient();
        WebTarget resourceTarget = client.target(STEAM_APPS_SEARCH_ENDPOINT);
        Invocation invocation = resourceTarget.request(MediaType.APPLICATION_JSON_TYPE).buildGet();
        SteamAppsResponse response = invocation.invoke(SteamAppsResponse.class);
        SteamApps apps = response.getApplist();
        LOG.info("loaded {} steamApps infos", apps.size());
        return apps;
    }

}
