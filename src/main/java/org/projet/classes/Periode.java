package org.projet.classes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projet.util.TypeTerasseEnum;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Periode {
    private Date dateDebut;
    private Date dateFin;
    private Personne exploitant;
    private Facture factureRedevable;
    private  Etablissement etablissement;

    public void calculFacture() {
        List<Terasse> terasses = etablissement.getTerasses();
        Facture facture = new Facture();
        for (Terasse t:terasses){
            switch (t.getTypeTerasse().getType()){
                case TERASSE_ETE:
                    Tarif tarifs = t.getTypeTerasse().getTarifs().stream()
                            .filter(tarif -> tarif.getTypeTerasse().equals(TypeTerasseEnum.TERASSE_ETE))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException("Tarif non trouvée"));
                    float prixParMetre = tarifs.getPrixParMetre();
                    float nominateur = (System.currentTimeMillis() - t.getDateInstallation().getTime())* prixParMetre;
                    float denominateur = tarifs.getTypeTerasse().getDateFin().getTime()-tarifs.getTypeTerasse().getDateDebut().getTime();
                    facture.setSommeRedevable(facture.getSommeRedevable()+(nominateur/denominateur));
                    break;
                case TERASSE_PERMANENTE:
                    Tarif tarifPermanent = t.getTypeTerasse().getTarifs().stream()
                            .filter(tarifP -> tarifP.getTypeTerasse().equals(TypeTerasseEnum.TERASSE_ETE))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException("Tarif non trouvée"));

                    float prixParMetreP = tarifPermanent.getPrixParMetre();
                    float nominateurP = (System.currentTimeMillis() - t.getDateInstallation().getTime())* prixParMetreP;
                    float denominateurP = tarifPermanent.getTypeTerasse().getDateFin().getTime()-tarifPermanent.getTypeTerasse().getDateDebut().getTime();
                    facture.setSommeRedevable(facture.getSommeRedevable()+(nominateurP/denominateurP));
                    break;
                case TERASSE_SEMI_PERMANENTE:
                    Tarif tarifsP = t.getTypeTerasse().getTarifs().stream()
                            .filter(tarifP -> tarifP.getTypeTerasse().equals(TypeTerasseEnum.TERASSE_ETE))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException("Tarif non trouvée"));

                    float prixParMetreSP = tarifsP.getPrixParMetre();
                    float nominateurSP = (System.currentTimeMillis() - t.getDateInstallation().getTime())* prixParMetreSP;
                    float denominateurSP = tarifsP.getTypeTerasse().getDateFin().getTime()-tarifsP.getTypeTerasse().getDateDebut().getTime();
                    facture.setSommeRedevable(facture.getSommeRedevable()+(nominateurSP/denominateurSP));
            }
        }
    }

}
