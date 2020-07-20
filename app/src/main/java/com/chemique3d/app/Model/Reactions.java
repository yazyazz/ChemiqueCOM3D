package com.chemique3d.app.Model;

public abstract class Reactions {
    abstract public String[] productsWhenreactWithWateratSTP(String[] ch, String condition);

    abstract public String[] productsWhenreactsWithOxygenatSTP(String[] ch, String condition);

    abstract public String[] productsWhenreactsWithNitrogenatSTP(String[] ch, String condition);

    abstract public String[] productsWhenreactsWithHydrogenatSTP(String[] ch, String condition);

    abstract public String[] productsWhenreactwithDiluteAcids(String[] ch, String condition);

    abstract public String[] productsWhenreactswithConcentratedAcids(String[] ch, String condition);

    abstract public String[] productsForOtherReactants(String[] ch, String condition);

    abstract public String[] productsForDecomposition(String[] ch, String condition);

}
