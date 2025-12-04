/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tableaurecherche;

/**
 *
 * @author lenovo
 */
import java.util.Scanner;
import java.util.Arrays;

public class TableauRecherche {
    
    // Méthode pour le tri à bulles
    public static void triBulle(int[] tableau) {
        int n = tableau.length;
        boolean echange;
        
        for (int i = 0; i < n - 1; i++) {
            echange = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (tableau[j] > tableau[j + 1]) {
                    // Échange des éléments
                    int temp = tableau[j];
                    tableau[j] = tableau[j + 1];
                    tableau[j + 1] = temp;
                    echange = true;
                }
            }
            // Si aucun échange n'a été fait, le tableau est déjà trié
            if (!echange) break;
        }
    }
    
    // Méthode pour trouver la valeur la plus proche de k
    public static int trouverPlusProche(int[] tableau, int k) {
        if (tableau.length == 0) return -1;
        
        int plusProche = tableau[0];
        int differenceMin = Math.abs(tableau[0] - k);
        
        for (int i = 1; i < tableau.length; i++) {
            int difference = Math.abs(tableau[i] - k);
            if (difference < differenceMin) {
                differenceMin = difference;
                plusProche = tableau[i];
            }
        }
        
        return plusProche;
    }
    
    // Méthode pour insérer une valeur dans un tableau trié
    public static int[] insererDansTableauTrie(int[] tableau, int v) {
        int[] nouveauTableau = new int[tableau.length + 1];
        int i = 0;
        
        // Trouver la position d'insertion
        while (i < tableau.length && tableau[i] < v) {
            i++;
        }
        
        // Copier les éléments avant la position d'insertion
        for (int j = 0; j < i; j++) {
            nouveauTableau[j] = tableau[j];
        }
        
        // Insérer la nouvelle valeur
        nouveauTableau[i] = v;
        
        // Copier les éléments après la position d'insertion
        for (int j = i; j < tableau.length; j++) {
            nouveauTableau[j + 1] = tableau[j];
        }
        
        return nouveauTableau;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Saisie de la taille du tableau
        System.out.print("Entrez la taille du tableau (n) : ");
        int n = scanner.nextInt();
        
        // Création du tableau
        int[] tableau = new int[n];
        
        // Saisie des éléments du tableau
        System.out.println("Entrez les " + n + " elements du tableau :");
        for (int i = 0; i < n; i++) {
            System.out.print("Élement " + (i + 1) + " : ");
            tableau[i] = scanner.nextInt();
        }
        
        // Affichage du tableau initial
        System.out.println("\nTableau initial : " + Arrays.toString(tableau));
        
        // Tri du tableau avec tri à bulles
        triBulle(tableau);
        System.out.println("Tableau trie (tri a bulles) : " + Arrays.toString(tableau));
        
        // Affichage des extrémités
        System.out.println("Extremites : minimum = " + tableau[0] + ", maximum = " + tableau[tableau.length - 1]);
        
        // Saisie de k
        System.out.print("\nEntrez la valeur k a rechercher : ");
        int k = scanner.nextInt();
        
        // Recherche de la valeur la plus proche de k
        int plusProche = trouverPlusProche(tableau, k);
        System.out.println("La valeur la plus proche de " + k + " est : " + plusProche);
        
        // Saisie de la valeur v à ajouter
        System.out.print("\nEntrez la valeur v à ajouter : ");
        int v = scanner.nextInt();
        
        // Insertion de v dans le tableau trié
        tableau = insererDansTableauTrie(tableau, v);
        System.out.println("Tableau après insertion de " + v + " : " + Arrays.toString(tableau));
        
        scanner.close();
    }
}
