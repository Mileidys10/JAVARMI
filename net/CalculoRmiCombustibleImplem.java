package net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import calculo.modelo.CalculoCosto;

public class CalculoRmiCombustibleImplem extends UnicastRemoteObject implements IRemotaCalculoCosto {

    private static final long serialVersionUID = 1L;

    public CalculoRmiCombustibleImplem() throws RemoteException {
        super();
    }

    @Override
    public CalculoCosto.Costo calcularCosto(CalculoCosto calculo) throws RemoteException {
        if (calculo == null) {
            return null;
        }

        System.out.println("Distancia: " + calculo.getDistancia());
        System.out.println("Rendimiento: " + calculo.getRendimiento());
        System.out.println("Precio Combustible: " + calculo.getPrecioCombustible());

        CalculoCosto.Costo costo = calculo.getCosto();

        System.out.println("Litros necesarios: " + costo.litrosNecesarios);
        System.out.println("Costo: " + costo.resultado);
        System.out.println("Mensaje: " + costo.mensaje);

        return costo;
    }
}
