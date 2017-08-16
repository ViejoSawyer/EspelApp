package com.proyecto.albanbassante.espelapp;

/**
 * Created by gonzaloalban on 6/5/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab1,container,false);

        final Bundle args = getArguments();
        final String nombre = args.getString("nombre");
        final String director = args.getString("director");
        final String mail = args.getString("mail");
        final String telefono = args.getString("telefono");
        final String horario = args.getString("horario");
        final String direccion = args.getString("direccion");

        TextView lblDepDirector = (TextView) v.findViewById(R.id.tvDepDirector);
        lblDepDirector.setText(director);

        if(direccion.equals("")){
            CardView cvDireccion = (CardView) v.findViewById(R.id.cvDireccion);
            cvDireccion.setVisibility(View.GONE);
        }
        TextView lblDepDireccion = (TextView) v.findViewById(R.id.tvDepDireccion);
        lblDepDireccion.setText(direccion);

        TextView lblDepMail = (TextView) v.findViewById(R.id.tvDepMail);
        lblDepMail.setText(mail);

        TextView lblDepTelefono = (TextView) v.findViewById(R.id.tvDepTelefono);
        lblDepTelefono.setText(telefono);

        TextView lblDepHorario = (TextView) v.findViewById(R.id.tvDepHorario);
        lblDepHorario.setText(horario);

        return v;
    }
}
