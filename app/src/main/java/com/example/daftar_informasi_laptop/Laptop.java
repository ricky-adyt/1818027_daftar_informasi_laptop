package com.example.daftar_informasi_laptop;

public class Laptop {
    private String _id, _nama, _tipe,_warna, _harga ;
    private byte[] _image;
    public Laptop (String id, byte[] image, String nama, String tipe,String warna, String harga ) {
        this._id = id;
        this._image = image;
        this._nama = nama;
        this._tipe = tipe;
        this._warna = warna;
        this._harga = harga;

    }
    public Laptop() {
    }
    public byte[] get_image() {
        return _image;
    }
    public void set_image(byte[] _image) {
        this._image = _image;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_nama() {
        return _nama;
    }
    public void set_nama(String _nama) { this._nama = _nama; }
    public String get_tipe() {
        return _tipe;
    }
    public void set_tipe(String _tipe) {
        this._tipe = _tipe;
    }
    public String get_warna() {
        return _warna;
    }
    public void set_warna(String _warna) {
        this._warna = _warna;
    }
    public String get_harga() {
        return _harga;
    }
    public void set_harga(String _harga) {
        this._harga = _harga;
    }

}
