
package com.kav.wearoscarconnect.fordmodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DieselSystemStatus {

    @SerializedName("exhaustFluidLevel")
    @Expose
    private ExhaustFluidLevel exhaustFluidLevel;
    @SerializedName("filterSoot")
    @Expose
    private Object filterSoot;
    @SerializedName("ureaRange")
    @Expose
    private UreaRange ureaRange;
    @SerializedName("metricType")
    @Expose
    private MetricType metricType;
    @SerializedName("filterRegenerationStatus")
    @Expose
    private FilterRegenerationStatus filterRegenerationStatus;

    public ExhaustFluidLevel getExhaustFluidLevel() {
        return exhaustFluidLevel;
    }

    public void setExhaustFluidLevel(ExhaustFluidLevel exhaustFluidLevel) {
        this.exhaustFluidLevel = exhaustFluidLevel;
    }

    public Object getFilterSoot() {
        return filterSoot;
    }

    public void setFilterSoot(Object filterSoot) {
        this.filterSoot = filterSoot;
    }

    public UreaRange getUreaRange() {
        return ureaRange;
    }

    public void setUreaRange(UreaRange ureaRange) {
        this.ureaRange = ureaRange;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public FilterRegenerationStatus getFilterRegenerationStatus() {
        return filterRegenerationStatus;
    }

    public void setFilterRegenerationStatus(FilterRegenerationStatus filterRegenerationStatus) {
        this.filterRegenerationStatus = filterRegenerationStatus;
    }

}
