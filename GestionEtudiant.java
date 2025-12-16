/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionetudiants;

/**
 *
 * @author lenovo
 */
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Etudiant {
    private String nom;
    private String prenom;
    private LocalDate dateN;
    private String lieuN;
    private String adresse;
    private String tel;
    private char genre;
    private String email;
    private double note;

    public Etudiant(String nom, String prenom, LocalDate dateN, String lieuN, 
                   String adresse, String tel, char genre, String email, double note) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateN = dateN;
        this.lieuN = lieuN;
        this.adresse = adresse;
        this.tel = tel;
        this.genre = genre;
        this.email = email;
        this.note = note;
    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateN() { return dateN; }
    public int getAge() { 
        return Period.between(dateN, LocalDate.now()).getYears(); 
    }
    public String getLieuN() { return lieuN; }
    public String getAdresse() { return adresse; }
    public String getTel() { return tel; }
    public char getGenre() { return genre; }
    public String getEmail() { return email; }
    public double getNote() { return note; }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-12s %-3d ans %-15s %-15s %-10s %c %-20s %.2f",
                nom, prenom, dateN.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getAge(), lieuN, adresse, tel, genre, email, note);
    }
}

public class GestionEtudiants {
    private static List<Etudiant> etudiants = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        initialiserEtudiants();
        afficherMenu();
    }

    private static void initialiserEtudiants() {
        // Création de 50 étudiants avec des données aléatoires
        String[] noms = {"Diop", "Sall", "Diouf", "Fall", "Ndiaye", "Samb", "Lô", "Mendy", "Camara", "Sow"};
        String[] prenoms = {"Amadou", "Serigne", "Mamadou", "Astou", "Aziz", "Coumba", "Malick", "Khoudia", "Yoro", "Khady"};
        String[] villes = {"Dioubel", "Dakar", "Mbacké", "Tamba", "Nioro", "Touba", "Saint Louis", "Mbacké", "Bambey", "Gossas"};
        
        Random random = new Random();
        
        for (int i = 0; i < 50; i++) {
            String nom = noms[random.nextInt(noms.length)];
            String prenom = prenoms[random.nextInt(prenoms.length)];
            
            // Date de naissance aléatoire entre 18 et 30 ans
            int annee = 1994 + random.nextInt(13); // 1994 à 2006
            int mois = random.nextInt(12) + 1;
            int jour = random.nextInt(28) + 1;
            LocalDate dateN = LocalDate.of(annee, mois, jour);
            
            String lieuN = villes[random.nextInt(villes.length)];
            String adresse = "Rue " + (random.nextInt(100) + 1) + ", " + villes[random.nextInt(villes.length)];
            String tel = "0" + (600000000 + random.nextInt(9999999));
            char genre = random.nextBoolean() ? 'M' : 'F';
            String email = prenom.toLowerCase() + "." + nom.toLowerCase() + (i+1) + "@email.com";
            double note = 5 + random.nextDouble() * 15; // Note entre 5 et 20
            
            etudiants.add(new Etudiant(nom, prenom, dateN, lieuN, adresse, tel, genre, email, note));
        }
        System.out.println("50 étudiants ont été créés avec succès!");
    }

    private static void afficherMenu() {
        while (true) {
            System.out.println("\n========== MENU GESTION ÉTUDIANTS ==========");
            System.out.println("1. Afficher tous les étudiants");
            System.out.println("2. Trier par âge (croissant)");
            System.out.println("3. Afficher les 5 étudiants les moins âgés");
            System.out.println("4. Afficher l'étudiant avec la meilleure note");
            System.out.println("5. Afficher l'étudiant avec la plus mauvaise note");
            System.out.println("6. Supprimer l'étudiant le moins âgé");
            System.out.println("7. Ajouter un nouvel étudiant");
            System.out.println("8. Quitter");
            System.out.print("Votre choix : ");
            
            int choix;
            try {
                choix = scanner.nextInt();
                scanner.nextLine(); // Vider le buffer
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide!");
                scanner.nextLine();
                continue;
            }
            
            switch (choix) {
                case 1:
                    afficherTousEtudiants();
                    break;
                case 2:
                    trierParAge();
                    break;
                case 3:
                    afficher5MoinsAges();
                    break;
                case 4:
                    afficherMeilleureNote();
                    break;
                case 5:
                    afficherMauvaiseNote();
                    break;
                case 6:
                    supprimerMoinsAge();
                    break;
                case 7:
                    ajouterEtudiant();
                    break;
                case 8:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide!");
            }
        }
    }

    private static void afficherTousEtudiants() {
        System.out.println("\n=== LISTE DES ÉTUDIANTS ===");
        System.out.printf("%-10s %-10s %-12s %-5s %-15s %-15s %-10s %-5s %-20s %-5s\n",
                "Nom", "Prénom", "Date N.", "Âge", "Lieu N.", "Adresse", "Téléphone", "G", "Email", "Note");
        System.out.println("=".repeat(120));
        
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }
    }

    private static void trierParAge() {
        etudiants.sort(Comparator.comparingInt(Etudiant::getAge));
        System.out.println("\nListe triée par âge (croissant) :");
        afficherTousEtudiants();
    }

    private static void afficher5MoinsAges() {
        // Créer une copie pour ne pas modifier l'ordre original
        List<Etudiant> copie = new ArrayList<>(etudiants);
        copie.sort(Comparator.comparingInt(Etudiant::getAge));
        
        System.out.println("\n=== LES 5 ÉTUDIANTS LES MOINS ÂGÉS ===");
        System.out.printf("%-10s %-10s %-12s %-5s %-15s %-20s %-5s\n",
                "Nom", "Prénom", "Date N.", "Âge", "Lieu N.", "Email", "Note");
        System.out.println("=".repeat(80));
        
        for (int i = 0; i < Math.min(5, copie.size()); i++) {
            Etudiant e = copie.get(i);
            System.out.printf("%-10s %-10s %-12s %-5d %-15s %-20s %.2f\n",
                    e.getNom(), e.getPrenom(), 
                    e.getDateN().format(formatter),
                    e.getAge(), e.getLieuN(), e.getEmail(), e.getNote());
        }
    }

    private static void afficherMeilleureNote() {
        Etudiant meilleur = Collections.max(etudiants, Comparator.comparingDouble(Etudiant::getNote));
        System.out.println("\n=== ÉTUDIANT AVEC LA MEILLEURE NOTE ===");
        afficherEtudiantDetail(meilleur);
    }

    private static void afficherMauvaiseNote() {
        Etudiant pire = Collections.min(etudiants, Comparator.comparingDouble(Etudiant::getNote));
        System.out.println("\n=== ÉTUDIANT AVEC LA PLUS MAUVAISE NOTE ===");
        afficherEtudiantDetail(pire);
    }

    private static void afficherEtudiantDetail(Etudiant etudiant) {
        System.out.printf("%-10s %-10s %-12s %-5s %-15s %-15s %-10s %-5s %-20s %-5s\n",
                "Nom", "Prénom", "Date N.", "Âge", "Lieu N.", "Adresse", "Téléphone", "G", "Email", "Note");
        System.out.println("=".repeat(120));
        System.out.println(etudiant);
    }

    private static void supprimerMoinsAge() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant à supprimer!");
            return;
        }
        
        Etudiant plusJeune = Collections.min(etudiants, Comparator.comparingInt(Etudiant::getAge));
        System.out.println("\nÉtudiant à supprimer (le moins âgé) :");
        afficherEtudiantDetail(plusJeune);
        
        System.out.print("Confirmer la suppression (O/N) ? ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("O")) {
            etudiants.remove(plusJeune);
            System.out.println("Étudiant supprimé avec succès!");
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    private static void ajouterEtudiant() {
        System.out.println("\n=== AJOUT D'UN NOUVEL ÉTUDIANT ===");
        
        try {
            System.out.print("Nom : ");
            String nom = scanner.nextLine();
            
            System.out.print("Prénom : ");
            String prenom = scanner.nextLine();
            
            System.out.print("Date de naissance (jj/mm/aaaa) : ");
            String dateStr = scanner.nextLine();
            LocalDate dateN = LocalDate.parse(dateStr, formatter);
            
            System.out.print("Lieu de naissance : ");
            String lieuN = scanner.nextLine();
            
            System.out.print("Adresse : ");
            String adresse = scanner.nextLine();
            
            System.out.print("Téléphone : ");
            String tel = scanner.nextLine();
            
            System.out.print("Genre (M/F) : ");
            char genre = scanner.nextLine().charAt(0);
            
            System.out.print("Email : ");
            String email = scanner.nextLine();
            
            System.out.print("Note : ");
            double note = scanner.nextDouble();
            scanner.nextLine(); // Vider le buffer
            
            Etudiant nouvelEtudiant = new Etudiant(nom, prenom, dateN, lieuN, adresse, tel, genre, email, note);
            etudiants.add(nouvelEtudiant);
            
            System.out.println("Étudiant ajouté avec succès!");
            
        } catch (Exception e) {
            System.out.println("Erreur dans la saisie : " + e.getMessage());
            scanner.nextLine(); // Vider le buffer en cas d'erreur
        }
    }
              }
