package com.example.radmushroom.contentprovider.link;

// @formatter:off
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.radmushroom.contentprovider.LinkProvider;

/**
 * A human being which is part of a team.
 */
@SuppressWarnings("unused")
public class LinkColumns implements BaseColumns {
    public static final String TABLE_NAME = "link";
    public static final Uri CONTENT_URI = Uri.parse(LinkProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String LINK = "link";

    public static final String TIME = "time";

    public static final String STATUS = "status";


    public static final String DEFAULT_ORDER = null;

    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            LINK,
            TIME,
            STATUS
    };

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(LINK) || c.contains("." + LINK)) return true;
            if (c.equals(TIME) || c.contains("." + TIME)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
        }
        return false;
    }

}
