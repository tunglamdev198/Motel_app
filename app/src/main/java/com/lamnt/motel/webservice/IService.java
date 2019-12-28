package com.lamnt.motel.webservice;

import com.lamnt.motel.model.Contract;
import com.lamnt.motel.model.Customer;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.model.Room;
import com.lamnt.motel.model.ServiceMotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {

    // API Manager

    @GET("/manager/all")
    Call<List<Manager>> getManager();

    @GET("/manager/add")
    Call<Manager> addManager(@Query("maQL") String maQL,
                             @Query("matKhau") String matKhau,
                             @Query("tenQLy") String tenQLy,
                             @Query("diaChi") String diaChi,
                             @Query("sdt") String sdt);

    @GET("/manager/search")
    Call<Manager> checkLogin(@Query("sdt") String sdt,
                             @Query("matKhau") String matKhau);

    @GET("/manager/delete")
    Call<Void> deleteManager(@Query("maQL") String maQL);

    @GET("/manager/edit")
    Call<Manager> editManager(@Query("maQL") String maQL,
                              @Query("matKhau") String matKhau,
                              @Query("tenQLy") String tenQL,
                              @Query("diaChi") String diaChi,
                              @Query("sdt") String sdt);


    // API Room

    @GET("/room/all")
    Call<List<Room>> getAll();


    @GET("/room/add")
    Call<Room> addRoom(@Query("maPhong") String maPhong,
                       @Query("maQL") String maQL,
                       @Query("dienTich") Double dienTich,
                       @Query("soNguoi") int soNguoi,
                       @Query("giaPhong") Long giaPhong);


    @GET("/room//delete")
    Call<Void> deleteRoom(@Query("maPhong") String maPhong);

    @GET("/room/edit")
    Call<Room> editRoom(@Query("maPhong") String maPhong,
                        @Query("maQL") String maQL,
                        @Query("dienTich") Double dienTich,
                        @Query("soNguoi") int soNguoi,
                        @Query("giaPhong") Long giaPhong);


    /**
     * API service
     */

    @GET("/service/all")
    Call<List<ServiceMotel>> getService();

    @GET("/service/add")
    Call<ServiceMotel> addService(@Query("maDV") String maDV,
                                  @Query("tenDV") String tenDV,
                                  @Query("giaDV") Double giaDV);

    @GET("/service/delete")
    Call<Void> deleteServiceMotel(@Query("maDV") String maDV);

    @GET("/service/edit")
    Call<ServiceMotel> editService(@Query("maDV") String maDV,
                                   @Query("tenDV") String tenDV,
                                   @Query("giaDV") Double giaDV);


    @GET("/customers/all")
    Call<List<Customer>> getALlCustomer();

    @GET("/customers/add")
    Call<Customer> addCustomer(@Query("maKT") String maKT,
                               @Query("matKhau") String matKhau,
                               @Query("hoTen") String hoTen,
                               @Query("diaChi") String diaChi,
                               @Query("cmnd") String cmnd,
                               @Query("sdt") String sdt,
                               @Query("ngheNghiep") String ngheNghiep,
                               @Query("maQL") String maQL);

    @GET("/customers/delete")
    Call<Void> deleteCustomer(@Query("maKT") String maKT);

    @GET("/customers/edit")
    Call<Customer> editCustomer(@Query("maKT") String maKT,
                                @Query("matKhau") String matKhau,
                                @Query("hoTen") String hoTen,
                                @Query("diaChi") String diaChi,
                                @Query("cmnd") String cmnd,
                                @Query("sdt") String sdt,
                                @Query("ngheNghiep") String ngheNghiep,
                                @Query("maQL") String maQL);


    // API Contract

    @GET("/contract/all")
    Call<List<Contract>> getAllContract();

    @GET("/contract/add")
    Call<Contract> addContract(@Query("maHopDong") String maHopDong,
                               @Query("maKT") Customer maKT,
                               @Query("tenKT") String tenKT,
                               @Query("maPhong") Room maPhong,
                               @Query("ngayThue") String ngayThue,
                               @Query("ngayTra") String ngayTra);

    @GET("/contract/delete")
    void deleteContract(@Query("maHopDong") String maHopDong);

    @GET("/contract/edit")
    Contract editContract(@Query("maHopDong") String maHopDong,
                          @Query("maKT") Customer maKT,
                          @Query("tenKT") String tenKT,
                          @Query("maPhong") Room maPhong,
                          @Query("ngayThue") String ngayThue,
                          @Query("ngayTra") String ngayTra);
}
