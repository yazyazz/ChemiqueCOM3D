package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class AluminiumReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {

        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            System.out.println("Then here");
            if (ch[i].contains("Al")) {
                System.out.println("Then here");
                products[0] = "Al2O3";
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
            if ((ch[i].equals("Al")) && (ch[i].contains("dil. HCl"))) {
                products[0] = "AlCl3";
                products[1] = "H2";
            }
            if ((ch[i].equals("Al")) && (ch[i].contains("dil. HNO3"))) {
                products[0] = "Al(NO3)3";
                products[1] = "NO2";
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
        return products;
    }
}
