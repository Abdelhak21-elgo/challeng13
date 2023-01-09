# Challenge 13

Ce projet comprend un formulaire qui permet à l'utilisateur de saisir son nom, son prénom, son nom en arabe, son prénom en arabe, son numéro d'identification national (NIN), sa profession et sa date de naissance. L'utilisateur doit également sélectionner le type de carte qu'il souhaite (A ou B) et télécharger une image.

Une fois que l'utilisateur a soumis le formulaire, le système génère une carte avec les informations saisies. La carte comprend également un QR code qui contient le nom, le prénom, le NIN et la date de naissance de l'utilisateur. Le fichier PDF de la carte est enregistré et peut être imprimé.

# Prérequis

Les éléments dont vous avez besoin pour installer le logiciel et la manière de les installer:

        Java: openjdk version "17.0.3" 2022-04-19
        Maven: Apache Maven 3.8.6
        Nodejs: v14.17.3
        npm: 6.14.13
        Angular: Angular CLI 14.2.10

# Installation

Voici comment configurer un environnement de développement pour ce projet:

    1.Clonez le dépôt
    2.Accédez au répertoire du projet

        Pour la partie backend:

            Build the project using Maven: "mvn clean install"
            Run the project: "mvn spring-boot:run"

        Pour la partie frontend:

            Installez les dépendances en utilisant npm:" npm install"
            Lancez le projet: "ng serve"

# Technologies utilisées

    Backend: Spring Boot
    Frontend: Angular

# Auteur
    Abdelhak El Gourmat