package com.example.pj_grocerystore.api;

public class City {
    private int type;
    private String SolrID;
    private int ID;
    private String Title;
    private int STT;
    private int Created;
    private int Updated;
    private int TotalDoanhNghiep;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSolrID() {
        return SolrID;
    }

    public void setSolrID(String solrID) {
        SolrID = solrID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getCreated() {
        return Created;
    }

    public void setCreated(int created) {
        Created = created;
    }

    public int getUpdated() {
        return Updated;
    }

    public void setUpdated(int updated) {
        Updated = updated;
    }

    public int getTotalDoanhNghiep() {
        return TotalDoanhNghiep;
    }

    public void setTotalDoanhNghiep(int totalDoanhNghiep) {
        TotalDoanhNghiep = totalDoanhNghiep;
    }

}
