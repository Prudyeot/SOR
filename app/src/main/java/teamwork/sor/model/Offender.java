package teamwork.sor.model;

/**
 * Created by Prudence on 02/12/2017.
 */
public class Offender {
    private String image;
    private String sex;
    private String name;
    private String residence;
    private String docket;
    private String offence;
    private String offenceCount;
    private String pdfUrl;


    public Offender(String image, String sex, String name, String residence, String docket, String offence, String offenceCount,String pdfUrl) {
        this.image = image;
        this.sex = sex;
        this.name = name;
        this.residence = residence;
        this.docket = docket;
        this.offence = offence;
        this.offenceCount = offenceCount;
        this.pdfUrl=pdfUrl;
    }




    public Offender() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }

    public String getOffence() {
        return offence;
    }

    public void setOffence(String offence) {
        this.offence = offence;
    }

    public String getOffenceCount() {
        return offenceCount;
    }

    public void setOffenceCount(String offenceCount) {
        this.offenceCount = offenceCount;
    }
}
