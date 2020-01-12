package coursera.dsp.floats;


import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

/**
 * An immutable array of floats with useful methods for processing data.
 * A lot of mathy stuff in this class is still not optimal and is used in experimental code. But there are also
 * methods that are used in real code.
 */
public class Floats {
    private final float[] data;
    private int startIdx, endIdx, size;

    public Floats() {
        this.data = new float[0];
    }

    public Floats(float... value) {
        this(0, value.length - 1, value.length, value);
    }
    public Floats(int startIdx, int endIdx, int size, float[] value) {
        if(startIdx < 0)
            throw new IndexOutOfBoundsException("Start index cannot be negative: " + startIdx);
        if(endIdx >= value.length)
            throw new IndexOutOfBoundsException("End index: " + endIdx + ", but the size: " + value.length);
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.size = size;
        this.data = value;
    }
    
    public static Floats repeat(float v, int n) {
        float[] data = new float[n];
        Arrays.fill(data, v);
        return new Floats(data);
    }
    public static Floats sequence(float from, float toInclusive, int n) {
        if(from > toInclusive)
            throw new IllegalArgumentException("From ("+from+") cannot be greater than to ("+toInclusive+")");
        float step = Math.abs(toInclusive - from) / n;
        float[] data = new float[n];
        int i = 0;
        for(float v = from; (toInclusive - v)/step > .001; v+=step)
            data[i++] = v;
        return new Floats(data);
    }

    public static Floats fromFloat32(byte[] bytes) {
        return new Floats(byteToFloat(bytes));
    }

    public int size() { return size; }
    public float get(int idx) {
        if (idx < 0 || idx >= size)
            throw new IllegalArgumentException("Index["+idx+"] is out of array[0;"+size+"]");
        if(idx < startIdx || idx > endIdx)
            return 0;
        return data[idx];
    }
    public float getLast() {
        return get(endIdx);
    }
    public Floats get(int startIdxInclusive, int endIdxInclusive) {
        int newStart = startIdx + startIdxInclusive;
        int newEnd = startIdx + endIdxInclusive;
        return new Floats(newStart, newEnd, newEnd - newStart + 1, data);
    }
    public Floats getBand(int from, int toInclusive) {
        return new Floats(from, toInclusive, size, data);
    }
    public Floats getBand(int [] indices) {
        float[] result = new float[size];
        for (int index : indices)
            result[index] = data[index];
        return new Floats(result);
    }
    public float max() {
        return maxPoint().getValue();
    }
    public FloatEntry maxPoint() {
        float maxValue = data[startIdx];
        int maxIndex = startIdx;
        for (int i = startIdx + 1; i <= endIdx; i++) {
            if(data[i] > maxValue) {
                maxValue = data[i];
                maxIndex = i;
            }
        }
        return new FloatEntry(maxValue, maxIndex);
    }
    public float min() {
        return minPoint().getValue();
    }
    public FloatEntry minPoint() {
        float maxValue = data[startIdx];
        int maxIndex = startIdx;
        for (int i = startIdx + 1; i <= endIdx; i++) {
            if(data[i] < maxValue) {
                maxValue = data[i];
                maxIndex = i;
            }
        }
        return new FloatEntry(maxValue, maxIndex);
    }

    public float sum() {
        float sum = 0;
        for (int i = startIdx; i <= endIdx; i++) {
            float next = data[i];
            sum += next;
        }
        return sum;
    }
    public float mean() {
        return sum() / size();
    }

    public float dot(Floats another) {
        if(another.size() != size()) throw new IllegalArgumentException("Different sizes");
        float result = 0;
        for (int i = 0; i <= endIdx; i++)
            result += data[i] * another.get(i);
        return result;
    }
    public Floats times(Floats factors) {
        if(size() != factors.size()) throw new IllegalArgumentException();
        float[] result = new float[size()];
        for(int i = startIdx; i <= endIdx; i++)
            result[i] = data[i] * factors.data[i];
        return new Floats(result);
    }
    public Floats times(float factor) {
        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++)
            result[i] = data[i] * factor;
        return new Floats(result);
    }
    public Floats abs() {
        float[] r = new float[data.length];
        for (int i = 0; i < data.length; i++)
            r[i] = Math.abs(data[i]);
        return new Floats(r);
    }
    public Floats toPower(double power) {
        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++)
            result[i] = (float) Math.pow(data[i], power);
        return new Floats(result);
    }
    public Floats filter(Predicate<Float> predicate) {
        float[] result = new float[data.length];
        int counter = 0;
        for (float value : data)
            if(predicate.test(value)) result[counter++] = value;
        return new Floats(result).copyTill(counter);
    }

    public Floats filterGreaterThan(float x) {
        float[] result = new float[data.length];
        int counter = 0;
        for (float value : data)
            if(value > x) result[counter++] = value;
        return new Floats(result).copyTill(counter);
    }
    public Floats filterLessThan(float x) {
        float[] result = new float[data.length];
        int counter = 0;
        for (float value : data)
            if(value < x) result[counter++] = value;
        return new Floats(result).copyTill(counter);
    }

    public Floats copyTill(int lastIdxInclusive) {
        if (lastIdxInclusive < data.length - 1)
            return get(0, lastIdxInclusive);
        return this;
    }
    public Floats append(Floats another) {
        float[] result = new float[this.data.length + another.size()];
        System.arraycopy(this.data, 0, result, 0, size());
        System.arraycopy(another.data, 0, result, this.data.length, another.size());
        return new Floats(result);
    }
    public Floats append(float another) {
        return append(new Floats(another));
    }
    public Floats diff() {
        float[] r = new float[size() - 1];
        for (int i = 0; i < data.length - 1; i++)
            r[i] = data[i+1] - data[i];
        return new Floats(r);
    }
    public Floats convolve(Floats filter) {
        float[] output = new float[size() + filter.size() - 1];
        for (int n = 0; n < output.length; n++) {
            float sum = 0;
            for (int k = 0; k < output.length; k++) {
                if(n - k < 0 || n - k >= filter.size() || k >= size()) continue;
                sum += this.get(k) * filter.get(n - k);
            }
            output[n] = sum;
        }
        return new Floats(output);
    }
    public byte[] toBinaryArray() {
        return floatToByte(toArray());
    }
    public float[] toArray() {
        float[] result = new float[size];
        System.arraycopy(data, startIdx, result, 0, size);
        return result;
    }
    public Floats plus(Floats another) {
        if(another.size() != this.size()) throw new IllegalArgumentException();
        float[] result = new float[size()];
        for(int i = 0; i < size(); i++)
            result[i] = get(i) + another.get(i);
        return new Floats(result);
    }
    public Floats plus(float v) {
        if(v == 0)
            return this;
        float[] result = new float[size()];
        for(int i = 0; i < size(); i++)
            result[i] = get(i) + v;
        return new Floats(result);
    }
    public Floats minus(Floats another) {
        return this.plus(another.times(-1));
    }

    public int closestIndex(float value) {
        int pos = Arrays.binarySearch(data, value);
        if (pos >= 0) return pos;
        pos *= -1;
        int left = Math.max(0, pos-2);
        int right =  Math.min(pos-1, data.length-1);
        return (value - data[left] < data[right] - value) ? left : right;
    }
    @Override public String toString() {
        return Arrays.toString(data);
    }
    public static byte[] floatToByte(float... input) {
        byte[] ret = new byte[input.length*4];
        for (int x = 0; x < input.length; x++) {
            ByteBuffer.wrap(ret, x*4, 4).order(LITTLE_ENDIAN).putFloat(input[x]);
        }
        return ret;
    }
    public static float[] byteToFloat(byte[] input) {
        float[] ret = new float[input.length/4];
        for (int x = 0; x < input.length; x+=4) {
            ret[x/4] = ByteBuffer.wrap(input, x, 4).order(LITTLE_ENDIAN).getFloat();
        }
        return ret;
    }
    public boolean isZero() {
        for(int i = 0; i < size(); i++)
            if(get(i) != 0)
                return false;
        return true;
    }
}
