package org.projet.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etablissement {
    private String code;
    private String appelationCommerciale;
    private String addresse;
    private Periode periode;
    private Zone zone;
    private List<Terasse> terasses;
    private Personne gerant;

    public void ajouterGerant(Personne personne){
        this.gerant = personne;
        Periode newPeriode = new Periode(new Date(System.currentTimeMillis()),null,personne,new Facture(), this);
        List<Periode> periodesOccupation = personne.getPeriodesDoccupations();
        periodesOccupation.add(newPeriode);
        personne.setPeriodesDoccupations(periodesOccupation);
    }

    public void quitterGerant(Personne personne){
         personne.getPeriodesDoccupations().stream()
                .filter(p-> p.getEtablissement().equals(this))
                .forEach(periode -> periode.setDateFin(new Date(System.currentTimeMillis())));
    }
}
