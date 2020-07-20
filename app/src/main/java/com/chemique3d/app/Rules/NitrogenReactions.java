package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class NitrogenReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {

        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].contains("N2"))) {//&& (conditions.contains("400C < t < 500C, Fe catalyst, high pressure"))) {
                products[0] = "NH3";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactswithConcentratedAcids(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("N2O5"))) {
                products[0] = "N2O4";
                products[1] = "O2";

            }
            if ((ch[i].equals("NH4NO3"))) {
                products[0] = "N2O";
                products[1] = "H2O";

            }
            if (ch[i].equals("NH4NO2")) {
                products[0] = "N2";
                products[1] = "H2O";
            }

        }
        return products;
    }
}
