
package com.kav.wearoscarconnect.fordmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehiclestatus {

    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("lockStatus")
    @Expose
    private LockStatus lockStatus;
    @SerializedName("alarm")
    @Expose
    private Alarm alarm;
    @SerializedName("PrmtAlarmEvent")
    @Expose
    private PrmtAlarmEvent prmtAlarmEvent;
    @SerializedName("odometer")
    @Expose
    private Odometer odometer;
    @SerializedName("fuel")
    @Expose
    private Fuel fuel;
    @SerializedName("gps")
    @Expose
    private Gps gps;
    @SerializedName("remoteStart")
    @Expose
    private RemoteStart remoteStart;
    @SerializedName("remoteStartStatus")
    @Expose
    private RemoteStartStatus remoteStartStatus;
    @SerializedName("battery")
    @Expose
    private Battery battery;
    @SerializedName("oil")
    @Expose
    private Oil oil;
    @SerializedName("tirePressure")
    @Expose
    private TirePressure tirePressure;
    @SerializedName("authorization")
    @Expose
    private String authorization;
    @SerializedName("TPMS")
    @Expose
    private Tpms tpms;
    @SerializedName("firmwareUpgInProgress")
    @Expose
    private FirmwareUpgInProgress firmwareUpgInProgress;
    @SerializedName("deepSleepInProgress")
    @Expose
    private DeepSleepInProgress deepSleepInProgress;
    @SerializedName("ccsSettings")
    @Expose
    private CcsSettings ccsSettings;
    @SerializedName("lastRefresh")
    @Expose
    private String lastRefresh;
    @SerializedName("lastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("serverTime")
    @Expose
    private String serverTime;
    @SerializedName("batteryFillLevel")
    @Expose
    private Object batteryFillLevel;
    @SerializedName("elVehDTE")
    @Expose
    private Object elVehDTE;
    @SerializedName("hybridModeStatus")
    @Expose
    private Object hybridModeStatus;
    @SerializedName("chargingStatus")
    @Expose
    private Object chargingStatus;
    @SerializedName("plugStatus")
    @Expose
    private Object plugStatus;
    @SerializedName("chargeStartTime")
    @Expose
    private Object chargeStartTime;
    @SerializedName("chargeEndTime")
    @Expose
    private Object chargeEndTime;
    @SerializedName("preCondStatusDsply")
    @Expose
    private Object preCondStatusDsply;
    @SerializedName("chargerPowertype")
    @Expose
    private Object chargerPowertype;
    @SerializedName("batteryPerfStatus")
    @Expose
    private Object batteryPerfStatus;
    @SerializedName("outandAbout")
    @Expose
    private OutandAbout outandAbout;
    @SerializedName("batteryChargeStatus")
    @Expose
    private Object batteryChargeStatus;
    @SerializedName("dcFastChargeData")
    @Expose
    private Object dcFastChargeData;
    @SerializedName("windowPosition")
    @Expose
    private WindowPosition windowPosition;
    @SerializedName("doorStatus")
    @Expose
    private DoorStatus doorStatus;
    @SerializedName("ignitionStatus")
    @Expose
    private IgnitionStatus ignitionStatus;
    @SerializedName("batteryTracLowChargeThreshold")
    @Expose
    private Object batteryTracLowChargeThreshold;
    @SerializedName("battTracLoSocDDsply")
    @Expose
    private Object battTracLoSocDDsply;
    @SerializedName("dieselSystemStatus")
    @Expose
    private DieselSystemStatus dieselSystemStatus;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public PrmtAlarmEvent getPrmtAlarmEvent() {
        return prmtAlarmEvent;
    }

    public void setPrmtAlarmEvent(PrmtAlarmEvent prmtAlarmEvent) {
        this.prmtAlarmEvent = prmtAlarmEvent;
    }

    public Odometer getOdometer() {
        return odometer;
    }

    public void setOdometer(Odometer odometer) {
        this.odometer = odometer;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public RemoteStart getRemoteStart() {
        return remoteStart;
    }

    public void setRemoteStart(RemoteStart remoteStart) {
        this.remoteStart = remoteStart;
    }

    public RemoteStartStatus getRemoteStartStatus() {
        return remoteStartStatus;
    }

    public void setRemoteStartStatus(RemoteStartStatus remoteStartStatus) {
        this.remoteStartStatus = remoteStartStatus;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Oil getOil() {
        return oil;
    }

    public void setOil(Oil oil) {
        this.oil = oil;
    }

    public TirePressure getTirePressure() {
        return tirePressure;
    }

    public void setTirePressure(TirePressure tirePressure) {
        this.tirePressure = tirePressure;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Tpms getTpms() {
        return tpms;
    }

    public void setTpms(Tpms tpms) {
        this.tpms = tpms;
    }

    public FirmwareUpgInProgress getFirmwareUpgInProgress() {
        return firmwareUpgInProgress;
    }

    public void setFirmwareUpgInProgress(FirmwareUpgInProgress firmwareUpgInProgress) {
        this.firmwareUpgInProgress = firmwareUpgInProgress;
    }

    public DeepSleepInProgress getDeepSleepInProgress() {
        return deepSleepInProgress;
    }

    public void setDeepSleepInProgress(DeepSleepInProgress deepSleepInProgress) {
        this.deepSleepInProgress = deepSleepInProgress;
    }

    public CcsSettings getCcsSettings() {
        return ccsSettings;
    }

    public void setCcsSettings(CcsSettings ccsSettings) {
        this.ccsSettings = ccsSettings;
    }

    public String getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(String lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public Object getBatteryFillLevel() {
        return batteryFillLevel;
    }

    public void setBatteryFillLevel(Object batteryFillLevel) {
        this.batteryFillLevel = batteryFillLevel;
    }

    public Object getElVehDTE() {
        return elVehDTE;
    }

    public void setElVehDTE(Object elVehDTE) {
        this.elVehDTE = elVehDTE;
    }

    public Object getHybridModeStatus() {
        return hybridModeStatus;
    }

    public void setHybridModeStatus(Object hybridModeStatus) {
        this.hybridModeStatus = hybridModeStatus;
    }

    public Object getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(Object chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    public Object getPlugStatus() {
        return plugStatus;
    }

    public void setPlugStatus(Object plugStatus) {
        this.plugStatus = plugStatus;
    }

    public Object getChargeStartTime() {
        return chargeStartTime;
    }

    public void setChargeStartTime(Object chargeStartTime) {
        this.chargeStartTime = chargeStartTime;
    }

    public Object getChargeEndTime() {
        return chargeEndTime;
    }

    public void setChargeEndTime(Object chargeEndTime) {
        this.chargeEndTime = chargeEndTime;
    }

    public Object getPreCondStatusDsply() {
        return preCondStatusDsply;
    }

    public void setPreCondStatusDsply(Object preCondStatusDsply) {
        this.preCondStatusDsply = preCondStatusDsply;
    }

    public Object getChargerPowertype() {
        return chargerPowertype;
    }

    public void setChargerPowertype(Object chargerPowertype) {
        this.chargerPowertype = chargerPowertype;
    }

    public Object getBatteryPerfStatus() {
        return batteryPerfStatus;
    }

    public void setBatteryPerfStatus(Object batteryPerfStatus) {
        this.batteryPerfStatus = batteryPerfStatus;
    }

    public OutandAbout getOutandAbout() {
        return outandAbout;
    }

    public void setOutandAbout(OutandAbout outandAbout) {
        this.outandAbout = outandAbout;
    }

    public Object getBatteryChargeStatus() {
        return batteryChargeStatus;
    }

    public void setBatteryChargeStatus(Object batteryChargeStatus) {
        this.batteryChargeStatus = batteryChargeStatus;
    }

    public Object getDcFastChargeData() {
        return dcFastChargeData;
    }

    public void setDcFastChargeData(Object dcFastChargeData) {
        this.dcFastChargeData = dcFastChargeData;
    }

    public WindowPosition getWindowPosition() {
        return windowPosition;
    }

    public void setWindowPosition(WindowPosition windowPosition) {
        this.windowPosition = windowPosition;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public IgnitionStatus getIgnitionStatus() {
        return ignitionStatus;
    }

    public void setIgnitionStatus(IgnitionStatus ignitionStatus) {
        this.ignitionStatus = ignitionStatus;
    }

    public Object getBatteryTracLowChargeThreshold() {
        return batteryTracLowChargeThreshold;
    }

    public void setBatteryTracLowChargeThreshold(Object batteryTracLowChargeThreshold) {
        this.batteryTracLowChargeThreshold = batteryTracLowChargeThreshold;
    }

    public Object getBattTracLoSocDDsply() {
        return battTracLoSocDDsply;
    }

    public void setBattTracLoSocDDsply(Object battTracLoSocDDsply) {
        this.battTracLoSocDDsply = battTracLoSocDDsply;
    }

    public DieselSystemStatus getDieselSystemStatus() {
        return dieselSystemStatus;
    }

    public void setDieselSystemStatus(DieselSystemStatus dieselSystemStatus) {
        this.dieselSystemStatus = dieselSystemStatus;
    }

}
