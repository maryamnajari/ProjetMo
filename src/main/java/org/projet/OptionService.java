package org.projet;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projet.classes.Personne;
import org.projet.classes.User;
import org.projet.util.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionService {
    RoleService roleService = new RoleService();
    public void executeOption(int option, User connectedUser){
        switch (option){
            case 1:
                if(roleService.getRole(connectedUser).equals(RoleType.PERSONNE_MORALE) || roleService.getRole(connectedUser).equals(RoleType.PERSONNE_PHYSIQUE)){
                    Personne personne = (Personne) connectedUser;
                    System.out.println("affichage des etablissments");
                }
                break;
            case 2:
                if(roleService.getRole(connectedUser).equals(RoleType.PERSONNE_MORALE) || roleService.getRole(connectedUser).equals(RoleType.PERSONNE_PHYSIQUE)){
                    Personne personne = (Personne) connectedUser;
                    System.out.println("Calcule de facture");
                }
                break;
        }
    }
}
