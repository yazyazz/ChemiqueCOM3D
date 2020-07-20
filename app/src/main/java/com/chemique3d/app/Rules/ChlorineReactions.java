package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class ChlorineReactions extends Reactions {
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
            if ((ch[i].equals("Cl2"))) {
                products[0] = "HCl";
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
        for (int i = 0; i < ch.length; i++) {
            if ((ch[0].equals("Cl2")) && (ch[1].equals("NaI"))) {
                products[0] = "NaCl";
                products[1] = "I";

            }
        }
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        return products;
    }
}
