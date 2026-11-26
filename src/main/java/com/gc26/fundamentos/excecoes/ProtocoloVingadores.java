package com.gc26.fundamentos.excecoes;

public class ProtocoloVingadores {

    public static void main(String[] args) {
        System.out.println("Iniciando protocolo");

        try {
            int[] poderes = new int[3];
            System.out.println(poderes[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Falha contida: " + e.getMessage());
        }

        System.out.println("Vingadores convocados");
    }
}
