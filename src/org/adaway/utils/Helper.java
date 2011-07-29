/*
 * Copyright (C) 2011 Dominik Schürmann <dominik@dominikschuermann.de>
 *
 * This file is part of AdAway.
 * 
 * AdAway is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AdAway is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AdAway.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.adaway.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.webkit.URLUtil;

public class Helper {
    /*
     * http://stackoverflow.com/questions/106179/regular-expression-to-match-hostname-or-ip-address/
     * 3824105#3824105 with added underscore to match more hosts
     */
    static final private String mHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-\\_]{0,61}[a-zA-Z0-9])\\.)+([a-zA-Z0-9]{2,5})$";
    private static Pattern mHostnamePattern;
    private static Matcher mHostnameMatcher;

    /*
     * http://stackoverflow.com/questions/46146/what-are-the-java-regular-expressions-for-matching-ipv4
     * -and-ipv6-strings
     */
    static final private String mIPv4Regex = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    private static Pattern mIPv4Pattern;
    private static Matcher mIPv4Matcher;

    /*
     * http://forums.dartware.com/viewtopic.php?t=452
     */
    static final private String mIPv6Regex = "^(((?=(?>.*?::)(?!.*::)))(::)?([0-9A-F]{1,4}::?){0,5}|([0-9A-F]{1,4}:){6})(\2([0-9A-F]{1,4}(::?|$)){0,2}|((25[0-5]|(2[0-4]|1\\d|[1-9])?\\d)(\\.|$)){4}|[0-9A-F]{1,4}:[0-9A-F]{1,4})(?<![^:]:|\\.)\\z";
    private static Pattern mIPv6Pattern;
    private static Matcher mIPv6Matcher;

    static {
        mHostnamePattern = Pattern.compile(mHostnameRegex);
        mIPv4Pattern = Pattern.compile(mIPv4Regex);
        mIPv6Pattern = Pattern.compile(mIPv6Regex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Just a wrapper
     * 
     * @param input
     * @return
     */
    static public boolean isValidUrl(String input) {
        return URLUtil.isValidUrl(input);
    }

    /**
     * I could not find any android class that provides checking of an hostnames, thus I am using
     * regex
     * 
     * @param input
     * @return return true if input is valid hostname
     */
    static public boolean isValidHostname(String input) {
        mHostnameMatcher = mHostnamePattern.matcher(input);

        return mHostnameMatcher.find();
    }

    /**
     * Check if input is a valid IPv4 address
     */
    static public boolean isValidIPv4(String input) {
        mIPv4Matcher = mIPv4Pattern.matcher(input);

        return mIPv4Matcher.find();
    }

    /**
     * Check if input is a valid IPv6 address
     */
    static public boolean isValidIPv6(String input) {
        mIPv6Matcher = mIPv6Pattern.matcher(input);

        return mIPv6Matcher.find();
    }

    /**
     * Check if input is a valid IP address
     */
    static public boolean isValidIP(String input) {
        return (isValidIPv4(input) || isValidIPv6(input));
    }
}
