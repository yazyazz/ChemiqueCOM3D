package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class CalciumReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].equals("Ca")) {
                products[0] = "Ca(OH)2";
                products[1] = "H2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Ca")) && (conditions.equals("t > 300C"))) {
                products[0] = "CaO";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Ca")) && (conditions.equals("200C < t < 450C"))) {
                products[0] = "Ca3N2";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Mg")) && (conditions.equals("500C < t < 700C"))) {
                products[0] = "MgH2";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Ca")) && (ch[i].contains("dil. HCl"))) {
                products[0] = "CaCl2";
                products[1] = "H2";
            }
            if ((ch[i].equals("Ca")) && (ch[i].contains("dil. HNO3"))) {
                products[0] = "Ca(NO3)2";
                products[1] = "N2O";
                products[2] = "H2O";
            }
            if ((ch[i].equals("CaCO3")) && (ch[i].contains("dil. HCl"))) {
                products[0] = "CaCl2";
                products[1] = "H2O";
                products[2] = "CO2";
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
            if ((ch[i].equals("CaCO3")) && (condition.equals("900C < t < 1200C"))) {
                products[0] = "CaO";
                products[1] = "CO2";

            }

            if ((ch[i].equals("Ca(NO3)2")) && (condition.equals("450C < t < 500C"))) {
                products[0] = "Ca(NO)2";
                products[2] = "O2";
            }
            if ((ch[i].equals("Ca(NO3)2")) && (condition.equals("t > 561C"))) {
                products[0] = "CaO";
                products[1] = "NO2";
                products[2] = "O2";
            }
        }

        return products;
    }
}
