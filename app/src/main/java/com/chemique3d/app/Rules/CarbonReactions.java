package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class CarbonReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("C"))) {// && (ch[i].contains("steam"))) {
                products[0] = "CO";
                products[1] = "H2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("C"))) {//&& (conditions.contains("t > 1000C"))) {
                products[0] = "CO";
            }
            if ((ch[i].equals("CO"))) {// && (conditions.contains("600C < t < 700C"))) {
                products[0] = "CO2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("C")) && (conditions.contains("on electric discharge"))) {
                products[0] = "C2N2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("C")) && (conditions.contains("t = 600C, catalyst = Pt"))) {
                products[0] = "CH4";
            }
            if ((ch[i].equals("C")) && (conditions.contains("1500C < t < 2000C"))) {
                products[0] = "C2H2";
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
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("C")) && (ch[i].contains("hot conc. H2SO4"))) {
                products[0] = "CO2";
                products[2] = "H2O";
                products[1] = "SO2";
            }
            if ((ch[i].equals("C")) && (ch[i].contains("hot conc. HNO3"))) {
                products[0] = "CO2";
                products[2] = "H2O";
                products[1] = "NO2";
            }
        }
        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[0].equals("CH4")) && (ch[1].equals("Br2"))) {
                products[0] = "CBr4";
                products[1] = "HBr";

            }
        }

        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        return products;
    }
}
