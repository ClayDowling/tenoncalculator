package com.lazarusid.tenoncalculator;

/**
 * Created by clay on 7/7/15.
 */
public class TenonModel {
    private Double leg = 0.0;
    private Double railWidth = 0.0;
    private Double tenonWidth = 0.0;
    private Double frontTenonShoulder = 0.0;
    private Double railSetback = 0.0;

    public Double getLeg() {
        return leg;
    }

    public void setLeg(Double leg) {
        this.leg = leg;
    }

    public Double getRailWidth() {
        return railWidth;
    }

    public void setRailWidth(Double railWidth) {
        this.railWidth = railWidth;
    }

    public Double getTenonWidth() {
        if (tenonWidth > 0.0) {
            return tenonWidth;
        } else {
            return railWidth / 3.0;
        }
    }

    public void setTenonWidth(Double tenonWidth) {
        this.tenonWidth = tenonWidth;
    }

    public Double getFrontTenonShoulder() {
        if (frontTenonShoulder > 0.0) {
            return frontTenonShoulder;
        } else {
            return (railWidth - getTenonWidth()) / 2.0;
        }
    }

    public void setFrontTenonShoulder(Double frontTenonShoulder) {
        this.frontTenonShoulder = frontTenonShoulder;
    }

    public Double getRailSetback() {
        return railSetback;
    }

    public void setRailSetback(Double railSetback) {
        this.railSetback = railSetback;
    }

    public Double getMortiseShoulder() {
          return getFrontTenonShoulder() + getRailSetback();
    }

    public Double getMortiseDepth() {
        if (leg == 0.0 || railWidth == 0.0) {
            return 0.0;
        } else {
            return leg - getMortiseShoulder();
        }
    }

    public Double getMortiseInsideDepth() {
        return getMortiseDepth() - getTenonWidth();
    }

}
