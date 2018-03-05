package com.example.radmushroom.contentprovider.link;

// @formatter:off

import android.database.Cursor;

import com.example.radmushroom.contentprovider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code link} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnnecessaryLocalVariable"})
public class LinkCursor extends AbstractCursor implements LinkModel {
    public LinkCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        Long res = getLongOrNull(LinkColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code link} value.
     * Can be {@code null}.
     */
    @Override
    public String getLink() {
        String res = getStringOrNull(LinkColumns.LINK);
        return res;
    }

    /**
     * Get the {@code time} value.
     * Can be {@code null}.
     */
    @Override
    public String getTime() {
        String res = getStringOrNull(LinkColumns.TIME);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Override
    public Status getStatus() {
        Integer intValue = getIntegerOrNull(LinkColumns.STATUS);
        if (intValue == null) return null;
        return Status.values()[intValue];
    }
}
