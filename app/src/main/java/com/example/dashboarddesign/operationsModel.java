package com.example.dashboarddesign;

public class operationsModel {
    String typeop;
    String montant;
    String dateop;

    public operationsModel(String typeop, String montant, String dateop) {
        this.typeop = typeop;
        this.montant = montant;
        this.dateop = dateop;
    }

    public String getTypeop() {
        return typeop;
    }

    public void setTypeop(String typeop) {
        this.typeop = typeop;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDateop() {
        return dateop;
    }

    public void setDateop(String dateop) {
        this.dateop = dateop;
    }
}
