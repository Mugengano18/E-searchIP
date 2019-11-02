
package com.android1.shoplarity.Apifolder;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Apiresponse {

    @SerializedName("businesses")
    @Expose
    public List<Business> businesses = null;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("region")
    @Expose
    public Region region;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Apiresponse() {
    }

    /**
     * 
     * @param total
     * @param region
     * @param businesses
     */
    public Apiresponse(List<Business> businesses, Integer total, Region region) {
        super();
        this.businesses = businesses;
        this.total = total;
        this.region = region;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
