package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class PottasiumReactions extends Reactions {
    String[] products = {" ", " ", " ", " ", " ", " "};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].equals("K")) {
                products[0] = "KOH";
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
            if (ch[i].equals("K")) {
                products[0] = "KO2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].equals("K")) {

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("K")) && (conditions.equals("200C < t< 350C"))) {
                products[0] = "KH";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("K")) && (ch[i + 1].contains("dil. HCl"))) {
                products[0] = "KCl";
                products[1] = "H2";
            }
            if ((ch[i].equals("K")) && (ch[i + 1].contains("dil. H2SO4"))) {
                products[0] = "K2SO4";
                products[1] = "H2O";
                products[2] = "SO2";
                products[3] = "S";
            }
            if ((ch[i].equals("K")) && (ch[i + 1].contains("dil. HNO3"))) {
                products[0] = "KNO3";
                products[1] = "N2O";
                products[2] = "NO";
                products[3] = "N2";
                products[4] = "H2O";
            }
            if ((ch[i].equals("K2O")) && (ch[i + 1].contains("dil. HCl"))) {
                products[0] = "KCl";
                products[1] = "H2O";
            }
            if ((ch[i].equals("KOH")) && (ch[1].equals("H3PO4"))) {
                products[0] = "K3PO4";
                products[1] = "H2O";
            }

        }
        return products;
    }

    @Override
    public String[] productsWhenreactswithConcentratedAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].equals("K")) {

            }
        }
        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("K") && (ch[i].equals("F")))) {
                products[0] = "KF";
            }
            if ((ch[i].equals("K") && (ch[i].equals("Cl")))) {
                products[0] = "KCl";
            }
            if ((ch[i].equals("K") && (ch[i].equals("Br")))) {
                products[0] = "KBr";
            }
            if ((ch[i].equals("K") && (ch[i].equals("I")))) {
                products[0] = "KI";
            }
            if (ch[i].equals("K2O") && (ch[i].equals("CO2"))) {
                products[0] = "K2CO3";
            }
        }
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("K2CO3") && (condition.equals("t>`1200C")))) {
                products[0] = "K2O";
                products[1] = "CO2";
            }
            if ((ch[i].equals("KHCO3") && (condition.equals("100C < t <`400C")))) {
                products[0] = "K2CO3";
                products[1] = "K2O";
                products[2] = "CO2";
            }
            if ((ch[i].equals("KNO3"))) {
                products[0] = "KNO2";
                products[1] = "O2";
            }
        }

        return products;
    }
}
