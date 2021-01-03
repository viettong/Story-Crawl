
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
//        CongNhan cn = new CapThap();
//        cn.setA(2);
//        cn.setB(2);
//        
//        CongNhan cn1 = new CapCao();
//        cn1.setA(2);
//        cn1.setB(2);
//        List<CongNhan> list = new LinkedList<>() ;
//        list.add(cn1);
//        list.add(cn);
//
//        
//        for(CongNhan c: list){
//            System.out.println( c.tienLuong());;
//        }

        double[] vector1 = {1,8,9,9,1};
        double[] vector2 = {1,4,3,6,-1};
        double[] vector3 = {1,9,9,8,1};
        double[] vector4 = {1,5,4,5,-1};
        double[] w = {-1,1,5,-5};
        Test t = new Test();
       // t.lap(w,vector1,vector2,vector3,vector4);
        t.check(w, vector1);
        t.check(w, vector2);
        t.check(w, vector3);
        t.check(w, vector4);
        
        
      
    }

    private double check(double[] w, double[] vector) {
        double tong = w[0]*vector[0]+w[1]*vector[1]+w[2]*vector[2]+w[3]*vector[3];
        System.out.printf("TOng: "+tong);
        if(tong>=0){
            tong = 1;
        }
        else{
            tong = -1;
        }
        System.out.println(":"+tong);
        return tong;
    }
    
    private double[] upradeW(double[] w,double[] vector,double out) {
        w[0] = w[0]+(vector[4]-out)*vector[0];
        w[1] = w[1]+(vector[4]-out)*vector[1];
        w[2] = w[2]+(vector[4]-out)*vector[2];
        w[3] = w[3]+(vector[4]-out)*vector[3];
        System.out.println(Arrays.toString(w));
        return w;
        
    }
    
    private void lap(double[] w, double[] vector1, double[] vector2, double[] vector3, double[] vector4) {
       boolean[] isTrue = {false,false,false,false};
       TrongSo trongSo = new TrongSo();
       trongSo.setW(w);
       
       while(isTrue[0]==false || isTrue[1]==false || isTrue[2]==false || isTrue[3]==false)
       {
                if(check(trongSo.getW(), vector1)!=vector1[4]){
                    trongSo.setW(upradeW(trongSo.getW(), vector1, check(trongSo.getW(), vector1)));
                    isTrue[0] = false;
                    System.out.println("lap1");
                }
                else{
                    isTrue[0] = true;
                }
                if(check(trongSo.getW(), vector2)!=vector2[4]){
                    trongSo.setW(upradeW(trongSo.getW(), vector2, check(trongSo.getW(), vector2)));
                    isTrue[1] = false;
                    System.out.println("lap2");
                }
                else{
                    isTrue[1] = true;
                }
                if(check(trongSo.getW(), vector3)!=vector3[4]){
                    trongSo.setW(upradeW(trongSo.getW(), vector3, check(trongSo.getW(), vector3)));
                    isTrue[2] = false;
                    System.out.println("lap3");
                }
                else{
                    isTrue[2] = true;
                }
                if(check(trongSo.getW(), vector4)!=vector4[4]){
                    trongSo.setW(upradeW(trongSo.getW(), vector4, check(trongSo.getW(), vector4)));
                    isTrue[3] = false;
                    System.out.println("lap4");
                }
                else{
                    isTrue[3] = true;
                }
               
       }
        System.out.println(Arrays.toString(trongSo.w));
        
        
    }

   
    
}
