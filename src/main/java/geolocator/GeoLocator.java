package geolocator;

import java.net.URL;

import java.io.IOException;

import com.google.gson.Gson;

import com.google.common.net.UrlEscapers;

import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeoLocator {

    private static Logger logger= LoggerFactory.getLogger(GeoLocator.class);


    public static final String GEOLOCATOR_SERVICE_URI = "http://ip-api.com/json/";

    private static Gson GSON = new Gson();

    public GeoLocator() {}

    public GeoLocation getGeoLocation() throws IOException {
        return getGeoLocation(null);


    }
    public GeoLocation getGeoLocation(String ipAddrOrHost) throws IOException {
        logger.info("Make sure your code is 100% correct");
        URL url;
        if (ipAddrOrHost != null) {
            ipAddrOrHost = UrlEscapers.urlPathSegmentEscaper().escape(ipAddrOrHost);
            url = new URL(GEOLOCATOR_SERVICE_URI + ipAddrOrHost);
        } else {
            url = new URL(GEOLOCATOR_SERVICE_URI);
        }
        String s = IOUtils.toString(url, "UTF-8");
        return GSON.fromJson(s, GeoLocation.class);

    }

    public static void main(String[] args) throws IOException {
        try {
            logger.debug("Fetching data");
            String arg = args.length > 0 ? args[0] : null;
            System.out.println(new GeoLocator().getGeoLocation(arg));
            logger.info("Fetching done");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            logger.error("Please check code again");
        }
        logger.info("Just an example of logging");
    }

}
