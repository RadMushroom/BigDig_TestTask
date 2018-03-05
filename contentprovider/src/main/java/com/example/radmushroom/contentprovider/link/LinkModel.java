package com.example.radmushroom.contentprovider.link;

// @formatter:off
import com.example.radmushroom.contentprovider.base.BaseModel;

/**
 * A human being which is part of a team.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface LinkModel extends BaseModel {

    /**
     * Primary key.
     */
    long getId();

    /**
     * Get the {@code link} value.
     * Can be {@code null}.
     */
    String getLink();

    /**
     * Get the {@code time} value.
     * Can be {@code null}.
     */
    String getTime();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    Status getStatus();
}
