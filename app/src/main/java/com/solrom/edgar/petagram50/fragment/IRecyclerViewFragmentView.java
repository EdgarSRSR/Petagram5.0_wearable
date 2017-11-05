package com.solrom.edgar.petagram50.fragment;

import com.solrom.edgar.petagram50.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram50.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 26/10/17.
 */

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();
    public void generarGridLayout();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
