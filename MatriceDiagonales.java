/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.matricediagonales;

/**
 *
 * @author lenovo
 */
import java.util.Arrays;
import java.util.Scanner;

public class MatriceDiagonales {
    private final int[][] matrice;
    private final int[] diagonalePrincipale;
    private final int[] diagonaleSecondaire;

    public MatriceDiagonales(int taille) {
        matrice = new int[taille][taille];
        diagonalePrincipale = new int[taille];
        diagonaleSecondaire = new int[taille];
        
        initialiserMatrice();
        extraireDiagonales();
    }

    private void initialiserMatrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Initialisation de la matrice " + matrice.length + "x" + matrice.length);
        
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                System.out.print("Élément [" + i + "][" + j + "]: ");
                matrice[i][j] = scanner.nextInt();
            }
        }
        scanner.nextLine(); // Nettoyer le buffer
    }

    private void extraireDiagonales() {
        int n = matrice.length;
        for (int i = 0; i < n; i++) {
            diagonalePrincipale[i] = matrice[i][i];
            diagonaleSecondaire[i] = matrice[i][n - 1 - i];
        }
    }

    public void afficherMatrice() {
        System.out.println("\nMatrice complète :");
        for (int[] ligne : matrice) {
            System.out.println(Arrays.toString(ligne));
        }
    }

    public int[] getDiagonalePrincipale() {
        return Arrays.copyOf(diagonalePrincipale, diagonalePrincipale.length);
    }

    public int[] getDiagonaleSecondaire() {
        return Arrays.copyOf(diagonaleSecondaire, diagonaleSecondaire.length);
    }

    public void afficherDiagonales() {
        System.out.println("\nDiagonale principale : " + Arrays.toString(diagonalePrincipale));
        System.out.println("Diagonale secondaire : " + Arrays.toString(diagonaleSecondaire));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Entrez la taille de la matrice carrée : ");
        int taille = scanner.nextInt();
        
        MatriceDiagonales md = new MatriceDiagonales(taille);
        
        md.afficherMatrice();
        md.afficherDiagonales();
        
        // Démonstration que les diagonales sont inchangeable
        int[] copieDiagPrincipale = md.getDiagonalePrincipale();
        copieDiagPrincipale[0] = 999; // Modification de la copie
        System.out.println("\nAprès modification d'une copie :");
        System.out.println("Diagonale originale : " + Arrays.toString(md.getDiagonalePrincipale()));
        System.out.println("Copie modifiée : " + Arrays.toString(copieDiagPrincipale));
        
        scanner.close();
    }
}
