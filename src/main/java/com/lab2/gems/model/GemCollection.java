package com.lab2.gems.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Root element Gem containing a collection of gemstones.
 * Corresponds to XSD root element Gem.
 */
public class GemCollection {

    private List<Gemstone> gemstones;

    public GemCollection() {
        this.gemstones = new ArrayList<>();
    }

    public GemCollection(List<Gemstone> gemstones) {
        this.gemstones = new ArrayList<>(Objects.requireNonNull(gemstones, "gemstones"));
    }

    public List<Gemstone> getGemstones() {
        return gemstones;
    }

    public void setGemstones(List<Gemstone> gemstones) {
        this.gemstones = new ArrayList<>(Objects.requireNonNull(gemstones, "gemstones"));
    }

    public void addGemstone(Gemstone gemstone) {
        this.gemstones.add(gemstone);
    }

    public int size() {
        return gemstones.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GemCollection)) {
            return false;
        }
        GemCollection that = (GemCollection) o;
        return Objects.equals(gemstones, that.gemstones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemstones);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GemCollection[").append(gemstones.size()).append(" gemstones]:");
        for (Gemstone g : gemstones) {
            sb.append(System.lineSeparator()).append("  ").append(g);
        }
        return sb.toString();
    }
}
