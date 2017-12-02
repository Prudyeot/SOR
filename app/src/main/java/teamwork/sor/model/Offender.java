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


    public Offender(String image, String sex, String name, String residence, String docket, String offence, String offenceCount) {
        this.image = image;
        this.sex = sex;
        this.name = name;
        this.residence = residence;
        this.docket = docket;
        this.offence = offence;
        this.offenceCount = offenceCount;
    }




    public Offender() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
