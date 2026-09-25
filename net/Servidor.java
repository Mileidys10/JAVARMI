package net;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    private int puerto = 1099;
    private String nombreServicio = "CalculoCostoCombustibleServicio";
    private Registry registro;
    private CalculoRmiCombustibleImplem calculo;

    public Servidor() {
    }

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() throws Exception {
        try {
            calculo = new CalculoRmiCombustibleImplem();
            try {
                registro = LocateRegistry.createRegistry(puerto);
            } catch (RemoteException re) {
                registro = LocateRegistry.getRegistry(puerto);
            }
            registro.rebind(nombreServicio, calculo);
            System.out.println("Servidor RMI Estandar iniciado en puerto: " + puerto);
            System.out.println("Servicio: " + nombreServicio);
        } catch (RemoteException ex) {
            throw new Exception("Error al iniciar servicio RMI: " + ex.getMessage(), ex);
        }
    }

    public void detener() {
        try {
            if (registro != null) {
                registro.unbind(nombreServicio);
                System.out.println("Servicio detenido.");
            }
        } catch (Exception ex) {
            System.err.println("Error al detener servidor: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Principal.main(args);
    }
}
