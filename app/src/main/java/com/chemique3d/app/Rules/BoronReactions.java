package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class BoronReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("B")) && (ch[i].contains("steam"))) {
                products[0] = "B2O3";
                products[1] = "H2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {

        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("B")) && (conditions.contains("t = 700C"))) {
                products[0] = "B2O3";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("B")) && (conditions.contains("900C < t <1000C"))) {
                products[0] = "B(OH)3";
                products[1] = "NO2";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsWhenreactswithConcentratedAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("B")) && (ch[i].equals("hot conc HNO3"))) {
                products[0] = "B(OH)3";
                products[1] = "NO2";

            }
        }
        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String conditions) {
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        return products;
    }
}
