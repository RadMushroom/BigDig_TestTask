package com.example.radmushroom.contentprovider.link;

// @formatter:off

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import com.example.radmushroom.contentprovider.base.AbstractSelection;

/**
 * Selection for the {@code link} table.
 */
@SuppressWarnings({"unused", "WeakerAccess", "Recycle"})
public class LinkSelection extends AbstractSelection<LinkSelection> {
    @Override
    protected Uri baseUri() {
        return LinkColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code LinkCursor} object, which is positioned before the first entry, or null.
     */
    public LinkCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new LinkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public LinkCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code LinkCursor} object, which is positioned before the first entry, or null.
     */
    public LinkCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new LinkCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public LinkCursor query(Context context) {
        return query(context, null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CursorLoader getCursorLoader(Context context, String[] projection) {
        return new CursorLoader(context, uri(), projection, sel(), args(), order()) {
            @Override
            public Cursor loadInBackground() {
                return new LinkCursor(super.loadInBackground());
            }
        };
    }


    public LinkSelection id(long... value) {
        addEquals("link." + LinkColumns._ID, toObjectArray(value));
        return this;
    }

    public LinkSelection idNot(long... value) {
        addNotEquals("link." + LinkColumns._ID, toObjectArray(value));
        return this;
    }

    public LinkSelection orderById(boolean desc) {
        orderBy("link." + LinkColumns._ID, desc);
        return this;
    }

    public LinkSelection orderById() {
        return orderById(false);
    }

    public LinkSelection link(String... value) {
        addEquals(LinkColumns.LINK, value);
        return this;
    }

    public LinkSelection linkNot(String... value) {
        addNotEquals(LinkColumns.LINK, value);
        return this;
    }

    public LinkSelection linkLike(String... value) {
        addLike(LinkColumns.LINK, value);
        return this;
    }

    public LinkSelection linkContains(String... value) {
        addContains(LinkColumns.LINK, value);
        return this;
    }

    public LinkSelection linkStartsWith(String... value) {
        addStartsWith(LinkColumns.LINK, value);
        return this;
    }

    public LinkSelection linkEndsWith(String... value) {
        addEndsWith(LinkColumns.LINK, value);
        return this;
    }

    public LinkSelection orderByLink(boolean desc) {
        orderBy(LinkColumns.LINK, desc);
        return this;
    }

    public LinkSelection orderByLink() {
        orderBy(LinkColumns.LINK, false);
        return this;
    }

    public LinkSelection time(String... value) {
        addEquals(LinkColumns.TIME, value);
        return this;
    }

    public LinkSelection timeNot(String... value) {
        addNotEquals(LinkColumns.TIME, value);
        return this;
    }

    public LinkSelection timeLike(String... value) {
        addLike(LinkColumns.TIME, value);
        return this;
    }

    public LinkSelection timeContains(String... value) {
        addContains(LinkColumns.TIME, value);
        return this;
    }

    public LinkSelection timeStartsWith(String... value) {
        addStartsWith(LinkColumns.TIME, value);
        return this;
    }

    public LinkSelection timeEndsWith(String... value) {
        addEndsWith(LinkColumns.TIME, value);
        return this;
    }

    public LinkSelection orderByTime(boolean desc) {
        orderBy(LinkColumns.TIME, desc);
        return this;
    }

    public LinkSelection orderByTime() {
        orderBy(LinkColumns.TIME, false);
        return this;
    }

    public LinkSelection status(Status... value) {
        addEquals(LinkColumns.STATUS, value);
        return this;
    }

    public LinkSelection statusNot(Status... value) {
        addNotEquals(LinkColumns.STATUS, value);
        return this;
    }


    public LinkSelection orderByStatus(boolean desc) {
        orderBy(LinkColumns.STATUS, desc);
        return this;
    }

    public LinkSelection orderByStatus() {
        orderBy(LinkColumns.STATUS, false);
        return this;
    }
}
