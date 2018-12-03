package com.aps.bayes.dc.entity;

/**
 *
 */
public class KeyProbability {
    private int key;
    private double probability;

    public KeyProbability() {
    }

    public KeyProbability(int key, double probability) {
        this.key = key;
        this.probability = probability;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return String.format("%d => %f", key, probability);
    }
}
