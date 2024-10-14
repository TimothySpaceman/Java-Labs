package lab2.task2;

import java.util.Arrays;

public class Operation {
    public static double addition(double...  values){
        double result = 0;
        for(double value: values){
            result += value;
        }
        return result;
    }

    public static double subtraction(double...  values){
        int i = 0;
        double result = values[i];
        for(++i; i < values.length; i += 1){
            result -= values[i];
        }
        return result;
    }

    public static double multiplication(double...  values){
        double result = 1;
        for(double value: values){
            result *= value;
        }
        return result;
    }

    public static double division(double...  values){
        int i = 0;
        double result = values[i];
        for(++i; i < values.length; i += 1){
            result /= values[i];
        }
        return result;
    }

    public static double average(double...  values){
        return addition(values) / values.length;
    }

    public static double maximum(double...  values){
        double result = values[0];
        for(double value: values){
            if(value > result){
                result = value;
            }
        }
        return result;
    }

    public static double minimum(double...  values){
        double result = values[0];
        for(double value: values){
            if(value < result){
                result = value;
            }
        }
        return result;
    }
}
