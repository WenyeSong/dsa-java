package edu.emory.cs.trie;

import java.util.Comparator;

/** @author Jinho D. Choi */
public class Entity {
    public int begin_index;
    public int end_index;
    public int country_id;

    /**
     * @param begin_index the offset of the first character (inclusive).
     * @param end_index the offset of the last character (exclusive).
     * @param country_id the unique country ID.
     */
    public Entity(int begin_index, int end_index, int country_id) {
        this.begin_index = begin_index;
        this.end_index = end_index;
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", begin_index, end_index, country_id);
    }
}