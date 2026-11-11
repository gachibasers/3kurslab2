package com.lab2.gems.model;

import java.util.Objects;

/**
 * Visual parameters of a gemstone: color, transparency (0-100%), facets (4-15).
 * Corresponds to XSD complex type VisualParametersType.
 */
public class VisualParameters {

    private Color color;
    private double transparency;
    private int facets;

    public VisualParameters() {
    }

    public VisualParameters(Color color, double transparency, int facets) {
        this.color = color;
        this.transparency = transparency;
        this.facets = facets;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public int getFacets() {
        return facets;
    }

    public void setFacets(int facets) {
        this.facets = facets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisualParameters)) {
            return false;
        }
        VisualParameters that = (VisualParameters) o;
        return Double.compare(that.transparency, transparency) == 0
                && facets == that.facets
                && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, transparency, facets);
    }

    @Override
    public String toString() {
        return String.format("VisualParameters[color=%s, transparency=%.1f%%, facets=%d]",
                color, transparency, facets);
    }
}
