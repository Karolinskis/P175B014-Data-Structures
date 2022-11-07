package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

/*
 * Aibės testavimas be Gui
 * Dirbant su Intellij ir norint konsoleje matyti gražų pasuktą medį,
 * reikia File -> Settings -> Editor -> File Encodings -> Global encoding pakeisti į UTF-8
 *
 */
public class ManualTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US);   // Suvienodiname skaiciu formatus
        new BstSetTests().executeTests();
        new AvlSetTests().executeTests();
    }
}
