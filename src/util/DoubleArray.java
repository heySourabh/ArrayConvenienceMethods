package util;

import java.util.Arrays;
import java.util.Random;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class DoubleArray {
    public static double[] add(double[] a1, double[] a2) {
        double[] result = new double[a1.length];

        for (int i = 0; i < a1.length; i++) {
            result[i] = a1[i] + a2[i];
        }

        return result;
    }

    public static double[] apply(double[] a1, double[] a2, DoubleBinaryOperator function) {
        double[] result = new double[a1.length];

        for (int i = 0; i < a1.length; i++) {
            result[i] = function.applyAsDouble(a1[i], a2[i]);
        }

        return result;
    }

    public static double[] apply(double[] array, DoubleUnaryOperator function) {
        double[] newArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = function.applyAsDouble(array[i]);
        }

        return newArray;
    }

    public static void copy(double[] from, double[] to) throws ArrayIndexOutOfBoundsException {
        System.arraycopy(from, 0, to, 0, from.length);
    }

    public static double[] zeros(int length) {
        return new double[length];
    }

    public static double[] ones(int length) {
        double[] ones = new double[length];

        Arrays.fill(ones, 1.0);

        return ones;
    }

    public static double[] random(int length) {
        return random(length, new Random());
    }

    public static double[] random(int length, Random generator) {
        double[] rDoubles = new double[length];

        for (int i = 0; i < length; i++) {
            rDoubles[i] = generator.nextDouble();
        }

        return rDoubles;
    }
}
