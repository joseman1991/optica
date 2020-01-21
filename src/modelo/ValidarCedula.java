/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class ValidarCedula {

    public boolean validadCedula(String cedula) {
        if (cedula.length() == 10) {
            int suma = 0;
            char[] charcedula = cedula.toCharArray();
            int verificador = Integer.parseInt(charcedula[9] + "");
            for (int i = 0; i < charcedula.length - 1; i++) {
                char c = charcedula[i];
                int n = Integer.parseInt(c + "");
                if (i % 2 == 0) {
                    n *= 2;
                    if (n > 9) {
                        n -= 9;
                    }
                }
                suma += n;
            }
            suma = suma % 10;
            if (suma != 0) {
                suma = 10 - suma;
            }
            return verificador == suma;
        }
        return false;
    }

  

}
