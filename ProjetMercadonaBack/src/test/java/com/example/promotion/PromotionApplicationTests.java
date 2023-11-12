package com.example.promotion;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Exécute l'ensemble des tests qui se situe dans les packages sélectionnés.
 */
@SelectPackages({"com.example.promotion.controleur"})
@Suite
class PromotionApplicationTests {
}
