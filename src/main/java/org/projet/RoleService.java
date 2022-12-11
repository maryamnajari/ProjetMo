package org.projet;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.projet.classes.*;
import org.projet.util.RoleType;

import java.util.List;

@Data
@NoArgsConstructor
public class RoleService {
    User personnePhysique = new PersonnePhysique("NAJARI", "Maryam", "célibataire", "Angular Dev", "najari", "maryam123");
    User personneMorale = new PersonneMorale("AGILIS", "SAS", "D152874521", "agilis", "mdpAgilis123");
    User agent1 = new AgentAdministratif("agent", "agentMdp", "Yassine", "Yassine");
    User agent2 = new Surveillant("Laila", "Maryam", "laila", "maryam");


    List<User> utilisateurs = List.of(personneMorale, personnePhysique, agent1, agent2);

    public User retreiveRole(String login, String pwd) {
        if (!login.isEmpty() || !pwd.isEmpty()) {
            return utilisateurs.stream()
                    .filter(utilisateur -> utilisateur.getLogin().equals(login) && utilisateur.getMdp().equals(pwd))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public RoleType getRole(User user) {
        if (user instanceof PersonnePhysique) {
            return RoleType.PERSONNE_PHYSIQUE;
        } else if (user instanceof PersonneMorale) {
            return RoleType.PERSONNE_MORALE;
        } else if (user instanceof AgentAdministratif) {
            return RoleType.AGENT_ADMINISTRATIF;
        } else if (user instanceof Surveillant) {
            return RoleType.SURVEILLANT;
        } else {
            return null;
        }
    }
}
