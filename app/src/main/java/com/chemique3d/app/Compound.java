package com.chemique3d.app;

import java.util.ArrayList;
import java.util.List;

public class Compound {
    private List<Element> elements = new ArrayList<>();

    public Compound() {
    }

    public void addElement(Element ele) {
        elements.add(ele);
    }

    public void setElement(int idx, Element ele) {
        elements.set(idx, ele);
    }

    public Element getElement(int idx) {
        return elements.get(idx);
    }

    public Element getElement(Element e) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getName().equals(e.getName())) {
                return elements.get(i);
            }
        }
        return new Element(null, 0);
    }

    public int length() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
