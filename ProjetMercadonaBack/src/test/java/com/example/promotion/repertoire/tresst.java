package com.example.promotion.repertoire;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class tresst {

    private final Calcul calcul= new Calcul();

    @Test
    void testAddition() {
        assertEquals(4, calcul.addition(2, 2));
    }
}




