package ir.codefather.mongodemo.Utils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {
    /**
     * get host name to call API
     */
    public static String getHost(HttpServletRequest request) {
        try {
            var url = new URL(request.getRequestURL().toString());

            return url.getProtocol() + "://" + url.getHost() + ":" + url.getPort();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
