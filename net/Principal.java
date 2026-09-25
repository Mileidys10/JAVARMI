package net;

public class Principal {
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        try {
            servidor.iniciar();
        } catch (Exception ex) {
            System.err.println("Error al iniciar servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
