package com.chemique3d.app.ChemicalDetector;

import com.chemique3d.app.OntologyDataHandler.OntologyRetriever;
import com.chemique3d.app.Rules.AluminiumReactions;
import com.chemique3d.app.Rules.ArgonReactions;
import com.chemique3d.app.Rules.BerryliumReactions;
import com.chemique3d.app.Rules.BoronReactions;
import com.chemique3d.app.Rules.CalciumReactions;
import com.chemique3d.app.Rules.CarbonReactions;
import com.chemique3d.app.Rules.ChlorineReactions;
import com.chemique3d.app.Rules.FluorineReactions;
import com.chemique3d.app.Rules.HeliumReactions;
import com.chemique3d.app.Rules.HydrogenReactions;
import com.chemique3d.app.Rules.LithiumReactions;
import com.chemique3d.app.Rules.MagnesiumReactions;
import com.chemique3d.app.Rules.NeonReactions;
import com.chemique3d.app.Rules.NitrogenReactions;
import com.chemique3d.app.Rules.OxygenReactions;
import com.chemique3d.app.Rules.PhosporousReactions;
import com.chemique3d.app.Rules.PottasiumReactions;
import com.chemique3d.app.Rules.SiliconReactions;
import com.chemique3d.app.Rules.SodiumReactions;
import com.chemique3d.app.Rules.SulphurReactions;

//class to rectify any false positives detected during OCR text recognition
public class TextProcessor {
    OntologyRetriever ontor;
    LithiumReactions lr;
    BerryliumReactions ber;
    BoronReactions br;
    CarbonReactions cr;
    NitrogenReactions nr;
    OxygenReactions or;
    FluorineReactions fr;
    NeonReactions ner;
    SodiumReactions nar;
    MagnesiumReactions mgr;
    AluminiumReactions alr;
    SiliconReactions sir;
    PhosporousReactions pr;
    SulphurReactions sr;
    ChlorineReactions clr;
    ArgonReactions arg;
    PottasiumReactions kr;
    CalciumReactions car;
    HeliumReactions he;
    HydrogenReactions hy;

    String condition = " ";
    String[] finproducts = {" ", " ", " ", " "};
    String reactantA = "";
    String reactantB = "";

    public TextProcessor() {
        car = new CalciumReactions();
        arg = new ArgonReactions();
        nar = new SodiumReactions();
        hy = new HydrogenReactions();
        he = new HeliumReactions();
        clr = new ChlorineReactions();
        lr = new LithiumReactions();
        ber = new BerryliumReactions();
        br = new BoronReactions();
        cr = new CarbonReactions();
        nr = new NitrogenReactions();
        or = new OxygenReactions();
        fr = new FluorineReactions();
        ner = new NeonReactions();
        mgr = new MagnesiumReactions();
        alr = new AluminiumReactions();
        sir = new SiliconReactions();
        pr = new PhosporousReactions();
        sr = new SulphurReactions();
        kr = new PottasiumReactions();

    }
/*
    public String getRectifiedChemical(String recognizedtext) {
        //returns the rectified chemical symbol to be displayed in the window
        String rectifiedchemicalname = "";

       // boolean ifElement = checkIfElement();
       // boolean ifCompound = checkIfCompound();
        /*if (ifElement || ifCompound) {
            return rectifiedchemicalname;
        } else
            return "Error in finding chemical name. Please try again.";
    }*/

    public boolean checkIfElement(String dettext) {
        boolean isElement = true;

        return isElement;
    }

    public boolean checkIfCompound(String dettext) {
        boolean isCompound = true;
        //ArrayList<ChemicalElement>=or.retrieveAllElements();
        return isCompound;
    }

    public String[] reactionClassifier(String[] identifiedtext, String condition) {

        String test = identifiedtext[0];
        for (int j = 0; j < test.length(); j++) {
            if ((String.valueOf(test.charAt(j)).equals("+")) || (test.substring(j)).contains("and")) {
                reactantA = test.substring(0, j);
                reactantB = test.substring(j + 1);


            }
        }
        System.out.println("first reactant " + reactantA);
        System.out.println("second reactant " + reactantB);

        if ((reactantA.contains("H")) && (reactantB.equals("N2"))) {
            finproducts = hy.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("H")) && (reactantB.equals("O2"))) {
            finproducts = hy.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("H")) && (reactantB.equals("H2O"))) {
            finproducts = hy.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("H")) && (reactantB.equals("H2"))) {
            finproducts = hy.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("H")) && (reactantB.equals("HCl"))) {
            finproducts = hy.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = hy.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("H")) && (reactantB.equals("H2SO4"))) {
            finproducts = hy.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = hy.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("H")) && (reactantB.equals("HNO3"))) {
            finproducts = hy.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = hy.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("H") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = hy.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.equals("He")) || (reactantB.equals("He"))) {
            finproducts[0] = "invalid chemical names";
        } else if ((reactantA.contains("Li")) && (reactantB.equals("N2"))) {
            finproducts = lr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Li")) && (reactantB.equals("O2"))) {
            finproducts = lr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Li")) && (reactantB.equals("H2O"))) {
            finproducts = lr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Li")) && (reactantB.equals("H2"))) {
            finproducts = lr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Li")) && (reactantB.equals("HCl"))) {
            finproducts = lr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = lr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Li")) && (reactantB.equals("H2SO4"))) {

            finproducts = lr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = lr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Li")) && (reactantB.equals("HNO3"))) {
            finproducts = lr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = lr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("Li") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = lr.productsForOtherReactants(identifiedtext, condition);
        } else if (reactantA.contains("B")) {
            for (int i = 0; i < reactantA.length(); i++) {
                if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("N2"))) {
                    finproducts = ber.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("O2"))) {
                    finproducts = ber.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("H2O"))) {
                    finproducts = ber.productsWhenreactWithWateratSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("H2"))) {
                    finproducts = ber.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("HCl"))) {
                    finproducts = ber.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = ber.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("H2SO4"))) {
                    finproducts = ber.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = ber.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Be")) && (reactantB.equals("HNO3"))) {
                    finproducts = ber.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = ber.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if (reactantA.substring(0, i + 1).equals("Be") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                        || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                    finproducts = ber.productsForOtherReactants(identifiedtext, condition);
                } else {
                    if ((reactantA.contains("B")) && (reactantB.equals("N2"))) {
                        finproducts = br.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("B")) && (reactantB.equals("O2"))) {

                        finproducts = br.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("B")) && (reactantB.equals("H2O"))) {
                        finproducts = br.productsWhenreactWithWateratSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("B")) && (reactantB.equals("H2"))) {
                        finproducts = br.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("B")) && (reactantB.equals("HCl"))) {
                        finproducts = br.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = br.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if ((reactantA.contains("B")) && (reactantB.equals("H2SO4"))) {
                        finproducts = br.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = br.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if ((reactantA.contains("B")) && (reactantB.equals("HNO3"))) {
                        finproducts = br.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = br.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if (reactantA.contains("B") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                            || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                        finproducts = br.productsForOtherReactants(identifiedtext, condition);
                    } else {
                        finproducts[0] = "invalid chemical names";
                    }

                }

            }

        } else if (reactantA.contains("C")) {
            for (int i = 0; i < reactantA.length(); i++) {
                if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("N2"))) {
                    finproducts = car.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("O2"))) {
                    finproducts = car.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("H2O"))) {
                    finproducts = car.productsWhenreactWithWateratSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("H2"))) {
                    finproducts = car.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("HCl"))) {
                    finproducts = car.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = car.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("H2SO4"))) {
                    finproducts = car.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = car.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ca")) && (reactantB.equals("HNO3"))) {
                    finproducts = car.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = car.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if (reactantA.substring(0, i + 1).equals("Ca") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                        || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                    finproducts = car.productsForOtherReactants(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("N2"))) {
                    finproducts = clr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("O2"))) {
                    finproducts = clr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("H2O"))) {
                    finproducts = clr.productsWhenreactWithWateratSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("H2"))) {
                    finproducts = clr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("HCl"))) {
                    finproducts = clr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = clr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("H2SO4"))) {
                    finproducts = clr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = clr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Cl")) && (reactantB.equals("HNO3"))) {
                    finproducts = clr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = clr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if (reactantA.substring(0, i + 1).equals("Cl") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                        || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                    finproducts = clr.productsForOtherReactants(identifiedtext, condition);
                } else {

                    if ((reactantA.contains("C")) && (reactantB.equals("N2"))) {
                        finproducts = cr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("C")) && (reactantB.equals("O2"))) {
                        finproducts = cr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("C")) && (reactantB.equals("H2O"))) {
                        finproducts = cr.productsWhenreactWithWateratSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("C")) && (reactantB.equals("H2"))) {
                        finproducts = cr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("C")) && (reactantB.equals("HCl"))) {
                        finproducts = cr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = cr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if ((reactantA.contains("C")) && (reactantB.equals("H2SO4"))) {
                        finproducts = cr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = cr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if ((reactantA.contains("C")) && (reactantB.equals("HNO3"))) {
                        finproducts = cr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = cr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if (reactantA.contains("C") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                            || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                        finproducts = cr.productsForOtherReactants(identifiedtext, condition);
                    }
                }
            }
        } else if (reactantA.contains("N")) {
            System.out.println("Comes here first");
            if ((reactantB.equals("N")) && (reactantA.contains("N2"))) {
                finproducts[0] = "invalid chemical names";
            }
            for (int i = 0; i < reactantA.length(); i++) {
                System.out.println(reactantA.substring(0, i + 1));
                if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("N2"))) {
                    finproducts = ner.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("O2"))) {
                    finproducts = ner.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("H2O"))) {
                    finproducts = ner.productsWhenreactWithWateratSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("H2"))) {
                    finproducts = ner.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("HCl"))) {
                    finproducts = ner.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = ner.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("H2SO4"))) {
                    finproducts = ner.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = ner.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Ne")) && (reactantB.equals("HNO3"))) {
                    finproducts = ner.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = ner.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if (reactantA.substring(0, i + 1).equals("Ne") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                        || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                    finproducts = ner.productsForOtherReactants(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("N2"))) {
                    System.out.println("Comes here");
                    finproducts = nar.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("O2"))) {
                    finproducts = nar.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("H2O"))) {
                    finproducts = nar.productsWhenreactWithWateratSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("H2"))) {
                    finproducts = nar.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("HCl"))) {
                    finproducts = nar.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = nar.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("H2SO4"))) {
                    finproducts = nar.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = nar.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                } else if ((reactantA.substring(0, i + 1).equals("Na")) && (reactantB.equals("HNO3"))) {
                    finproducts = nar.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    finproducts = nar.productsWhenreactwithDiluteAcids(identifiedtext, condition);

                } else if (reactantA.substring(0, i + 1).equals("Na") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                        || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                    finproducts = nar.productsForOtherReactants(identifiedtext, condition);
                } else {
                    if ((reactantA.contains("N")) && (reactantB.equals("O2"))) {
                        finproducts = nr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("N")) && (reactantB.equals("H2O"))) {
                        finproducts = nr.productsWhenreactWithWateratSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("N")) && (reactantB.equals("H2"))) {
                        finproducts = nr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
                    } else if ((reactantA.contains("N")) && (reactantB.equals("HCl"))) {
                        finproducts = nr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = nr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if ((reactantA.contains("N")) && (reactantB.equals("H2SO4"))) {
                        finproducts = nr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                    } else if ((reactantA.contains("N")) && (reactantB.equals("HNO3"))) {
                        finproducts = nr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
                        finproducts = nr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
                    } else if (reactantA.contains("N") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                            || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
                        finproducts = nr.productsForOtherReactants(identifiedtext, condition);
                    }
                }


            }
        } else if ((reactantA.contains("O")) && (reactantB.equals("N2"))) {
            finproducts = or.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);

        } else if ((reactantA.contains("O")) && (reactantB.equals("O2"))) {
            finproducts = or.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("O")) && (reactantB.equals("H2O"))) {
            finproducts = or.productsWhenreactWithWateratSTP(identifiedtext, condition);

        } else if ((reactantA.contains("O")) && (reactantB.equals("H2"))) {
            finproducts = or.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);

        } else if ((reactantA.contains("O")) && (reactantB.equals("HCl"))) {
            finproducts = or.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = or.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("O")) && (reactantB.equals("H2SO4"))) {
            finproducts = or.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = or.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("O")) && (reactantB.equals("HNO3"))) {
            finproducts = or.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = or.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("O") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = or.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("N2"))) {
            finproducts = fr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("O2"))) {
            finproducts = fr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("H2O"))) {
            finproducts = fr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("H2"))) {
            finproducts = fr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("HCl"))) {
            finproducts = fr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = fr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("H2SO4"))) {
            finproducts = fr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = fr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("F")) && (reactantB.equals("HNO3"))) {
            finproducts = fr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = fr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("F") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = fr.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("N2"))) {
            finproducts = mgr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("O2"))) {
            finproducts = mgr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("H2O"))) {
            finproducts = mgr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("H2"))) {
            finproducts = mgr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("HCl"))) {
            System.out.println("inside here");
            finproducts = mgr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = mgr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("H2SO4"))) {
            finproducts = mgr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = mgr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Mg")) && (reactantB.equals("HNO3"))) {
            finproducts = mgr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = mgr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("Mg") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = mgr.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("Al")) && (reactantB.equals("N2"))) {
            finproducts = alr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Al")) && (reactantB.equals("O2"))) {
            System.out.println("comes here");
            finproducts = alr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Al")) && (reactantB.equals("H2O"))) {
            finproducts = alr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Al")) && (reactantB.equals("H2"))) {
            finproducts = alr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Al")) && (reactantB.equals("HCl"))) {
            finproducts = alr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = alr.productsWhenreactwithDiluteAcids(identifiedtext, condition);

        } else if ((reactantA.contains("Al")) && (reactantB.equals("H2SO4"))) {
            finproducts = alr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = alr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Al")) && (reactantB.equals("HNO3"))) {
            finproducts = alr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = alr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("Al") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = alr.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("Si")) && (reactantB.equals("N2"))) {
            finproducts = sir.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Si")) && (reactantB.equals("O2"))) {
            finproducts = sir.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Si")) && (reactantB.equals("H2O"))) {
            finproducts = sir.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Si")) && (reactantB.equals("H2"))) {
            finproducts = sir.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);

        } else if ((reactantA.contains("Si")) && (reactantB.equals("HCl"))) {
            finproducts = sir.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = sir.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Si")) && (reactantB.equals("H2SO4"))) {
            finproducts = sir.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = sir.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Si")) && (reactantB.equals("HNO3"))) {
            finproducts = sir.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = sir.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("Si") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = sir.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("N2"))) {
            finproducts = pr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("O2"))) {
            finproducts = pr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("H2O"))) {
            finproducts = pr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("H2"))) {
            finproducts = pr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("HCl"))) {

            finproducts = pr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = pr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("H2SO4"))) {
            finproducts = pr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = pr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("P")) && (reactantB.equals("HNO3"))) {
            finproducts = pr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = pr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("P") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = pr.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("N2"))) {

            finproducts = sr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("O2"))) {
            finproducts = sr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("H2O"))) {
            finproducts = sr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("H2"))) {
            finproducts = sr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("HCl"))) {
            finproducts = sr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = sr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("H2SO4"))) {
            finproducts = sr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = sr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("S")) && (reactantB.equals("HNO3"))) {
            finproducts = sr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = sr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if (reactantA.contains("S") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = sr.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("Ar")) && (reactantB.equals("N2"))) {
            finproducts = arg.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Ar")) && (reactantB.equals("O2"))) {
            finproducts = arg.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Ar")) && (reactantB.equals("H2O"))) {
            finproducts = arg.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Ar")) && (reactantB.equals("H2"))) {
            finproducts = arg.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("Ar")) && (reactantB.equals("HCl"))) {
            finproducts = arg.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = arg.productsWhenreactwithDiluteAcids(identifiedtext, condition);

        } else if ((reactantA.contains("Ar")) && (reactantB.equals("H2SO4"))) {
            finproducts = arg.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = arg.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("Ar")) && (reactantB.equals("HNO3"))) {
            finproducts = arg.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = arg.productsWhenreactwithDiluteAcids(identifiedtext, condition);

        } else if (reactantA.contains("Ar") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = arg.productsForOtherReactants(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("N2"))) {
            finproducts = kr.productsWhenreactsWithNitrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("O2"))) {
            finproducts = kr.productsWhenreactsWithOxygenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("H2O"))) {
            finproducts = kr.productsWhenreactWithWateratSTP(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("H2"))) {
            finproducts = kr.productsWhenreactsWithHydrogenatSTP(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("HCl"))) {
            finproducts = kr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = kr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("H2SO4"))) {
            finproducts = kr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = kr.productsWhenreactwithDiluteAcids(identifiedtext, condition);
        } else if ((reactantA.contains("K")) && (reactantB.equals("HNO3"))) {
            finproducts = kr.productsWhenreactswithConcentratedAcids(identifiedtext, condition);
            finproducts = kr.productsWhenreactwithDiluteAcids(identifiedtext, condition);

        } else if (reactantA.contains("K") && ((!reactantB.equals("HNO3")) || (!reactantB.equals("H2SO4")) || (!reactantB.equals("HCl"))
                || (!reactantB.equals("H2O")) || (!reactantB.equals("H2")) || (!reactantB.equals("N2")) || (!reactantB.equals("O2")) || (reactantB.equals("H2")))) {
            finproducts = kr.productsForOtherReactants(identifiedtext, condition);
        } else {
            finproducts[0] = "invalid chemical names";
        }


        return finproducts;
    }


    public String getRectifiedConditions(String recognizedtext) {
        String rectifiedcondition = "";
        //returns the rectified chemical symbol to be displayed in the window
        boolean ifValidCondition = checkIfConditionIsValid();
        if (ifValidCondition) {
            return rectifiedcondition;
        } else
            return "Invalid condition. Please specify valid condition";
    }

    private boolean checkIfConditionIsValid() {
        boolean IsValidCondition = false;
        return IsValidCondition;
    }

}
