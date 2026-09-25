package net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import calculo.modelo.CalculoCosto;

public interface IRemotaCalculoCosto extends Remote {
    public CalculoCosto.Costo calcularCosto(CalculoCosto calculo) throws RemoteException;
}
