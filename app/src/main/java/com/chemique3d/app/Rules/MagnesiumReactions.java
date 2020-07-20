package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;


public class MagnesiumReactions extends Reactions {
    String[] products = {"", "", ""};
    String reactant1;
    String reactant2;

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            String reactants = ch[i];
            for (int j = 0; j < reactants.length(); j++) {
                System.out.println(reactants);
                if (reactants.substring(j, j + 1).equals("+")) {
                    reactant1 = reactants.substring(0, j - 1);
                    reactant2 = reactants.substring(j + 1);
                }

            }
            if ((reactant1.equals("Mg") && (reactant2.contains("hot H2O")))) {
                System.out.println("comes here");
                products[0] = "Mg(OH)2";
                products[1] = "H2";
            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Mg"))) {//&& (conditions.equals("600C < t < 650C"))) {
                products[0] = "MgO";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {

        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Mg")) && (conditions.equals("780C < t < 800C"))) {
                products[0] = "Mg3N2";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Mg")) && (conditions.equals("t = 150C, catalyst=MgCl2"))) {
                products[0] = "MgH2";

            }
        }
        return products;
    }

    @Override
    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Mg")) && (ch[i].contains("dil. HCl"))) {
                products[0] = "MgCl2";
                products[1] = "H2";
            }
            if ((ch[i].equals("Mg")) && (ch[i].contains("dil. HNO3"))) {
                products[0] = "Mg(NO3)2";
                products[1] = "N2O";
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
            if ((ch[i].equals("MgCO3")) && (condition.equals("350C < t < 650C"))) {
                products[0] = "MgO";
                products[1] = "CO2";

            }
            if ((ch[i].equals("Mg(NO3)2")) && (condition.equals("t > 300C"))) {
                products[0] = "MgO";
                products[1] = "NO2";
                products[2] = "O2";
            }
        }
        return products;
    }
}
