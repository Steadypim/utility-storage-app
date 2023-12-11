package dev.steadypim.thewhitehw.homework1.general.network;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class NetworkInfoProvider {

    private NetworkInfoProvider() {
        throw new IllegalStateException("Utility class");
    }

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String obtainIpAddress(HttpServletRequest request) {
        String ipAddress = extractIpAddressFromHeaders(request);

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = extractIpAddressFromRemoteAddress(request);
        }

        if (ipAddress != null && ipAddress.length() > 15 && ipAddress.contains(",")) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }

    private static String extractIpAddressFromHeaders(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ipAddress = request.getHeader(header);
            if (isValidIpAddress(ipAddress)) {
                return ipAddress;
            }
        }
        return null;
    }

    private static String extractIpAddressFromRemoteAddress(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        if (isLocalhostIpAddress(ipAddress)) {
            InetAddress inet = getLocalHostInetAddress();
            ipAddress = (inet != null) ? inet.getHostAddress() : null;
        }
        return ipAddress;
    }

    private static boolean isValidIpAddress(String ipAddress) {
        return ipAddress != null && !ipAddress.isEmpty() && !"unknown".equalsIgnoreCase(ipAddress);
    }

    private static boolean isLocalhostIpAddress(String ipAddress) {
        return ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1");
    }

    private static InetAddress getLocalHostInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String obtainUserAgent(ServletRequestAttributes attributes) {

        HttpServletRequest request = attributes.getRequest();

        return request.getHeader("User-Agent");
    }

}
