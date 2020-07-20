package com.chemique3d.app.Rules;

import com.chemique3d.app.Model.Reactions;

public class SodiumReactions extends Reactions {
    String[] products = {"", "", ""};

    @Override
    public String[] productsWhenreactWithWateratSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].contains("Na")) {
                products[0] = "NaOH";
                products[1] = "H2";

            }
            if (ch[i].equals("Na2O")) {
                products[0] = "NaOH";
                products[1] = "H2O";
            }
            if (ch[i].equals("Na2O2")) {
                products[0] = "NaOH";
                products[1] = "H2O2";
            }
            if (ch[i].equals("Na2CO3")) {
                products[0] = "NaHCO3";
                products[1] = "NaOH";
            }
            if (ch[i].equals("Na3N")) {
                products[0] = "NaOH";
                products[1] = "NH3";
                products[2] = "H2O";
            }

        }
        return products;
    }

    @Override
    public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Na")) && (conditions.equalsIgnoreCase("250C < t < 400C"))) {
                products[0] = "Na2O2";
            }
        }

        return products;
    }

    @Override
    public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].contains("Na"))) {//&& (conditions.equalsIgnoreCase("t = 100C, electric discharge"))) {
                products[0] = "Na3N";

            }
            if (ch[i].contains("NaH")) {
                System.out.println("hr");
                products[0] = "Na3N";
                products[1] = "NH3";
            }
        }

        return products;

    }

    public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Na")) && (conditions.equalsIgnoreCase("250C < t < 400C"))) {
                products[0] = "NaH";
            }
        }
        return products;
    }

    public String[] productsWhenreactwithDiluteAcids(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].equals("Na") && (ch[i].equals("dil. HCl"))) {
                products[0] = "NaCl";
                products[1] = "H2";
            }
            if (ch[i].equals("NaHCO3") && (ch[i].equals("dil. HCl"))) {
                products[0] = "NaCl";
                products[1] = "CO2";
                products[2] = "H2O";
            }
            if (ch[i].equals("HCl") && (ch[i].equals("Na3N"))) {
                products[0] = "LiCl";
                products[1] = "NH4Cl";
            }
            if (ch[i].equals("NaOH") && (ch[i + 1].equals("H2SO4"))) {
                products[0] = "Na2SO4";
                products[1] = "H2O";
            }
        }
        return products;
    }

    public String[] productsWhenreactswithConcentratedAcids(String[] ch, String conditions) {
        if (ch.equals("Li")) {
            products[0] = "Li2O";
        }
        return products;
    }

    @Override
    public String[] productsForOtherReactants(String[] ch, String conditions) {
        for (int i = 0; i < ch.length; i++) {
            //reactions that create halides of sodium
            if ((ch[i].equals("Na")) && (ch[i].equals("F2")) && (conditions.equalsIgnoreCase("t = STP"))) {
                products[0] = "NaF";
            }
            if ((ch[i].equals("Na")) && (ch[i].equals("Cl2")) && (conditions.equalsIgnoreCase("t = STP"))) {
                products[0] = "NaCl";
            }
            if ((ch[i].equals("Na")) && (ch[i].equals("Br2")) && (conditions.equalsIgnoreCase("150C < t < 250C"))) {
                products[0] = "NaBr";
            }
            if ((ch[i].equals("Na")) && (ch[i].equals("I2")) && (conditions.equalsIgnoreCase("150C < t < 250C"))) {
                products[0] = "NaI";
            }

            //reactions involving S, Se, Te
            if ((ch[i].equals("Na")) && (ch[i].equals("S")) && (conditions.equalsIgnoreCase("t > 130C"))) {
                products[0] = "Na2S";
            }
            if ((ch[i].equals("Na")) && (ch[i].equals("Se")) && (conditions.equalsIgnoreCase("t > 130C"))) {
                products[0] = "Na2Se";
            }
            if ((ch[i].equals("Na")) && (ch[i].equals("Te")) && (conditions.equalsIgnoreCase("t > 130C"))) {
                products[0] = "Na2Te";
            }

            //bicarbonate reactions
            if ((ch[i].equals("NaHCO3")) && (conditions.equalsIgnoreCase("250C < t < 300C"))) {
                products[0] = "Na2CO3";
                products[1] = "CO2";
                products[2] = "H2O";
            }
        }
        return products;
    }

    @Override
    public String[] productsForDecomposition(String[] ch, String condition) {
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i].equals("Na2CO3") && (condition.equals("t =`1000C")))) {
                products[0] = "Na2O";
                products[1] = "CO2";
            }
            if ((ch[i].equals("NaHCO3") && (condition.equals("250C < t <`300C")))) {
                products[0] = "Na2CO3";
                products[1] = "Na2O";
                products[2] = "CO2";
            }
        }
        return products;
    }

}
