
package com.kav.wearoscarconnect.fordmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tpms {

    @SerializedName("tirePressureByLocation")
    @Expose
    private TirePressureByLocation tirePressureByLocation;
    @SerializedName("tirePressureSystemStatus")
    @Expose
    private TirePressureSystemStatus tirePressureSystemStatus;
    @SerializedName("dualRearWheel")
    @Expose
    private DualRearWheel dualRearWheel;
    @SerializedName("leftFrontTireStatus")
    @Expose
    private LeftFrontTireStatus leftFrontTireStatus;
    @SerializedName("leftFrontTirePressure")
    @Expose
    private LeftFrontTirePressure leftFrontTirePressure;
    @SerializedName("rightFrontTireStatus")
    @Expose
    private RightFrontTireStatus rightFrontTireStatus;
    @SerializedName("rightFrontTirePressure")
    @Expose
    private RightFrontTirePressure rightFrontTirePressure;
    @SerializedName("outerLeftRearTireStatus")
    @Expose
    private OuterLeftRearTireStatus outerLeftRearTireStatus;
    @SerializedName("outerLeftRearTirePressure")
    @Expose
    private OuterLeftRearTirePressure outerLeftRearTirePressure;
    @SerializedName("outerRightRearTireStatus")
    @Expose
    private OuterRightRearTireStatus outerRightRearTireStatus;
    @SerializedName("outerRightRearTirePressure")
    @Expose
    private OuterRightRearTirePressure outerRightRearTirePressure;
    @SerializedName("innerLeftRearTireStatus")
    @Expose
    private InnerLeftRearTireStatus innerLeftRearTireStatus;
    @SerializedName("innerLeftRearTirePressure")
    @Expose
    private Object innerLeftRearTirePressure;
    @SerializedName("innerRightRearTireStatus")
    @Expose
    private InnerRightRearTireStatus innerRightRearTireStatus;
    @SerializedName("innerRightRearTirePressure")
    @Expose
    private Object innerRightRearTirePressure;
    @SerializedName("recommendedFrontTirePressure")
    @Expose
    private RecommendedFrontTirePressure recommendedFrontTirePressure;
    @SerializedName("recommendedRearTirePressure")
    @Expose
    private RecommendedRearTirePressure recommendedRearTirePressure;

    public TirePressureByLocation getTirePressureByLocation() {
        return tirePressureByLocation;
    }

    public void setTirePressureByLocation(TirePressureByLocation tirePressureByLocation) {
        this.tirePressureByLocation = tirePressureByLocation;
    }

    public TirePressureSystemStatus getTirePressureSystemStatus() {
        return tirePressureSystemStatus;
    }

    public void setTirePressureSystemStatus(TirePressureSystemStatus tirePressureSystemStatus) {
        this.tirePressureSystemStatus = tirePressureSystemStatus;
    }

    public DualRearWheel getDualRearWheel() {
        return dualRearWheel;
    }

    public void setDualRearWheel(DualRearWheel dualRearWheel) {
        this.dualRearWheel = dualRearWheel;
    }

    public LeftFrontTireStatus getLeftFrontTireStatus() {
        return leftFrontTireStatus;
    }

    public void setLeftFrontTireStatus(LeftFrontTireStatus leftFrontTireStatus) {
        this.leftFrontTireStatus = leftFrontTireStatus;
    }

    public LeftFrontTirePressure getLeftFrontTirePressure() {
        return leftFrontTirePressure;
    }

    public void setLeftFrontTirePressure(LeftFrontTirePressure leftFrontTirePressure) {
        this.leftFrontTirePressure = leftFrontTirePressure;
    }

    public RightFrontTireStatus getRightFrontTireStatus() {
        return rightFrontTireStatus;
    }

    public void setRightFrontTireStatus(RightFrontTireStatus rightFrontTireStatus) {
        this.rightFrontTireStatus = rightFrontTireStatus;
    }

    public RightFrontTirePressure getRightFrontTirePressure() {
        return rightFrontTirePressure;
    }

    public void setRightFrontTirePressure(RightFrontTirePressure rightFrontTirePressure) {
        this.rightFrontTirePressure = rightFrontTirePressure;
    }

    public OuterLeftRearTireStatus getOuterLeftRearTireStatus() {
        return outerLeftRearTireStatus;
    }

    public void setOuterLeftRearTireStatus(OuterLeftRearTireStatus outerLeftRearTireStatus) {
        this.outerLeftRearTireStatus = outerLeftRearTireStatus;
    }

    public OuterLeftRearTirePressure getOuterLeftRearTirePressure() {
        return outerLeftRearTirePressure;
    }

    public void setOuterLeftRearTirePressure(OuterLeftRearTirePressure outerLeftRearTirePressure) {
        this.outerLeftRearTirePressure = outerLeftRearTirePressure;
    }

    public OuterRightRearTireStatus getOuterRightRearTireStatus() {
        return outerRightRearTireStatus;
    }

    public void setOuterRightRearTireStatus(OuterRightRearTireStatus outerRightRearTireStatus) {
        this.outerRightRearTireStatus = outerRightRearTireStatus;
    }

    public OuterRightRearTirePressure getOuterRightRearTirePressure() {
        return outerRightRearTirePressure;
    }

    public void setOuterRightRearTirePressure(OuterRightRearTirePressure outerRightRearTirePressure) {
        this.outerRightRearTirePressure = outerRightRearTirePressure;
    }

    public InnerLeftRearTireStatus getInnerLeftRearTireStatus() {
        return innerLeftRearTireStatus;
    }

    public void setInnerLeftRearTireStatus(InnerLeftRearTireStatus innerLeftRearTireStatus) {
        this.innerLeftRearTireStatus = innerLeftRearTireStatus;
    }

    public Object getInnerLeftRearTirePressure() {
        return innerLeftRearTirePressure;
    }

    public void setInnerLeftRearTirePressure(Object innerLeftRearTirePressure) {
        this.innerLeftRearTirePressure = innerLeftRearTirePressure;
    }

    public InnerRightRearTireStatus getInnerRightRearTireStatus() {
        return innerRightRearTireStatus;
    }

    public void setInnerRightRearTireStatus(InnerRightRearTireStatus innerRightRearTireStatus) {
        this.innerRightRearTireStatus = innerRightRearTireStatus;
    }

    public Object getInnerRightRearTirePressure() {
        return innerRightRearTirePressure;
    }

    public void setInnerRightRearTirePressure(Object innerRightRearTirePressure) {
        this.innerRightRearTirePressure = innerRightRearTirePressure;
    }

    public RecommendedFrontTirePressure getRecommendedFrontTirePressure() {
        return recommendedFrontTirePressure;
    }

    public void setRecommendedFrontTirePressure(RecommendedFrontTirePressure recommendedFrontTirePressure) {
        this.recommendedFrontTirePressure = recommendedFrontTirePressure;
    }

    public RecommendedRearTirePressure getRecommendedRearTirePressure() {
        return recommendedRearTirePressure;
    }

    public void setRecommendedRearTirePressure(RecommendedRearTirePressure recommendedRearTirePressure) {
        this.recommendedRearTirePressure = recommendedRearTirePressure;
    }

}
