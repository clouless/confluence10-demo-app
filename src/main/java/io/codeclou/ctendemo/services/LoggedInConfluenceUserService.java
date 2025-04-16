package io.codeclou.ctendemo.services;

import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;

import com.atlassian.confluence.user.UserPreferences;
import com.atlassian.confluence.user.UserPreferencesAccessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// SPRING-BEAN
public class LoggedInConfluenceUserService {

    private UserPreferencesAccessor userAccessor;

    public LoggedInConfluenceUserService(UserPreferencesAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }




    public ConfluenceUser getLoggedInUser() {
        return getLoggedInConfluenceUser();
    }

    protected ConfluenceUser getLoggedInConfluenceUser() {
        return AuthenticatedUserThreadLocal.get();
    }

    public String getRemoteUserTimeZone() {
        ConfluenceUser user = getLoggedInConfluenceUser();
        if (user != null) {
            UserPreferences preferences = userAccessor.getUserPreferences(user);
            if (preferences != null && preferences.getTimeZone() != null) {
                return preferences.getTimeZone().getID();
            }
        }
        return "";
        // When no timeZone is passed, the frontend will detect the timeZone by the
        // browser. That is also the way Confluence does it.
    }

    public String getRemoteUserLocale() {
        ConfluenceUser user = getLoggedInConfluenceUser();
        if (user != null) {
            UserPreferences preferences = userAccessor.getUserPreferences(user);
            if (preferences != null && preferences.getLocale() != null) {
                return preferences.getLocale().toLanguageTag();
            }
        }
        return "";
        // When no locale is passed, the frontend will detect the language by the
        // browser lang. That is also the way Confluence does it.
    }

    public String getUtcIsoDateString(Date d) {
        if (d != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+0000'", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            return format.format(d);
        }
        return "1970-01-01T04:20:00+0000";
    }
}
