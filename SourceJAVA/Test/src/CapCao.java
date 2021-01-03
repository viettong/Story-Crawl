/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class CapCao extends CongNhan{

    @Override
    public int tienLuong() {
        return tinhTienLuong();
    }
    public int tinhTienLuong(){
        return this.a*this.b*2;
    }
    
}