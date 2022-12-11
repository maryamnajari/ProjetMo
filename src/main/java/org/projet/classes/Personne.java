package org.projet.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Personne extends User{
    private List<Periode> periodesDoccupations;

    public Personne(String login,
                    String mdp){
        super(login, mdp);
    }

    public List<Etablissement> getAllEtablissement(){
        return periodesDoccupations.stream()
                .map(Periode::getEtablissement)
                .collect(Collectors.toList());
    }

    public List<Facture> getAllFactures(){
        List<Facture> allInvoices  = new ArrayList<>();
        for (Periode p: this.periodesDoccupations){
            p.calculFacture();
            allInvoices.add(p.getFactureRedevable());
        }
        return allInvoices;
    }

    public List<Facture> getEtablissementInvoice(String code){
        List<Facture> etablissementInvoices  = new ArrayList<>();
        for (Periode p: this.periodesDoccupations){
            if (p.getEtablissement().getCode().equals(code)){
                p.calculFacture();
                etablissementInvoices.add(p.getFactureRedevable());
            }
        }
        return etablissementInvoices;
    }

}
