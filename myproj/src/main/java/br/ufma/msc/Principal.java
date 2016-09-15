/**
 * 
 */
package br.ufma.msc;

import java.util.Scanner;

import br.ufma.msc.gui.Tela;

/**
 * @author jwalker
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Receberá as instruções, tela de console com o Menu de opções
		  Tela tela = new Tela();
		    tela.menu();

		    Scanner scanner = new Scanner(System.in);
		    int choice = scanner.nextInt();

		    switch (choice) {
		        case 1:

		            break;
		        case 2:

		            break;
		        case 3:

		            break;
		        case 4:

		            break;
		        default:
		            // The user input an unexpected choice.
		    }
	}

}
