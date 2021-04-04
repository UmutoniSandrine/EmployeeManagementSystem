/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.ApplicantDao;
import Domain.Applicant;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bitwayiki
 */
@ManagedBean
@SessionScoped
public class ApplicantModel{

    private Applicant applicant = new  Applicant();
    private List<Applicant> applicants;
    private boolean updateapplicant = false;

    public ApplicantModel() {
        applicants = ApplicantDao.findAll();
    }
    
    

    public boolean isUpdateapplicant() {
        return updateapplicant;
    }

    public void setUpdateapplicant(boolean updateapplicant) {
        this.updateapplicant = updateapplicant;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public String apply() {
        try {

            ApplicantDao.createApplicant(applicant);

            FacesContext.getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(applicant.getNationalId() + "", applicant.getNationalId() + ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage("", ""));
        }

        applicant = new Applicant();
        applicants = ApplicantDao.findAll();
        return "Applicants.xhtml?faces-redirect=true";
    }
    
    
     public String updateApplicants(Applicant ap) {
        applicant = ap;
        updateapplicant = true;
        return "ApplicantsList.xhtml?faces-redirect=true";
    }

    public String applicantCancel() {
        updateapplicant = false;
        applicant= new Applicant();
        return "Applicants.xhtml?faces-redirect=true";
    }

    public String deleteApplicant(Applicant a) {
        ApplicantDao.deleteApplicant(a);
        applicants = ApplicantDao.findAll();
        return "ApplicantsList.xhtml?faces-redirect=true";
    }

}
