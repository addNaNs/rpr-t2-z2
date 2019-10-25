package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

import static java.lang.Double.max;
import static java.lang.Double.min;

public class Interval {
    private double a,b;
    private boolean ai,bi;

    public Interval(double a, double b, boolean ai, boolean bi) throws IllegalArgumentException {
        if (a>b) throw new IllegalArgumentException();
        this.a=a;
        this.b=b;
        this.ai=ai;
        this.bi=bi;
    }

    public Interval(){
        a=0;
        b=0;
        ai=false;
        bi=false;
    }

    public boolean isNull(){
        return a==0 && b==0 && !ai && !bi;
    }

    public boolean isIn(double x){
        return (a<x && x<b) || (a==x && ai) || (x==b && bi);
    }

    public Interval intersect(Interval i){
        double x,y;
        boolean xi,yi;

        if(this.a > i.a){
            x=this.a;
            xi=this.ai;
        } else if(this.a < i.a){
            x=i.a;
            xi=i.ai;
        }else{
            x=this.a;
            xi = this.ai && i.ai;
        }

        if(this.b < i.b){
            y=this.b;
            yi=this.bi;
        } else if(this.b > i.b){
            y=i.b;
            yi=i.bi;
        }else{
            y=this.b;
            yi = this.bi && i.bi;
        }

        try {
            return new Interval(x,y,xi,yi);
        } catch (IllegalArgumentException e){
            return new Interval();
        }
    }

    public static Interval intersect(Interval i1, Interval i2){
        return i1.intersect(i2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.a, a) == 0 &&
                Double.compare(interval.b, b) == 0 &&
                ai == interval.ai &&
                bi == interval.bi;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public boolean isAi() {
        return ai;
    }

    public boolean isBi() {
        return bi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, ai, bi);
    }

    public String toString(){
        if (a==0 && b==0 && !ai && !bi) return "()";
        return (ai?"[":"(") + String.valueOf(a) + "," + String.valueOf(b) + (bi?"]":")");
    }
}
