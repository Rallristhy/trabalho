package com.rallristhy.trabalho.trabalhoandroid.model;

/**
 * Created by rallristhy on 19/07/2017.
 */

public class Estabelecimentos {
    private Integer id;
    private String nome;
    private Coord coord;
    private End end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }
}
