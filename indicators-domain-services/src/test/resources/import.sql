-- ETABLISSEMENT
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0453456A', '45', 'CFA');
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0362903S', '36', 'CFA');
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0456782B', '45', 'LEGT');
INSERT INTO etablissement (uai, departement, typeetablissement) VALUES ('0376543R', '37', 'LA');

-- NOMBRE DE VISITEURS
INSERT INTO nombredevisiteurs (jour, typeetab, typestat, uai, nbvisites, nbvisiteurs) VALUES ('2012-05-28', 'CFA', 'Etablissement', '0453456A', '450', '60');

-- CONNEXIONS AU PORTAIL
INSERT INTO seconnectesemaine (premierjoursemaine, uai, uid, nomprofil, nbconnexionsemaine, moyennesemaine) VALUES ('2012-05-28', '0453456A', 'F1234567X', 'Teacher', '12', '26.33' );
INSERT INTO seconnectesemaine (premierjoursemaine, uai, uid, nomprofil, nbconnexionsemaine, moyennesemaine) VALUES ('2012-05-28', '0453456A', 'F8912345R', 'Teacher', '8', '40.37' );

-- CONNEXIONS AUX SERVICES
INSERT INTO connexionservicejour (jour, uai, nomservice, uid, nomprofil, nbconnexionservice) VALUES ('2012-05-28', '0453456A', 'Teacher service', 'F1234567X', 'Teacher', '25');
INSERT INTO connexionservicejour (jour, uai, nomservice, uid, nomprofil, nbconnexionservice) VALUES ('2012-05-28', '0453456A', 'Teacher service', 'F8912345R', 'Teacher', '32');
