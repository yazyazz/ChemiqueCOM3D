package com.chemique3d.app.OntologyDataHandler;

import com.chemique3d.app.Rules.LithiumReactions;

public class OntologyRetriever {
    // Create a Query instance
    boolean isElement;
    boolean isCompound;
    String cond = " ";

    public boolean checkIfReactant(String reactantA, String reactantB) {
        isElement = checkIfElement(reactantA, reactantB);
        isCompound = checkIfCompound(reactantA, reactantB);
        String[] reac = {reactantA, reactantB};

        if (isElement) {
            if ((reactantA.contains("Li")) || (reactantB.contains("Li"))) {
                LithiumReactions lr = new LithiumReactions();
                if ((reactantA.contains("N")) || (reactantB.contains("N"))) {
                    lr.productsWhenreactsWithNitrogenatSTP(reac, cond);
                }
            }
        }

        return true;
    }

    public boolean checkIfElement(String reactantA, String reactantB) {
        /*
        FileManager.get().addLocatorClassLoader(OntologyRetriever.class.getClassLoader());
        Model model=FileManager.get().loadModel("C:/Users/Sherry Peiris/Desktop/Videos-Ontology/Test.owl");

        String queryString="SELECT ?dataType ?data\n" +
                "WHERE {\n" +
                "  <http://nasa.dataincubator.org/launch/1961-012> ?dataType ?data.\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query,model);
        try {
            ResultSet resultSet = qe.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                Literal name = qs.getLiteral(te.toString());

            }
        }
            finally {
                qe.close();
            }

*/
        return isElement;
    }

    public boolean checkIfCompound(String reactantA, String reactantsB) {
        /*
        FileManager.get().addLocatorClassLoader(OntologyRetriever.class.getClassLoader());
        Model model=FileManager.get().loadModel("C:/Users/Sherry Peiris/Desktop/Videos-Ontology/Test.owl");

        String queryString="SELECT ?dataType ?data\n" +
                "WHERE {\n" +
                "  <http://nasa.dataincubator.org/launch/1961-012> ?dataType ?data.\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query,model);
        try {
            ResultSet resultSet = qe.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                Literal name = qs.getLiteral(te.toString());

            }
        }
            finally {
                qe.close();
            }

*/
        return isCompound;
    }

}
