package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class LithiumReactions extends Reactions {
    String[] products;

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].equals("Li")) {
                products[0] = "LiOH";
                products[1] = "H2";

            }
            if (ch[i].equals("Li2O")) {
                products[0] = "LiOH";
                products[1] = "H2O";
            }
            if (ch[i].equals("L12O2")) {
                products[0] = "LiOH";
                products[1] = "H2O2";
            }
            if (ch[i].equals("L12CO3")) {
                products[0] = "LiHCO3";
                products[1] = "LiOH";
            }
            if (ch[i].equals("Li3N")) {
                products[0] = "LiOH";
                products[1] = "NH3";
                products[2] = "H2O";
            }

        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        if (ch.equals("Li")) {
            products[0] = "Li2O";
        }


        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        if (ch[0].contains("Li")) {
            products[0] = "Li3N";
        }
        if (ch.equals("LiH")) {
            products[0] = "Li3N";
            products[1] = "NH3";
        }


        return products;

    }

    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Li")) && (conditions.equalsIgnoreCase("500C < t < 700C"))) {
                products[0] = "LiH";
            }
        }
        return products;

    }

    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Li")) && (ch[i].equals("dil. HNO3"))) {
                products[0] = "LiNO3";
                products[1] = "NO";
                products[2] = "H2O";
            }

            if (ch[i].equals("HCl") && (ch[i].equals("Li3N"))) {
                products[0] = "LiCl";
                products[1] = "NH4Cl";
            }
        }
        return products;
    }

    public String[] productsWhenreactswithConcentratedAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Li")) && (ch[i].equals("conc. HCl"))) {
                products[0] = "LiCl";
                products[1] = "H2";

            }
            if ((ch[i].equals("Li")) && (ch[i].equals("conc. H2SO4"))) {
                products[0] = "Li2SO4";
                products[1] = "SO2";
                products[2] = "H2O";

            }
        }

        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            //Lithium obtaining reaction
            if ((ch[i].equals("Li2O")) && (ch[i].equals("Si")) && (conditions.equalsIgnoreCase("t = 1000C"))) {
                products[0] = "Li";
                products[1] = "SiO2";

            }

            //Lithium obtaining reaction
            if ((ch[i].equals("Li2O")) && (ch[i].equals("Mg")) && (conditions.equalsIgnoreCase("t > 800C"))) {
                products[0] = "Li";
                products[1] = "MgO";

            }

            //Lithium obtaining reaction
            if ((ch[i].equals("Li2O")) && (ch[i].equals("Al")) && (conditions.equalsIgnoreCase("t > 1000C"))) {
                products[0] = "Li";
                products[1] = "Al2O3";

            }

            //reactions that create halides of lithium
            if ((ch[i].equals("Li")) && (ch[i].equals("F2")) && (conditions.equalsIgnoreCase("t = STP"))) {
                products[0] = "LiF";
            }
            if ((ch[i].equals("Li")) && (ch[i].equals("Cl2")) && (conditions.equalsIgnoreCase("t = STP"))) {
                products[0] = "LiCl";
            }
            if ((ch[i].equals("Li")) && (ch[i].equals("Br2")) && (conditions.equalsIgnoreCase("t = STP"))) {
                products[0] = "LiBr";
            }
            if ((ch[i].equals("Li")) && (ch[i].equals("I2")) && (conditions.equalsIgnoreCase("t > 200C"))) {
                products[0] = "LiI";
            }

            //reactions between ammonia and Lithium in different conditions
            if ((ch[i].equals("Li")) && (ch[i].equals("NH3")) && (conditions.equalsIgnoreCase("t = 220C"))) {
                products[0] = "LiNH2";
                products[1] = "N2";
            }
            if ((ch[i].equals("Li")) && (ch[i].equals("NH3")) && (conditions.equalsIgnoreCase("t = 400C"))) {
                products[0] = "Li2NH";
                products[1] = "N2";
            }
            if ((ch[i].equals("Li")) && (ch[i].equals("NH3(l)")) && (conditions.equalsIgnoreCase("t = -40C"))) {
                products[0] = "[Li(NH3)4]0";

            }

            //decomposition reactions
            if (ch[i].equals("Li2CO3") && (conditions.equalsIgnoreCase("730C < t < 1270C"))) {
                products[0] = "Li2O";
                products[1] = "CO2";
            }
            if (ch[i].equals("LiNO3") && (conditions.equalsIgnoreCase("475C < t < 650C"))) {
                products[0] = "Li2O";
                products[1] = "NO2";
                products[2] = "O2";
            }

            //reactions of carbonates
            if ((ch[i].equals("Li2CO3")) && (ch[i].equals("C")) && (conditions.equalsIgnoreCase("t = 800C"))) {
                products[0] = "Li20";
                products[1] = "CO";
            }
            if ((ch[i].equals("Li2CO3")) && (ch[i].equals("Mg")) && (conditions.equalsIgnoreCase("t = 500C"))) {
                products[0] = "Li";
                products[1] = "MgO";
                products[2] = "CO2";
            }


        }
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Li2CO3") && (condition.equals("730C < t <`1270C")))) {
                products[0] = "Li2O";
                products[1] = "CO2";
            }
            if ((ch[i].equals("LiNO3") && (condition.equals("475C < t <`650C")))) {
                products[0] = "Li2O";
                products[1] = "NO2";
                products[2] = "O2";
            }
        }
        return products;
    }

}
