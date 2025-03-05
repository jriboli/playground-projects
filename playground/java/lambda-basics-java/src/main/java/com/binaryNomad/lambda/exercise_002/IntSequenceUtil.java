package com.binaryNomad.lambda.exercise_002;

public class IntSequenceUtil {

    public static IntSequence of(int... values) {
        return new IntSequence() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public int next() {
                return values[index++];
            }
        };
    }

    public static IntSequence constant(int value) {
        return new IntSequence() {
            @Override
            public boolean hasNext() {
                return true; // Infinite sequence
            }

            @Override
            public int next() {
                return value;
            }
        };
    }
}
