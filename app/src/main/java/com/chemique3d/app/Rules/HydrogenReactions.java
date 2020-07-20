package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class HydrogenReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String condition) {

        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("O2"))) {
                products[0] = "H2O";

            }
        }

        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].contains("H2"))) {//&& (conditions.contains("400C < t < 500C, Fe catalyst, high pressure"))) {
                products[0] = "NH3";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String condition) {
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String condition) {
        return products;
    }

    @Override
    public String[] productsWhenreactswithConcentratedAcids(String[] ch, String condition) {
        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Br2"))) {
                products[0] = "HBr";

            }
        }
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("H2O2"))) {
                products[0] = "H2O";
                products[1] = "O2";

            }
        }
        return products;
    }


}
