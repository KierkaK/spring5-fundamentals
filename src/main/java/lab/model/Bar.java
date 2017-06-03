package lab.model;

import lab.model.simple.Squishee;

@FunctionalInterface
public interface Bar {
    Squishee sellSquishee(Person customer);
}