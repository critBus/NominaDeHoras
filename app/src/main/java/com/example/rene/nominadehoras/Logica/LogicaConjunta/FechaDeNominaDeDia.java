package com.example.rene.nominadehoras.Logica.LogicaConjunta;

/**
 * Created by Rene on 28/12/2021.
 */
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.TipoDeDia;

import java.util.Date;

public class FechaDeNominaDeDia {
    private TipoDeDia tipo;
    private Date date;

    public FechaDeNominaDeDia(TipoDeDia tipo) {
        this.tipo = tipo;
    }

    public FechaDeNominaDeDia(TipoDeDia tipo, Date date) {
        this.tipo = tipo;
        this.date = date;
    }

    public TipoDeDia getTipo() {
        return tipo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
