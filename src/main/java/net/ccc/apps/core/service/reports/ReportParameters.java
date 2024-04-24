package net.ccc.apps.core.service.reports;

import java.sql.Date;
import java.util.List;

public class ReportParameters {

    Date from;
    Date to;
    String isono;
    List<String> weldersList;
    List<String> spoolNoList;


    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public List<String> getWeldersList() {
        return weldersList;
    }

    public void setWeldersList(List<String> weldersList) {
        this.weldersList = weldersList;
    }

    public String getIsono() {
        return isono;
    }

    public void setIsono(String isono) {
        this.isono = isono;
    }

    public List<String> getSpoolNoList() {
        return spoolNoList;
    }

    public void setSpoolNoList(List<String> spoolNoList) {
        this.spoolNoList = spoolNoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportParameters{");
        sb.append("from=").append(from);
        sb.append(", to=").append(to);
        sb.append(", isono='").append(isono).append('\'');
        sb.append(", weldersList=").append(weldersList);
        sb.append(", spoolNoList=").append(spoolNoList);
        sb.append('}');
        return sb.toString();
    }
}
