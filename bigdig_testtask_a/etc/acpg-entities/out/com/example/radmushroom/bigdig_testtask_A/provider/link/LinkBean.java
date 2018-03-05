package com.example.radmushroom.contentprovider.link;

// @formatter:off
import com.example.radmushroom.contentprovider.base.BaseModel;

import java.util.Date;

/**
 * A human being which is part of a team.
 */
@SuppressWarnings({"WeakerAccess", "unused", "ConstantConditions"})
public class LinkBean implements LinkModel {
    private long mId;
    private String mLink;
    private String mTime;
    private Status mStatus;

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        return mId;
    }

    /**
     * Primary key.
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * Get the {@code link} value.
     * Can be {@code null}.
     */
    @Override
    public String getLink() {
        return mLink;
    }

    /**
     * Set the {@code link} value.
     * Can be {@code null}.
     */
    public void setLink(String link) {
        mLink = link;
    }

    /**
     * Get the {@code time} value.
     * Can be {@code null}.
     */
    @Override
    public String getTime() {
        return mTime;
    }

    /**
     * Set the {@code time} value.
     * Can be {@code null}.
     */
    public void setTime(String time) {
        mTime = time;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Override
    public Status getStatus() {
        return mStatus;
    }

    /**
     * Set the {@code status} value.
     * Can be {@code null}.
     */
    public void setStatus(Status status) {
        mStatus = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkBean bean = (LinkBean) o;
        return mId == bean.mId;
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    /**
     * Instantiate a new LinkBean with specified values.
     */
    public static LinkBean newInstance(long id, String link, String time, Status status) {
        LinkBean res = new LinkBean();
        res.mId = id;
        res.mLink = link;
        res.mTime = time;
        res.mStatus = status;
        return res;
    }

    /**
     * Instantiate a new LinkBean with all the values copied from the given model.
     */
    public static LinkBean copy(LinkModel from) {
        LinkBean res = new LinkBean();
        res.mId = from.getId();
        res.mLink = from.getLink();
        res.mTime = from.getTime();
        res.mStatus = from.getStatus();
        return res;
    }

    public static class Builder {
        private LinkBean mRes = new LinkBean();

        /**
         * Primary key.
         */
        public Builder id(long id) {
            mRes.mId = id;
            return this;
        }

        /**
         * Set the {@code link} value.
         * Can be {@code null}.
         */
        public Builder link(String link) {
            mRes.mLink = link;
            return this;
        }

        /**
         * Set the {@code time} value.
         * Can be {@code null}.
         */
        public Builder time(String time) {
            mRes.mTime = time;
            return this;
        }

        /**
         * Set the {@code status} value.
         * Can be {@code null}.
         */
        public Builder status(Status status) {
            mRes.mStatus = status;
            return this;
        }

        /**
         * Get a new LinkBean built with the given values.
         */
        public LinkBean build() {
            return mRes;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
