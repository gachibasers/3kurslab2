package com.lab2.gems.model;

import java.util.Objects;

/**
 * A single gemstone (XSD complex type GemstoneType).
 * Holds id (xs:ID), preciousness (required attribute), name, origin,
 * visual parameters and value (weight in carats).
 */
public class Gemstone {

    private String id;
    private Preciousness preciousness;
    private String name;
    private String origin;
    private VisualParameters visualParameters;
    private double value;

    public Gemstone() {
    }

    public Gemstone(String id, Preciousness preciousness, String name, String origin,
                    VisualParameters visualParameters, double value) {
        this.id = id;
        this.preciousness = preciousness;
        this.name = name;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gemstone)) {
            return false;
        }
        Gemstone gemstone = (Gemstone) o;
        return Double.compare(gemstone.value, value) == 0
                && Objects.equals(id, gemstone.id)
                && preciousness == gemstone.preciousness
                && Objects.equals(name, gemstone.name)
                && Objects.equals(origin, gemstone.origin)
                && Objects.equals(visualParameters, gemstone.visualParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preciousness, name, origin, visualParameters, value);
    }

    @Override
    public String toString() {
        return String.format(
                "Gemstone[id=%s, preciousness=%s, name='%s', origin='%s', vp=%s, value=%.2f ct]",
                id, preciousness, name, origin, visualParameters, value);
    }
}
