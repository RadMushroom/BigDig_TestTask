package com.example.radmushroom.contentprovider.link;

// @formatter:off

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import com.example.radmushroom.contentprovider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code link} table.
 */
@SuppressWarnings({"ConstantConditions", "unused"})
public class LinkContentValues extends AbstractContentValues<LinkContentValues> {
    @Override
    protected Uri baseUri() {
        return LinkColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  LinkSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The context to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  LinkSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public LinkContentValues putLink(String value) {
        mContentValues.put(LinkColumns.LINK, value);
        return this;
    }

    public LinkContentValues putLinkNull() {
        mContentValues.putNull(LinkColumns.LINK);
        return this;
    }

    public LinkContentValues putTime(String value) {
        mContentValues.put(LinkColumns.TIME, value);
        return this;
    }

    public LinkContentValues putTimeNull() {
        mContentValues.putNull(LinkColumns.TIME);
        return this;
    }

    public LinkContentValues putStatus(Status value) {
        mContentValues.put(LinkColumns.STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public LinkContentValues putStatusNull() {
        mContentValues.putNull(LinkColumns.STATUS);
        return this;
    }
}
