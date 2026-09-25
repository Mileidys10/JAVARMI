package calculo.modelo;

import java.io.Serializable;

public class CalculoCosto implements Serializable {
    private static final long serialVersionUID = 1L;

    private float distancia;
    private float rendimiento;
    private float precioCombustible;

    public static class Costo implements Serializable {
        private static final long serialVersionUID = 1L;
        public float litrosNecesarios;
        public float resultado;
        public String mensaje;

        public float getLitrosNecesarios() {
            return litrosNecesarios;
        }

        public void setLitrosNecesarios(float litrosNecesarios) {
            this.litrosNecesarios = litrosNecesarios;
        }

        public float getResultado() {
            return resultado;
        }

        public void setResultado(float resultado) {
            this.resultado = resultado;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    private Costo costo;

    public CalculoCosto() {
    }

    public CalculoCosto(float distancia, float rendimiento, float precioCombustible) {
        this.distancia = distancia;
        this.rendimiento = rendimiento;
        this.precioCombustible = precioCombustible;
    }

    public Costo getCosto() {
        costo = new Costo();
        if (distancia <= 0 || rendimiento <= 0 || precioCombustible <= 0) {
            costo.litrosNecesarios = 0;
            costo.resultado = 0;
            costo.mensaje = "Error: Todos los valores deben ser mayores a cero.";
        } else {
            float litrosNecesarios = distancia / rendimiento;
            costo.litrosNecesarios = litrosNecesarios;
            costo.resultado = litrosNecesarios * precioCombustible;
            costo.mensaje = "Costo calculado correctamente.";
        }
        return costo;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public float getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(float rendimiento) {
        this.rendimiento = rendimiento;
    }

    public float getPrecioCombustible() {
        return precioCombustible;
    }

    public void setPrecioCombustible(float precioCombustible) {
        this.precioCombustible = precioCombustible;
    }
}
