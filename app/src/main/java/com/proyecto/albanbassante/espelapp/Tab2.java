package com.proyecto.albanbassante.espelapp;

/**
 * Created by gonzaloalban on 6/5/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Tab2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        final Bundle args = getArguments();
        final String id = args.getString("id");

        View v = inflater.inflate(R.layout.tab2_ciencias_exactas, container, false);

        if (id.equals("1")) {
            v = inflater.inflate(R.layout.tab2_ciencias_exactas, container, false);
            LinearLayout lyCard1 = (LinearLayout) v.findViewById(R.id.lyCard1);
            LinearLayout lyCard2 = (LinearLayout) v.findViewById(R.id.lyCard2);
            LinearLayout lyCard3 = (LinearLayout) v.findViewById(R.id.lyCard3);

            lyCard1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labCiencias1");
                    startActivity(myIntent);
                }
            });

            lyCard2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labCiencias2");
                    startActivity(myIntent);
                }
            });

            lyCard3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labCiencias3");
                    startActivity(myIntent);
                }
            });
        }
        
        if (id.equals("2")) {
            v = inflater.inflate(R.layout.tab2_energia_mecanica, container, false);
            LinearLayout lyCard1 = (LinearLayout) v.findViewById(R.id.lyCard1);
            LinearLayout lyCard2 = (LinearLayout) v.findViewById(R.id.lyCard2);
            LinearLayout lyCard3 = (LinearLayout) v.findViewById(R.id.lyCard3);
            LinearLayout lyCard4 = (LinearLayout) v.findViewById(R.id.lyCard4);
            LinearLayout lyCard5 = (LinearLayout) v.findViewById(R.id.lyCard5);
            LinearLayout lyCard6 = (LinearLayout) v.findViewById(R.id.lyCard6);
            LinearLayout lyCard7 = (LinearLayout) v.findViewById(R.id.lyCard7);
            LinearLayout lyCard8 = (LinearLayout) v.findViewById(R.id.lyCard8);
            LinearLayout lyCard9 = (LinearLayout) v.findViewById(R.id.lyCard9);
            LinearLayout lyCard10 = (LinearLayout) v.findViewById(R.id.lyCard10);

            lyCard1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia1");
                    startActivity(myIntent);
                }
            });

            lyCard2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia2");
                    startActivity(myIntent);
                }
            });

            lyCard3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia3");
                    startActivity(myIntent);
                }
            });

            lyCard4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia4");
                    startActivity(myIntent);
                }
            });

            lyCard5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia5");
                    startActivity(myIntent);
                }
            });

            lyCard6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia6");
                    startActivity(myIntent);
                }
            });

            lyCard7.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia7");
                    startActivity(myIntent);
                }
            });

            lyCard8.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia8");
                    startActivity(myIntent);
                }
            });

            lyCard9.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia9");
                    startActivity(myIntent);
                }
            });

            lyCard10.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labEnergia10");
                    startActivity(myIntent);
                }
            });
        }

        if (id.equals("3")) {
            v = inflater.inflate(R.layout.tab2_electrica_electronica, container, false);
            LinearLayout lyCard1 = (LinearLayout) v.findViewById(R.id.lyCard1);
            LinearLayout lyCard2 = (LinearLayout) v.findViewById(R.id.lyCard2);
            LinearLayout lyCard3 = (LinearLayout) v.findViewById(R.id.lyCard3);
            LinearLayout lyCard4 = (LinearLayout) v.findViewById(R.id.lyCard4);
            LinearLayout lyCard5 = (LinearLayout) v.findViewById(R.id.lyCard5);
            LinearLayout lyCard6 = (LinearLayout) v.findViewById(R.id.lyCard6);
            LinearLayout lyCard7 = (LinearLayout) v.findViewById(R.id.lyCard7);
            LinearLayout lyCard8 = (LinearLayout) v.findViewById(R.id.lyCard8);
            LinearLayout lyCard9 = (LinearLayout) v.findViewById(R.id.lyCard9);
            LinearLayout lyCard10 = (LinearLayout) v.findViewById(R.id.lyCard10);
            LinearLayout lyCard11 = (LinearLayout) v.findViewById(R.id.lyCard11);
            LinearLayout lyCard12 = (LinearLayout) v.findViewById(R.id.lyCard12);
            LinearLayout lyCard13 = (LinearLayout) v.findViewById(R.id.lyCard13);
            LinearLayout lyCard14 = (LinearLayout) v.findViewById(R.id.lyCard14);


            lyCard1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica1");
                    startActivity(myIntent);
                }
            });

            lyCard2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica2");
                    startActivity(myIntent);
                }
            });

            lyCard3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica3");
                    startActivity(myIntent);
                }
            });

            lyCard4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica4");
                    startActivity(myIntent);
                }
            });

            lyCard5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica5");
                    startActivity(myIntent);
                }
            });

            lyCard6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica6");
                    startActivity(myIntent);
                }
            });

            lyCard7.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica7");
                    startActivity(myIntent);
                }
            });

            lyCard8.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica8");
                    startActivity(myIntent);
                }
            });

            lyCard9.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica9");
                    startActivity(myIntent);
                }
            });

            lyCard10.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica10");
                    startActivity(myIntent);
                }
            });

            lyCard11.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica11");
                    startActivity(myIntent);
                }
            });

            lyCard12.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica12");
                    startActivity(myIntent);
                }
            });

            lyCard13.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica13");
                    startActivity(myIntent);
                }
            });

            lyCard14.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labElectrica14");
                    startActivity(myIntent);
                }
            });
        }


        if (id.equals("4")) {
            v = inflater.inflate(R.layout.tab2_lenguas, container, false);
            LinearLayout lyCard1 = (LinearLayout) v.findViewById(R.id.lyCard1);
            LinearLayout lyCard2 = (LinearLayout) v.findViewById(R.id.lyCard2);

            lyCard1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labLenguas1");
                    startActivity(myIntent);
                }
            });

            lyCard2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), LaboratorioInfoActivity.class);
                    myIntent.putExtra("laboratorio", "labLenguas2");
                    startActivity(myIntent);
                }
            });

        }

        if (id.equals("5")) {
            v = inflater.inflate(R.layout.tab2_entrada, container, false);
        }



        return v;
    }
}
