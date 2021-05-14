package org.csc133.a3.visitors;

import org.csc133.a3.gameobjects.Bird;
import org.csc133.a3.gameobjects.Helicopter;
import org.csc133.a3.gameobjects.RefuelingBlimp;
import org.csc133.a3.gameobjects.SkyScraper;

public interface IVisitor {
    void visit(Helicopter h);
    void visit(Bird b);
    void visit(SkyScraper s);
    void visit(RefuelingBlimp b);
}
