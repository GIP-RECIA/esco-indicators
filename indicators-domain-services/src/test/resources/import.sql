-- ETABLISSEMENT
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0453456A', '45', 'CFA');
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0362903S', '36', 'CFA');
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0456782B', '45', 'LEGT');
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0376543R', '37', 'LA');


-- ACTIVATION DE COMPTE
INSERT INTO est_activee (uid, datedebutactivation, datefinactivation) VALUES ('F1234567X', '2011-03-19', '2012-06-04');
INSERT INTO est_activee (uid, datedebutactivation, datefinactivation) VALUES ('F8912345R', '2012-02-01', NULL);
INSERT INTO est_activee (uid, datedebutactivation, datefinactivation) VALUES ('F8654397U', '2012-01-19', '2012-02-20');

-- NOMBRE DE VISITEURS
INSERT INTO nombredevisiteurs (jour, typeetab, typestat, uai, nbvisites, nbvisiteurs) VALUES ('2012-05-28', 'CFA', 'TOTAL_UN_ETABLISSEMENT', '0453456A', '450', '60');

-- PROFILS UTILISATEURS
INSERT INTO acommeprofil (uai, uid, nomprofil, datedebutprofil, datefinprofil) VALUES ('0453456A', 'F1234567X', 'Teacher', '2011-03-18', '2012-07-13');
INSERT INTO acommeprofil (uai, uid, nomprofil, datedebutprofil, datefinprofil) VALUES ('0453456A', 'F8912345R', 'Teacher', '2011-09-17', NULL);
INSERT INTO acommeprofil (uai, uid, nomprofil, datedebutprofil, datefinprofil) VALUES ('0453456A', 'F8654397U', 'Teacher', '2012-02-01', NULL);


-- CONNEXIONS PROFILS AU PORTAIL (HEBDOMADAIRE)
INSERT INTO connexionprofilsemaine (semaine, uai, nomprofil, nbconnexion, nbpersonne, moyenneconnexion) VALUES ('2012-05-28', '0453456A', 'Teacher', '3', '25', '16.43');
INSERT INTO connexionprofilsemaine (semaine, uai, nomprofil, nbconnexion, nbpersonne, moyenneconnexion) VALUES ('2012-05-28', '0453456A', 'Teacher', '4', '13', '36.07');

-- CONNEXIONS PROFILS AU PORTAIL (MENSUEL)
INSERT INTO connexionprofilmois (mois, uai, nomprofil, nbconnexion, nbpersonne, moyenneconnexion) VALUES ('2012-05-01', '0453456A', 'Teacher', '7', '115', '46.21');
INSERT INTO connexionprofilmois (mois, uai, nomprofil, nbconnexion, nbpersonne, moyenneconnexion) VALUES ('2012-05-01', '0453456A', 'Teacher', '9', '13', '16.21');



-- CONNEXIONS UTILISATEURS AU PORTAIL (HEBDOMADAIRE)
INSERT INTO seconnectesemaine (premierjoursemaine, uai, uid, nomprofil, nbconnexionsemaine, moyennesemaine) VALUES ('2012-05-28', '0453456A', 'F1234567X', 'Teacher', '12', '26.33');
INSERT INTO seconnectesemaine (premierjoursemaine, uai, uid, nomprofil, nbconnexionsemaine, moyennesemaine) VALUES ('2012-05-28', '0453456A', 'F8912345R', 'Teacher', '8', '40.37');

-- CONNEXIONS UTILISATEURS AU PORTAIL (MENSUEL)
INSERT INTO seconnectemois (mois, uai, uid, nomprofil, nbconnexionmois, moyennemois) VALUES ('2012-05-01', '0453456A', 'F1234567X', 'Teacher', '128', '34.76');
INSERT INTO seconnectemois (mois, uai, uid, nomprofil, nbconnexionmois, moyennemois) VALUES ('2012-05-01', '0453456A', 'F8912345R', 'Teacher', '234', '49.32');



-- CONNEXIONS AUX SERVICES
INSERT INTO connexionservicejour (jour, uai, nomservice, uid, nomprofil, nbconnexionservice) VALUES ('2012-05-28', '0453456A', 'Teacher service', 'F1234567X', 'Teacher', '25');
INSERT INTO connexionservicejour (jour, uai, nomservice, uid, nomprofil, nbconnexionservice) VALUES ('2012-05-28', '0453456A', 'Teacher service', 'F8912345R', 'Teacher', '32');
