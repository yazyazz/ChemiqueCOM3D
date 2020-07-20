package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class BerryliumReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Be")) && (conditions.equals("Boiling water"))) {
                products[0] = "BeOH";
                products[1] = "H2";

            }
            if (ch[i].equals("K2O")) {
                products[0] = "KOH";


            }

        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Be")) && (conditions.equals("t = 900C"))) {
                products[0] = "BeO";
                products[1] = "H2";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Be")) && (ch[i].contains("dil. HCl"))) {
                products[0] = "BeCl2";
                products[1] = "H2";
            }
            if ((ch[i].equals("Be")) && (ch[i].contains("dil. hot HNO3"))) {
                products[0] = "Be(NO3)2";
                products[1] = "NO";
                products[2] = "H2O";
            }
        }
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
            if ((ch[i].equals("BeCO3") && (condition.equals("t>`180C")))) {
                products[0] = "BeO";
                products[1] = "CO2";
            }
        }
        return products;
    }
}
