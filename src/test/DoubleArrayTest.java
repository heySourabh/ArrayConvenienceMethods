package test;

import org.junit.Test;
import util.DoubleArray;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class DoubleArrayTest {

    @Test
    public void applyArrayFunction() throws Exception {
        double[] array1 = new double[200];
        double[] array2 = new double[200];
        double[] expectedArray = new double[200];

        Random r = new Random(123);
        for (int i = 0; i < array1.length; i++) {
            array1[i] = r.nextDouble();
            array2[i] = r.nextDouble();
            expectedArray[i] = array1[i] * array2[i];
        }

        double[] computedArray = DoubleArray.apply(array1, array2, (e1, e2) -> e1 * e2);

        assertArrayEquals(expectedArray, computedArray, 1e-15);
    }

    @Test
    public void applyScalarFunction() throws Exception {
        double[] oldArray = new double[500];
        double[] expectedArray = new double[500];

        Random r = new Random(345);
        for (int i = 0; i < oldArray.length; i++) {
            oldArray[i] = r.nextDouble();
            expectedArray[i] = oldArray[i] * 1.45;
        }

        double[] computedArray = DoubleArray.apply(oldArray, e -> e * 1.45);

        assertArrayEquals(expectedArray, computedArray, 1e-15);
    }

    @Test
    public void copy() throws Exception {
        double[] from = DoubleArray.random(500);
        double[] to = new double[from.length];

        DoubleArray.copy(from, to);

        assertArrayEquals(from, to, 1e-15);
    }

    @Test
    public void zeros() throws Exception {
        double[] expectedArray = new double[400];

        Arrays.fill(expectedArray, 0.0);

        assertArrayEquals(expectedArray, DoubleArray.zeros(400), 1e-15);
    }

    @Test
    public void ones() throws Exception {
        double[] expectedArray = new double[400];

        Arrays.fill(expectedArray, 1.0);

        assertArrayEquals(expectedArray, DoubleArray.ones(400), 1e-15);
    }

    @Test
    public void add() throws Exception {

        double[][] arrays = new double[50][100];
        double[] expectedTotal = new double[100];

        Random r = new Random(254);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 100; j++) {
                arrays[i][j] = r.nextDouble();
                expectedTotal[j] += arrays[i][j];
            }
        }

        // With precondition
        double[] computedTotal = Arrays.stream(arrays)
                .reduce(DoubleArray.zeros(100), DoubleArray::add);
        assertArrayEquals(expectedTotal, computedTotal, 1e-15);

        // Without precondition
        computedTotal = Arrays.stream(arrays)
                .reduce(DoubleArray::add).orElse(DoubleArray.zeros(100));
        assertArrayEquals(expectedTotal, computedTotal, 1e-15);
    }
}